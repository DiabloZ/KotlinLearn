package main.kotlin.my_study.coroutines

import kotlinx.coroutines.*
import main.kotlin.utils.Logger

class CoroutinesSandBox {

    val logger = Logger
    val duration = 300L
    val durationSecond = 1000L
    val sayHello = "Hello Coroutines"
    suspend fun makeSandBox() = coroutineScope {
        val name = object : Any() {}.javaClass.enclosingMethod.name
        val dispatchIO = Dispatchers.IO
        val dispatchMain = Dispatchers.Main
        val dispatchUnconfined = Dispatchers.Unconfined
        val dispatchDefault = Dispatchers.Default
/*        println("||||||||||||||||TEST||||||||||||||||")
        testDelay()
        println("||||||||||||||||COROUTINE UNIT||||||||||||||||")
        coroutine1()
        println("||||||||||||||||PARALLEL LAUNCH||||||||||||||||")
        launch { doWork() } //Параллельная задача
        printText(name, sayHello)*/
/*        println("||||||||||||||||SEVERAL COROUTINES LAUNCH||||||||||||||||")
        severalCoroutinesLaunch()
        println("||||||||||||||||RUN BLOCKING LAUNCH||||||||||||||||")
        runBlockingTest() //Приостанавливает поток, потом возвращается в него уже в контексте run blocking-а
        printText(name, "this text will be after run block in run block context")*/
/*        println("|||||||||||||||NESTED COROUTINE|||||||||||||||||")
        nestedCoroutine()*/
/*        println("|||||||||||||||Job|||||||||||||||||")
        jobJoin()*/
/*        println("|||||||||||||||Job suspend run|||||||||||||||||")
        jobJoinSuspendRun()*/
/*        println("|||||||||||||||Async call|||||||||||||||||")
        asyncCall()*/
/*        println("|||||||||||||||Async deferred call|||||||||||||||||")
        asyncGetDifferedValue()*/
/*        logger.printText(name,"|||||||||||||||Async get several deferred call|||||||||||||||||")
        asyncGetSeveralDifferedValues()*/
/*        logger.printText(name,"|||||||||||||||Async suspend run|||||||||||||||||")
        asyncSuspendRun()*/
/*        logger.printText(name,"|||||||||||||||Cancel coroutine|||||||||||||||||")
        cancelWorkOfCoroutine()*/
/*        logger.printText(name,"|||||||||||||||Handle cancellation exception|||||||||||||||||")
        handleCancellationException()*/
        logger.printText(name,"|||||||||||||||Handle async cancellation exception|||||||||||||||||")
        handleAsyncCancellationException()
    }

    private suspend fun testDelay(){
        val name = object : Any() {}.javaClass.enclosingMethod.name
        for(i in 0..5){
            delay(duration)
            logger.printText(name, i)
        }

        logger.printText(name,sayHello)
    }

    //Типизированная карутина, если не типизировать, возвращает Job
    private suspend fun coroutine1() = coroutineScope<Unit> {
        val name = object : Any() {}.javaClass.enclosingMethod.name
        launch {
            for(i in 0..5){
                delay(duration)
                logger.printText(name, i)
            }
        }
    }

    private suspend fun doWork(){
        val name = object : Any() {}.javaClass.enclosingMethod.name
        for(i in 0..5){
            logger.printText(name, i)
            delay(duration)
        }
    }

    private suspend fun severalCoroutinesLaunch() = coroutineScope {
        val name = object : Any() {}.javaClass.enclosingMethod.name
        launch {
            for (i in 0..5){
                delay(duration)
                logger.printText(name, i)
            }
        }
        launch {
            for (i in 6..10){
                delay(duration)
                logger.printText(name, i)
            }
        }

        logger.printText(name, sayHello)
    }

    private fun runBlockingTest() = runBlocking {
        val name = object : Any() {}.javaClass.enclosingMethod.name
        launch {
            for (i in 0..5){
                delay(duration)
                logger.printText(name, i)
            }
        }
        logger.printText(name, sayHello)
    }

    private suspend fun nestedCoroutine() = coroutineScope {
        val name = object : Any() {}.javaClass.enclosingMethod.name
        launch {
            logger.printText(name, "Outer coroutine")
            launch {
                logger.printText(name, "Inner coroutine")
                delay(duration)
            }
        }
        println("End of function")
    }

    private suspend fun jobJoin() = coroutineScope {
        val name = object : Any() {}.javaClass.enclosingMethod.name
        val job = launch {
            for (i in 1..5){
                logger.printText(name, i)
                delay(duration)
            }
        }
        logger.printText(name, "Start")
        job.join()
        logger.printText(name, "Stop")
    }

