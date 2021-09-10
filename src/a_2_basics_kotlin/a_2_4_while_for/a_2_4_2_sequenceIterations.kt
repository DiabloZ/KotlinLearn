package a_2_basics_kotlin.a_2_4_while_for

fun a_2_4_2_SequenceIterations() {
    for (i in 1..100){
        print(fizzBuzz(i))
    }
    print("\n\n")
    for(i in 100 downTo 1 step 2){
        print(fizzBuzz(i))
    }
    print("\n\n")


    fun fifthExampleForLoop() {









    }
}

fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz "
    i % 3 == 0 -> "Fizz "
    i % 5 == 0 -> "Buzz "
    else -> "$i "
}