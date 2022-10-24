package main.kotlin.kotlin_learn.a_3_defining_functions.a_3_3_added_method_in_class

import java.lang.StringBuilder


fun a_3_3_3_HelpedFunctionsAsExtensions() {
    println("last char - ${"teZ".lastCharCleanUp()}")

    val set = hashSetOf(1, 7, 53)
    val list = arrayListOf(1, 7, 53)

    println(list.joinToStringFinal(" "))
    println(set.joinToStringFinal(" "))

    println(listOf("one", "two", "eight").cutJoinOnlyStrings(" "))
    //println(list.cutJoinOnlyStrings()) //error - type mismatch
}

fun <T> Collection<T>.joinToStringFinal(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
) = StringBuilder(prefix).let { sb ->
    for ((index, element) in this.withIndex()){
        if (index > 0) sb.append(separator)
        sb.append(element)
    }
    sb.append(postfix)
}.toString()

fun Collection<String>.cutJoinOnlyStrings(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
) = joinToStringFinal(separator, prefix, postfix)

fun <T> joinToString(
    collection: Collection<T>,
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
) = StringBuilder(prefix).apply {
    for ((index, element) in collection.withIndex()){
        if (index > 0) append(separator)
        append(element)
    }
    append(postfix)
}.toString()