    private suspend fun jobJoinSuspendRun() = coroutineScope {
        val name = object : Any() {}.javaClass.enclosingMethod.name
        val job = launch(start = CoroutineStart.LAZY) {//Корутина создана, но не запущена, благодаря флажку
            delay(duration)
            logger.printText(name, "Coroutine has started")
        }

        delay(durationSecond)
        job.start()
        logger.printText(name, "Other action in main method")
    }

    private suspend fun asyncCall() = coroutineScope {
        val name = object : Any() {}.javaClass.enclosingMethod.name
        suspend fun someWork() {
            delay(duration)
            logger.printText(name, "Some work!")
        }
        async { someWork() }
        logger.printText(name, "Program has finished")
    }

    private suspend fun asyncGetDifferedValue() = coroutineScope {
        val name = object : Any() {}.javaClass.enclosingMethod.name
        suspend fun getMessage() : String {
            delay(duration)
            return "Some message!"
        }

        val message: Deferred<String> = async { getMessage() }
        logger.printText(name, "message: ${message.await()}")
        logger.printText(name, "Program has finished")

    }

    private suspend fun asyncGetSeveralDifferedValues() = coroutineScope {
        val name = object : Any() {}.javaClass.enclosingMethod.name
        suspend fun sum (a: Int, b: Int): Int{
            delay(durationSecond)
            return a + b
        }

        val numDeffered1 = async { sum(1, 2) }
        val numDeffered2 = async { sum(3, 4) }
        val numDeffered3 = async { sum(5, 6) }

        val num1 = numDeffered1.await()
        val num2 = numDeffered2.await()
        val num3 = numDeffered3.await()

        logger.printText(name, "number1: $num1, number2: $num2, number3: $num3")

    }

    private suspend fun asyncSuspendRun() = coroutineScope {
        val name = object : Any() {}.javaClass.enclosingMethod.name
        suspend fun sum(a: Int, b: Int): Int{
            logger.printText(name, "Coroutine has started")
            delay(durationSecond)
            return a + b
        }

        val sum = async (start = CoroutineStart.LAZY) { sum (1,2)}
        val sum2 = async (start = CoroutineStart.LAZY) { sum (3,4)}
        delay(durationSecond)
        sum.start() //будет тут вычеслена
        logger.printText(name, "sum: ${sum.await()}") // а тут будет отдана
        logger.printText(name, "sum2: ${sum2.await()}") // тут будет вычеслена и сразу отдастся

    }

    private suspend fun cancelWorkOfCoroutine() = coroutineScope {
        val name = object : Any() {}.javaClass.enclosingMethod.name
        val downloader: Job = launch {
            logger.printText(name, "Начинаем загрузку файлов")
            for (i in 1..10){
                logger.printText(name, "Загружен файл $i")
                delay(duration)
            }
        }
        delay(durationSecond) // установим задержку, чтобы загрузились несколько файлов
        logger.printText(name, "Надоело ждать, пока все файлы загрузятся. Прерву-ка я загрузку...")
        downloader.cancel() //отменяем корутину
        downloader.join() // ожидаем завершения корутины
        downloader.cancelAndJoin() // или так, объединяем эти два метода
        logger.printText(name, "Работа программы завершена")
    }

    private suspend fun handleCancellationException() = coroutineScope {
        val name = object : Any() {}.javaClass.enclosingMethod.name
        val downloader: Job = launch {
            try {
                logger.printText(name, "Начинаем загрузку файлов")
                for (i in 1..5){
                    logger.printText(name, "Загружен файл $i")
                    delay(durationSecond)
                }
            } catch (e: CancellationException){
                logger.printText(name, "Загрузка файлов прервана")
            }
            finally {
                logger.printText(name, "Загрузка завершена")
            }
        }

        delay(durationSecond)
        logger.printText(name, "Надоело ждать. Прерву-ка я загрузку...")
        downloader.cancelAndJoin()
        logger.printText(name, "Работа программы завершена")
    }

    private suspend fun handleAsyncCancellationException() = coroutineScope {
        val name = object : Any() {}.javaClass.enclosingMethod.name
        suspend fun getMessage(): String {
            delay(duration * 2)
            return "Hello"
        }
        val message = async {
            getMessage()
        }

        message.cancelAndJoin()

        try {
            logger.printText(name, "Сообщение - ${message.await()}")

        } catch (e: CancellationException){
            logger.printText(name, "Загрузка файлов прервана")
        }
        logger.printText(name, "Работа программы завершена")
    }

}