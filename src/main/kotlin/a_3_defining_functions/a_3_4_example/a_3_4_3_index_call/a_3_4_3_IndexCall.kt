package a_3_defining_functions.a_3_4_example.a_3_4_3_index_call

fun a_3_4_3_IndexesCall() {
    val map = mapOf(1 to "one",
        7 to "seven",
        53 to "fifty-three",
        2.to("two")
    )

    val (number, name) = 1 to "one"
    val test2 = 1 to "one"
    val test3 = Pair(2 , "two")
    val (number2, name2) = map.entries.find { it.key == 7 }?.toPair() ?: (2 to "two")
    println("number - $number, name - $name")
    println("number2 - $number2, name2 - $name2")
}