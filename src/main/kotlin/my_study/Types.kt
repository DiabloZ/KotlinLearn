package main.kotlin.my_study

import kotlin.reflect.KClass
import kotlin.reflect.cast

fun main() {
    trySome()
}

fun trySome() {
    val some = Some.of("123") { it.toDouble() }
    val some2 = Some.of("321") { it.toInt() }
    val some3 = Some.of("string") { it }
    val someList: List<Some<*>> = listOf(some, some2, some3)
    someList.forEach {
        println(
            handleSomeDynamically(it)
        )
    }
    val someFiltered: List<Some<String>> = someList.filter {
        handleSomeDynamically(it) is Double
    }.filterIsInstance<Some<String>>()

    val someFiltered2: List<Some<String>> = someList.filter {
        handleSomeDynamically(it) is Int
    }.filterIsInstance<Some<String>>()

    val someFiltered3: List<Some<String>> = someList.filter {
        handleSomeDynamically(it) is String
    }.filterIsInstance<Some<String>>()

    println("11111")
    println("SomeList- ${someList.size}")
    someList.forEach { println(it.value) }
    println("222222")
    println("SomeFiltered - ${someFiltered.size}")
    someFiltered.forEach { println(it.value) }
    println("333333")
    println("SomeFiltered2 - ${someFiltered2.size}")
    someFiltered2.forEach { println(it.value) }
    println("44444")
    println("SomeFiltered3 - ${someFiltered3.size}")
    someFiltered3.forEach { println(it.value) }
}

data class Some<T : Any>(
    val value: String,
    val type: KClass<T>,
    val toAny: (String) -> T
) {
    companion object {
        inline fun <reified T : Any> of(value: String, noinline toAny: (String) -> T): Some<T> {
            return Some(value, T::class, toAny)
        }
    }
}

fun handleSomeDynamically(some: Some<*>): Any? {
    val clazz = some.type // Это Class<T>, хотя T мы не знаем

    println("Тип внутри Some: ${clazz.simpleName}")
    println("Значение: ${some.value}")

    return if (clazz.isInstance(some.toAny.invoke(some.value))) {
        // Мы знаем, что value действительно соответствует type
        val valueAsTyped = clazz.cast(some.toAny.invoke(some.value))
        println("Работаем с value как с ${clazz.simpleName}: $valueAsTyped")
        valueAsTyped
    } else {
        println("value не соответствует указанному type!")
        null
    }
}

fun someUnpack(some: Some<*>): Any? {
    return if (some.type.isInstance(some.toAny.invoke(some.value))) {
        @Suppress("UNCHECKED_CAST")
        val typedSome = some as Some<Any>
        typedSome.value // теперь это точно тип T
    } else {
        null // значение не соответствует ожидаемому типу
    }
}
fun <T: Any> someUnpack2(some: Some<*>): Any? {
    return if (some.type.isInstance(some.value)) {
        @Suppress("UNCHECKED_CAST")
        val typedSome = some as Some<T>
        typedSome.value // теперь это точно тип T
    } else {
        null // значение не соответствует ожидаемому типу
    }
}