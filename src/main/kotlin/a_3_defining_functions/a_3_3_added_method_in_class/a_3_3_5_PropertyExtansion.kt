package main.kotlin.a_3_defining_functions.a_3_3_added_method_in_class

fun a_3_3_5_PropertyExtension (){
    println("last char property - ${"test".lastChar}")

    StringBuilder("Kot12lin?").apply {
        lastChar = '!'
        append(conteins123)
    }.println
}

val String.lastChar: Char
    get() = get(length - 1)

var java.lang.StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value) = this.setCharAt(length - 1, value)

val java.lang.StringBuilder.println
    get() = println(this)

val java.lang.StringBuilder.conteins123
    get() = this.contains("123")