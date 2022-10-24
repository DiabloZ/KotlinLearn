package main.kotlin.kotlin_learn.a_4_create_class_hierarchy

interface Clickable {
    fun click()
    fun showOff() = println("I'm clickable!")
}

interface Focusable {
    fun setFocus(b: Boolean) =
        println("I ${if (b) "got" else "lost"} focus.")
    fun showOff() = println("I'm focusable!")
}

interface Testable{
    fun funForTest() = println("Test is done!")
}

class Button: Clickable, Focusable, Testable {
    override fun click() = println("I was clicked.")

    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }
}

fun a_4_1_1_interfaces_in_kotlin(){
    Button().apply {
        click()
        showOff()
        setFocus(false)
        setFocus(true)
        funForTest()
    }

}