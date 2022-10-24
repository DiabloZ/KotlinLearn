package main.kotlin.kotlin_learn.a_2_basics_kotlin.a_2_4_while_for

fun a_2_4_4_InForCheckEntriesInCollections(){
    println("w is isLetter - ${isLetter('w')}")
    println("3 is isLetter - ${isLetter('3')}")
    println("Ы is isRuLetter - ${isRuLetter('Ы')}")
    println("w is isRuLetter - ${isRuLetter('w')}")
    println("2 is isNotDigit - ${isNotDigit('2')}")
    println("q is isNotDigit - ${isNotDigit('q')}")

    println("q - ${recognize('q')}")
    println("1 - ${recognize('1')}")
    println("ц - ${recognize('ц')}")
    println("% - ${recognize('%')}")


    println("Kotlin" in "Java".."Scala")
    println("Kotlin" in setOf("Java", "Scala"))
}


fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'
fun isRuLetter(c: Char) = c in 'а'..'я' || c in 'А'..'Я'
fun isNotDigit(c: Char) = c !in '0'..'9'

fun recognize(c: Char) = when(c) {
    in '0'..'9' -> "It's a digit!"
    in 'a'..'z', in 'A'..'Z' -> "It's a letter!"
    in 'а'..'я', in 'А'..'Я' -> "It's a russian letter!"
    else -> "I don't know..."
}