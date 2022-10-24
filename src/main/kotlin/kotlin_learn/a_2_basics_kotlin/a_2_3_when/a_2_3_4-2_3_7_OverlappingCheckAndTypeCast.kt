package main.kotlin.kotlin_learn.a_2_basics_kotlin.a_2_3_when

fun a_2_3_4__2_3_7_OverlappingCheckAndTypeCast() {
    println(
        "eval - ${eval(Sum(Sum(Num(1), Num(2)), Num(4)))}"
    )
    println(
        "eval smart - ${evalSmart(Sum(Sum(Num(3), Num(2)), Num(3)))}"
    )
}

interface Expr

class Num(val value: Int): Expr

class Sum(val left: Expr, val right: Expr): Expr

fun eval(e: Expr): Int {
    if (e is Num){
        val n = e as Num
        return n.value
    }

    if (e is Sum){
        return eval((e.right)) + eval(e.left)
    }

    throw IllegalArgumentException("Unknown expression")
}

fun evalSmart(e: Expr) :Int =
    when(e){
        is Num -> {
            println("Num - ${e.value}")
            e.value
        }
        is Sum -> {
            val right = evalSmart(e.right)
            val left = evalSmart(e.left)
            println("Sum : $left + $right")
            left + right
        }
        else -> throw IllegalArgumentException("Unknown expression")
    }