package main.kotlin.kotlin_learn.a_2_basics_kotlin.a_2_5_exeption_in_kotlin

import java.io.BufferedReader
import java.io.StringReader
import java.lang.NumberFormatException

fun a_2_5_1_TryCatchFinally() {
    BufferedReader(StringReader("12a3")).let {
        println("BufferedReader - ${readNumber(it)}")
    }
    BufferedReader(StringReader("123")).let {
        println("BufferedReader - ${readNumber(it)}")
    }
}

fun readNumber(reader: BufferedReader) =
    try {
        val line = reader.readLine()
        Integer.parseInt(line)
    } catch (e: NumberFormatException) {
        null
    }
    finally {
        reader.close()
    }