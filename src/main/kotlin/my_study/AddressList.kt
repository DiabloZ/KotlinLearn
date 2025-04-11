package main.kotlin.my_study

import org.apache.commons.net.telnet.TelnetClient
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.Inet4Address
import java.net.InetAddress
import java.nio.charset.StandardCharsets


data class RouterConfig(
    val routerHost: String,
    val routerUser: String,
    val routerPassword: String,
    val interfaceName: String,
    val routerPort: Int,
    val domains: List<String>
)

val configFile = File("router_config.json")
val config = if (configFile.exists()) {
    parseJson(configFile.readText())

} else {
    error("!!!")
}

fun parseJson(json: String): RouterConfig {
    val routerHost = json.substringAfter("\"routerHost\": \"").substringBefore("\",")
    val routerUser = json.substringAfter("\"routerUser\": \"").substringBefore("\",")
    val routerPassword = json.substringAfter("\"routerPassword\": \"").substringBefore("\",")
    val interfaceName = json.substringAfter("\"interfaceName\": \"").substringBefore("\",")
    val routerPort = json.substringAfter("\"routerPort\": ").substringBefore(",").toInt()
    val domains =
        json.substringAfter("\"domains\": [").substringBefore("]").split(",").map { it.trim().removeSurrounding("\"") }

    return RouterConfig(
        routerHost = routerHost,
        routerUser = routerUser,
        routerPassword = routerPassword,
        interfaceName = interfaceName,
        routerPort = routerPort,
        domains = domains
    )
}

val routerHost = config.routerHost
val routerUser = config.routerUser
val routerPassword = config.routerPassword
val interfaceName = config.interfaceName
val routerPort = config.routerPort
val domains = config.domains

fun logWithTimeStamp(message: String, level: LOG_LEVEL = LOG_LEVEL.INFO) {
    println(
        "$level "+"${
            java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))
        } $message"
    )
}
enum class LOG_LEVEL {
    INFO,
    ERROR,
    DEBUG,
    TRACE,
    WARN,
    FATAL,
    CRITICAL,
}
fun main() {
    while (true) {
        logWithTimeStamp("Запускаем скрипт для добавления маршрутов в роутер")
        val domainsResult = getIpAddressess(domains)
        addDomainsToRouter(domainsResult)
        logWithTimeStamp("Останавливаемся, ждём")
        Thread.sleep(1000 * 60)
    }
}

data class NewRoute(
    val address: String,
    val description: String
)

fun filteredRoutes(domains: List<Domain>, weHave: List<Route>): List<NewRoute> {
    return domains.flatMap { domain ->
        domain.addresses.map { NewRoute(address = it, domain.domainName) }
    }.filter { newRouteCandidate ->
        weHave.none { route ->
            route.destination == newRouteCandidate.address
        }
    }
}

var allRoutesWithWeHave = emptyList<Route>()

private fun addDomainsToRouter(domains: List<Domain>) {
    val telnetClient by lazy {
        TelnetClient().apply { connect(routerHost, routerPort) }
    }
    val inputStream by lazy { telnetClient.inputStream }
    val outputStream by lazy { telnetClient.outputStream }
    val isSuccessAuthenticate by lazy { authenticate(inputStream, outputStream, routerUser, routerPassword) }
    val allNewRoutes = if (allRoutesWithWeHave.isEmpty()) {
        if (!isSuccessAuthenticate) {
            logWithTimeStamp("Не удалось пройти аутентификацию.")
            return
        }
        allRoutesWithWeHave = getAllRotes(inputStream, outputStream)
        filteredRoutes(domains, allRoutesWithWeHave)
    } else {
        filteredRoutes(domains, allRoutesWithWeHave)
    }

    allRoutesWithWeHave = allRoutesWithWeHave.toMutableList().apply {
        addAll(allNewRoutes.map { Route(it.address, "", interfaceName, "", 0) })
    }

    if (allNewRoutes.isEmpty()) {
        logWithTimeStamp("Нет новых маршрутов для добавления.")
        return
    } else {
        logWithTimeStamp("Количество добавляемых адресов: ${allNewRoutes.size}", level = LOG_LEVEL.CRITICAL)
        allNewRoutes.forEach {
            logWithTimeStamp("Добавляемые адреса:  - ${it.address}", level = LOG_LEVEL.WARN)
        }
    }

    try {
        if (!isSuccessAuthenticate) {
            logWithTimeStamp("Не удалось пройти аутентификацию.")
            return
        }
        allNewRoutes.forEach { newRoute ->
            addRouteTelnet(
                inputStream = inputStream,
                outputStream = outputStream,
                destinationIp = newRoute.address,
                description = newRoute.description,
                interfaceName = interfaceName
            )
            logWithTimeStamp("Маршрут для $newRoute успешно добавлен", level = LOG_LEVEL.TRACE)
        }
    } catch (e: IOException) {
        logWithTimeStamp("Ошибка Telnet: ${e.message}")
    } finally {
        try {
            outputStream.write("system configuration save\r\n".toByteArray(StandardCharsets.UTF_8))
            outputStream.flush()
            readUntil(
                inputStream = inputStream,
                pattern = "Core::System"
            )
            outputStream.write("exit\r\n".toByteArray(StandardCharsets.UTF_8))
            outputStream.flush()
            telnetClient.disconnect()
        } catch (e: IOException) {
            // Обработка ошибки отключения
        }
    }
}

