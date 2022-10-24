package main.kotlin.kotlin_learn.a_3_defining_functions.a_3_3_added_method_in_class

fun a_3_3_4_ExtensionsNotOverride() {
    val view: View = Button()
    view.click()
    view.showOff()

    Button().showOff()
}

open class View{
    open fun click() = println("View clicked")
}
class Button: View(){
    override fun click() = println("Button clicked")
}

fun View.showOff() = println("I'm a view!")
fun Button.showOff() = println("I'm a button!")