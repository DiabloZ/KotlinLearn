package main.kotlin.utils

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.*

@OptIn(DelicateCoroutinesApi::class)
class CoroutineTimer {

    private var count: Double = 0.0
    private var timerJob: Job? = null
    private var isPause = false
    private var timeTemp: Long = 0
    private var timeTickTemp: Long = 0
    private var tickListener: (time: Long) -> Unit = {}
    private var onCompleteListener: () -> Unit = {}

    private var startTime: Long? = null
    private val cacheMutex = Mutex()
    //private var startTime: AtomicLong? = null


    //private val dispatcher = newSingleThreadContext("TimerCoroutine")

    private val currentTime:Long
        get()= Date().time

    fun start(){
        if (timerJob?.isActive == false){
            timerJob?.start()
            isPause = false
        }
    }

    fun stop(){
        timerJob?.cancel()
    }

    fun stopAndRecreate(){
        stop()
        count = 0.0
        startTime = null
        tickListener(count.toLong())
        createTimer(timeTemp, timeTickTemp, tickListener, onCompleteListener)
    }

    fun pause(){
        isPause = true
    }

    fun setListeners(tick:(time: Long) -> Unit, onComplete: () -> Unit){
        tickListener = tick
        onCompleteListener = onComplete
    }

    fun createTimer(time: Long, timeTick: Long, tick:(time: Long) -> Unit, onComplete: () -> Unit) {
        timeTemp = time
        timeTickTemp = timeTick
        tickListener = tick
        onCompleteListener = onComplete
        timerJob = CoroutineScope(Dispatchers.Default).launch(start = CoroutineStart.LAZY) {
            calculateTime()
        }
    }

    private suspend fun calculateTime(
        time: Long = timeTemp,
        timeTick: Long= timeTickTemp,
        tick:(time: Long) -> Unit = tickListener,
        onComplete: () -> Unit = onCompleteListener
    ){
        cacheMutex.withLock {
            startTime = currentTime
            count = 0.0
            while (count < time){
                if (!isPause) {
                    val result = (count / time * 100).toLong()
                    tick(result)
                    count = currentTime.minus(startTime ?: 0).toDouble()
                    delay(timeTick)
                }
            }

            if (
                timerJob?.isActive == true &&
                timerJob?.isCancelled == false &&
                timerJob?.isCompleted == false &&
                startTime != null &&
                startTime != currentTime
            ){
                Logger.printText("", "!!! startTime - $startTime, currentTime - $currentTime")
                onComplete()
            }
        }
    }

    /*    fun deInitTimer(){
            dispatcher.close()
        }*/

}