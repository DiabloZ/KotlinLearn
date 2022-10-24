package main.kotlin.kotlin_learn.a_4_create_class_hierarchy

open class RichButton: Clickable{
    final fun disable(){} //является финальным по умолчанию
    open fun animate(){} // можно переопределить
    final override fun click() {} //по умолчанию открыт, так как override, но с добавлением "final" становится закрытым.
}
abstract class Animated {
    abstract fun animate() //при наследовании будет переопределена, реализации до переопределение быть не может
    open fun stopAnimating(){} //функции по умолчанию закрыты, но можно сделать открытыми для изменений
    fun animateTwice(){} //функции по умолчанию закрыты, но можно сделать открытыми для изменений
}
fun a_4_1_2_modify_class_open_final_abstract() {
}