fun authenticate(inputStream: InputStream, outputStream: OutputStream, login: String, password: String): Boolean {
    // Ожидаем приглашение ввести логин
    readUntil(inputStream, "Login:")
    outputStream.write("$login\r\n".toByteArray(StandardCharsets.UTF_8))
    outputStream.flush()

    // Ожидаем приглашение ввести пароль
    readUntil(inputStream, "Password:")
    outputStream.write("$password\r\n".toByteArray(StandardCharsets.UTF_8))
    outputStream.flush()

    // Ожидаем приглашение командной строки (может отличаться)
    val prompt = readUntil(inputStream, ">") // Или "(config)>" в зависимости от уровня
    return !prompt.contains("Login incorrect") && !prompt.contains("Authentication failed")
}

fun getAllRotes(inputStream: InputStream, outputStream: OutputStream): List<Route> {
    outputStream.write("show ip route\r\n".toByteArray(StandardCharsets.UTF_8))
    outputStream.flush()
    val response = readUntil(inputStream, ">").substringAfterLast("=")
    val responses = parseRoutes(response)
    return responses
}

fun addRouteTelnet(
    inputStream: InputStream,
    outputStream: OutputStream,
    destinationIp: String,
    description: String,
    interfaceName: String
) {

    //ip route <сеть_назначения> <маска_подсети> <шлюз> [интерфейс] [auto]
    //val command = "ip route 173.194.220.138 255.255.255.255 0.0.0.0 WifiMaster0/WifiStation0 auto !123"
    val command = "ip route $destinationIp 255.255.255.255 0.0.0.0 $interfaceName auto !$description\r\n"
    //logWithTimeStamp("Отправляем команду: $command")
    outputStream.write(command.toByteArray(StandardCharsets.UTF_8))
    outputStream.flush()
    readUntil(inputStream, "(config)>")
}

fun readUntil(inputStream: InputStream, pattern: String = "(config)>"): String {
    val sb = StringBuilder()
    var resultString = ""
    try {
        while (true) {
            val char = inputStream.read()
            if (char == -1) {
                break
            }
            sb.append(char.toChar())
            val sbArray = sb.toString().toByteArray()
            val recoveredString = String(sbArray, Charsets.UTF_8)
            if (recoveredString.lowercase().contains(pattern.lowercase())) {
                resultString = recoveredString
                break
            }
        }
    } catch (e: IOException) {
        // Обработка ошибки чтения
    }
    //logWithTimeStamp("Ответ сервера : ${resultString}}")
    return sb.toString()
}


fun getIpAddressess(domains: List<String>): List<Domain> {
    // Список доменных имён, на основе которых требуется получить IP-адреса:

    val domainResult = mutableListOf<Domain>()
    // Проходим по каждому домену и выводим список IP-адресов
    domains.forEach { domain ->
        try {
            val domainAddressList = mutableListOf<String>()
            val addresses = InetAddress.getAllByName(domain)  // Получаем массив InetAddress
            //logWithTimeStamp("IP-адреса для $domain:")
            addresses.forEach { addr ->
                if (addr is Inet4Address) {
                    domainAddressList.add(addr.hostAddress)
                    //logWithTimeStamp("  ${addr.hostAddress}")
                }
            }
            if (domainAddressList.isNotEmpty()) {
                domainResult.add(
                    Domain(
                        domainName = domain,
                        addresses = domainAddressList
                    )
                )
            }
        } catch (ex: Exception) {
            //logWithTimeStamp("Не удалось разрешить домен $domain: ${ex.message}")
        }
        //println() // Пустая строка для разделения вывода
    }
    return domainResult
}

data class Domain(
    val domainName: String,
    val addresses: List<String> = mutableListOf()
)

fun parseRoutes(input: String): List<Route> {
    val routes = mutableListOf<Route>()

    // Разделяем ввод на строки
    val lines = input.lines()

    // Обрабатываем каждую строку
    for (line in lines) {
        // Пропускаем пустые строки
        if (line.isBlank()) continue

        // Разделяем строку на компоненты
        val parts = line.split(Regex("\\s+"))

        // Проверяем, что строка корректная и содержит 5 элементов
        if (parts.size >= 5) {
            // Создаем объект Route
            val route = Route(
                destination = parts[0].substringBeforeLast("/"),
                gateway = parts[1],
                interfaceName = parts[2],
                flags = parts[3],
                refCount = parts[4].toIntOrNull() ?: 0 // если значение не число, ставим 0
            )

            // Добавляем маршрут в список
            routes.add(route)
        }
    }

    return routes
}

data class Route(
    val destination: String,
    val gateway: String,
    val interfaceName: String,
    val flags: String,
    val refCount: Int
)
