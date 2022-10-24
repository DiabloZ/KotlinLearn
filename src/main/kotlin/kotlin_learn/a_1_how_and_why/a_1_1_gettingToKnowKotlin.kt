package main.kotlin.kotlin_learn.a_1_how_and_why

fun a_1_1_ElvisNull() {
    val persons = listOf(
        Person("Alice"),
        Person("Bob", 29)
    )

    val oldest = persons.maxByOrNull { it.age ?: 0 }
    persons.printPersonList()
    println("The oldest is: $oldest")
}

data class Person(val name:String,
                    val age: Int? = null)

fun List<Person>.printPersonList(){
    this.forEach { person ->
        println("Person - ${person.name}, age - ${person.age ?: 0}")
    }
}