package main.kotlin.a_3_defining_functions.a_3_5_work_with_regular_expression

//89
fun a_3_5_1_StringDivider(){
    //переопределенный метод с явной передачей регулярного выражения
    println("with regular - ${"12.345-6.1.5?2!6,123".split("[.\\-!?,]".toRegex())}")

    //Обычный метод без регулярки
    println("without regular with string - ${"12.345-6.1.5?2!6,123".split(".", "-", "!", "?", ",")}")
    println("without regular with char - ${"12.345-6.1.5?2!6,123".split('.', '-', '!', '?', ',')}")

}