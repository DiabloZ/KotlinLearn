package main.kotlin.my_study

import java.net.InetAddress

fun main() {
    getIpAddressess()
}

fun getIpAddressess() {
    // Список доменных имён, на основе которых требуется получить IP-адреса:
    val domains = listOf(
        "github.com",
        "api.github.com",
        "copilot-telemetry.githubusercontent.com",
        "copilot-proxy.githubusercontent.com",
        "origin-tracker.githubusercontent.com"
        // Обратите внимание: wildcard-записи, такие как *.githubcopilot.com,
        // не могут быть напрямую разрешены в отдельный IP.
    )

    // Проходим по каждому домену и выводим список IP-адресов
    domains.forEach { domain ->
        try {
            val addresses = InetAddress.getAllByName(domain)  // Получаем массив InetAddress
            println("IP-адреса для $domain:")
            addresses.forEach { addr ->
                println("  ${addr.hostAddress}")
            }
        } catch (ex: Exception) {
            println("Не удалось разрешить домен $domain: ${ex.message}")
        }
        println() // Пустая строка для разделения вывода
    }
}