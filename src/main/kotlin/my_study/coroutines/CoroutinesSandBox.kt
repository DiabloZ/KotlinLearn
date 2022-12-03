package main.kotlin.my_study.coroutines

import kotlinx.coroutines.*

class CoroutinesSandBox {
    suspend fun makeSandBox(){
        val dispatchIO = Dispatchers.IO
        val dispatchMain = Dispatchers.Main
        val dispatchUnconfined = Dispatchers.Unconfined
        val dispatchDefault = Dispatchers.Default
        testDelay()
        coroutine1()
    }
}

suspend fun testDelay(){
    for(i in 0..5){
        delay(400L)
        println(i)
    }
    println("Hello Coroutines testDelay()")
}

suspend fun coroutine1() = coroutineScope {
    launch {
        for(i in 0..5){
            delay(400L)
            println(i)
        }
        println("Hello Coroutines coroutine1()")
    }
}
