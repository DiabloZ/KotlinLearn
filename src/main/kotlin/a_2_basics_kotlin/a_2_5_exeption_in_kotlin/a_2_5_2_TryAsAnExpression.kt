package main.kotlin.a_2_basics_kotlin.a_2_5_exeption_in_kotlin

import java.io.BufferedReader
import java.io.StringReader
import java.lang.NumberFormatException

fun a_2_5_2_TryAsAnExpression() {
    BufferedReader(StringReader("not a number")).let {
        println("BufferedReader - ${readNumberNull(it)}")
    }
    BufferedReader(StringReader("123")).let {
        println("BufferedReader - ${readNumberNull(it)}")
    }
    BufferedReader(StringReader("not a number")).let {
        println("BufferedReader - ${readNumberReturn(it)}")
    }
    BufferedReader(StringReader("123")).let {
        println("BufferedReader - ${readNumberReturn(it)}")
    }

    catchException {
        println("catchException - ${percentException(1)}")
    }
    catchException {
        println("catchException - ${percentException(101)}")
    }

    catchException {
        percentException(2)
    }.let {
        when(it){
            is Int -> println("$it")
            is ArrayList<*> -> println("when(it) - $it")
            is Unit -> println("Unit")
        }
    }
    catchException {
        percentException(202)
    }.let {
        when(it){
            is Int -> println("$it")
            is ArrayList<*> -> println("when(it) - $it")
            is Unit -> println("Unit")
        }
    }
    println("percent - ${catchException { percentException(10) }}")
    println("percent - ${catchException { percentException(101) }}")
    println("percent - ${catchException { percentException(12) }}")
}

fun percentException(percent: Int) = when (percent) {
    !in 0..100 -> throw IllegalArgumentException(
        "A percentage value must be between 0 and 100: now - $percent"
    )
    else -> percent
}

fun catchException(function : () -> Any) =
    try {
        function()
    } catch (e: Exception){
        e.printStackTrace()
    } finally {
        println("catch completed")
    }



fun readNumberReturn(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        return
    }
    println(number)
}

fun readNumberNull(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        null
    }
    println(number)
}