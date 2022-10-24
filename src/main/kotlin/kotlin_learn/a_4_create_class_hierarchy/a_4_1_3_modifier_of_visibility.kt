package main.kotlin.kotlin_learn.a_4_create_class_hierarchy

internal open class TalkativeButton {
    internal fun letsRun() = println("Hey! Let's run!")
    private fun yell() = println("Hey!")
    protected fun wisper() = println("Let's talk!")
}

/*fun TalkativeButton.giveSpeech() { //будет ошибка, публичный член класса раскрывает внутренний
    letsRun() // будет запущена, но эта функция обречена
    yell() //будет недоступна, так как объявлена как private
    wisper() //будет недоступна так как protect
}*/

fun a_4_1_3_modifier_of_visibility() {
}