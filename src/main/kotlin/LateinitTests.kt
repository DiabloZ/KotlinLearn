package main.kotlin

import kotlin.reflect.KProperty

class SomeLateInit<T>(
    private val lazyValue: () -> T
) {
    val value: T by lazy { lazyValue.invoke() }
    operator fun invoke(): T {
        return value
    }
}

operator fun <T> SomeLateInit<T>.getValue(thisObj: Any?, property: KProperty<*>): T = value

inline fun <T> remember(
    key1: Any?,
    crossinline calculation: () -> T
): T {
    return calculation.invoke()
}

private class TestClass(
    val someString: SomeLateInit<String> = SomeLateInit { "123" }
) {
    fun testTest() {
        println(
            someString.invoke()
        )
        println(
            someString.value
        )
        println(
            someString()
        )

    }


}

fun main() {
    TestClass().testTest()
}