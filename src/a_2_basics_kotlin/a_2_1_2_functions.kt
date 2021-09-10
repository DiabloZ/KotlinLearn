package a_2_basics_kotlin

import kotlin.random.Random

fun a_2_1_2_FunVariableInClass(){
    /*val rectangleList = listOf(
        Rectangle(1, 4),
        Rectangle(1, 1),
        Rectangle(2, 2),
        Rectangle(4, 3)
    )*/
    val rectangleList = createRandomListRectangles(1000)

    rectangleList.filter { it.isSquare }.apply {
        onEach {
            println("Rectangle is square - height - ${it.height}, width - ${it.width}, Index - ${it.index}")
        }
        println("total counter - $size")
    }


    /*rectangleList.filter { !it.isSquare }.forEach {
        println("Rectangle isn't square - \nheight - ${it.height}\nwidth - ${it.width}")
    }*/
}

class Rectangle (val height:Int, val width: Int, val index: Int = 0) {
    val isSquare = height == width
}

fun createRandomListRectangles(amount: Int) : ArrayList<Rectangle> {
    Random.apply {
        val list = ArrayList<Rectangle>()
        for (i in 0 until amount){
            list.add(Rectangle(nextInt(100), nextInt(100), i))
        }
        return list
    }
}