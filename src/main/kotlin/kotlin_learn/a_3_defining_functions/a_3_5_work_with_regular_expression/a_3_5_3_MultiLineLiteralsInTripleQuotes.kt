package main.kotlin.kotlin_learn.a_3_defining_functions.a_3_5_work_with_regular_expression

fun a_3_5_3_MultiLineLiteralsInTripleQuotes() {
    val kotlinLogo = """| //
                       .|//
                       .|/ \""".trimMargin(".")
    println(kotlinLogo)

    //единственный способ встроить литерал со знаком доллара
    val price = """${'$'}99.9"""
    println("price - $price")
}