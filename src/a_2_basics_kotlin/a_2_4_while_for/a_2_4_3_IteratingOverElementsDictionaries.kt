package a_2_basics_kotlin.a_2_4_while_for

import java.util.*

fun a_2_4_3_IteratingOverElementsDictionaries() {
    for ((letter, binary) in createBinaryMap()){
        println("$letter = $binary")
    }

    for ((index, element) in listInt().withIndex()){
        println("${index + 1}: $element")
    }
}

fun createBinaryMap(): TreeMap<Char, String> =
    TreeMap<Char, String>().apply{
        for (c in 'A'..'F'){
            val binary = Integer.toBinaryString(c.code)
            this[c] = binary
        }
    }

fun listInt() = arrayListOf(10, 11, 222, 203, 3)