package main.kotlin

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.Hashtable
import java.util.LinkedList
import kotlin.concurrent.thread

val hashMap2 = linkedMapOf(Something("1") to 1L)

suspend fun main() {
    val lock = Any()
    val mutex = Mutex()
    val threadPool = mutableListOf<Thread>()
    val multyply = 50
    val s = 0..10_000_000L
    val chankes = s.chunked((s.max() / multyply).toInt())

    val assPool = mutableListOf<Deferred<Unit>>()
    repeat(multyply) { count ->
        val ass = CoroutineScope(Dispatchers.IO).async {
            for (l in chankes[count]) {
                    if (count == 0){
                        println(l)
                    }

                    hashMap2.put(Something(l.toString()), l)

            }
        }
        assPool.add(ass)
    }

    assPool.forEach { it.await() }
    hashMap2

}

data class Something(
    val some: String = ""
) {
    override fun hashCode(): Int {
        return 1
    }

    override fun equals(other: Any?): Boolean {
        return false
    }
}