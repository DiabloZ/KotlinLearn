package main.kotlin.a_3_defining_functions.a_3_3_added_method_in_class

import main.kotlin.a_3_defining_functions.a_3_3_added_method_in_class.lastCharFun as lol

fun a_3_3_1_importAndFunctionExtensions() {
    val set = hashSetOf(1, 7, 53)
    val list = arrayListOf(1, 7, 53)
    val map = hashMapOf(1 to "one", 7 to "seven", 53 to "fifty-three")
    val string = listOf("one", "two", "three")
    listOf("test: ", string )
    println(set.javaClass)
    println(list.javaClass)
    println(map.javaClass)


    println(string.last())

    println(list.maxOrNull())

    println(list)

    println(
        joinToString(
            collection = list,
            separator = "; ",
            prefix = "(",
            postfix = ")"
        )
    )
    println(joinToString(list))
    println(joinToString(collection = list, separator = "; "))
    println(joinToString(collection = list, prefix = "# ", postfix = ";"))

    println("last char - ${"tes".lol()}") // import lastChar as lol

}

fun String.lastCharFun(): Char = this[this.length - 1]
fun String.lastCharCleanUp(): Char = get(length - 1)