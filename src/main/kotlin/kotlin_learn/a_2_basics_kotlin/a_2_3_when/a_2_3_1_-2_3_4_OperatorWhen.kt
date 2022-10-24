package main.kotlin.kotlin_learn.a_2_basics_kotlin.a_2_3_when
import main.kotlin.kotlin_learn.a_2_basics_kotlin.a_2_3_when.Color.*

enum class Color(private val r: Int, private val g: Int, private val b: Int) {
    RED(255, 0, 0),
    ORANGE(255, 165, 0),
    YELLOW(255, 255, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255),
    INDIGO(75, 0, 130),
    VIOLET(238, 130, 238);

    fun rgb() = (r * 256 + g) * 256 + b


}

fun a_2_3_1__2_3_4_ExampleWhen(){
    with(VIOLET){
        println("RGB - ${rgb()}") //2.3.1
        println("Mnemonic - ${getMnemonic(this)}") //2.3.2
        println("Температура цвета $this - ${getWarmth(this)}") //2.3.2
        println("Получился цвет ($this + BLUE) - ${mixColor( this, BLUE)}") //2.3.3
        println("Получился цвет ($this + BLUE) - ${mixColorWithOutSetOfWithOptimized( this, BLUE)}") //2.3.4
    }
}

fun getMnemonic(color: Color) =
    when(color){
        RED -> "$color Каждый"
        ORANGE -> "$color Охотник"
        YELLOW -> "$color Желает"
        GREEN -> "$color Знать"
        BLUE -> "$color Где"
        INDIGO -> "$color Сидит"
        VIOLET -> "$color Фазан"
    }

fun getWarmth(color: Color) =
    when(color){
        RED, ORANGE, YELLOW -> "Тёплая"
        GREEN -> "Нейтральная"
        BLUE, INDIGO, VIOLET -> "Холодная"
    }

fun mixColor(c1: Color, c2: Color) =
    when(setOf(c1, c2)){
        setOf(RED, YELLOW) -> ORANGE
        setOf(YELLOW, BLUE) -> GREEN
        setOf(BLUE, VIOLET) -> INDIGO
        else -> throw Exception("Неясный цвет")
    }

fun mixColorWithOutSetOfWithOptimized(c1: Color, c2: Color) =
    when {
        (c1 == RED && c2 == YELLOW) ||
        (c1 == YELLOW && c2 == RED) -> ORANGE
        (c1 == YELLOW && c2 == BLUE) ||
        (c1 == BLUE && c2 == YELLOW) -> GREEN
        (c1 == BLUE && c2 == VIOLET) ||
        (c1 == VIOLET && c2 == BLUE) -> INDIGO
        else -> throw Exception("Неясный цвет")
    }