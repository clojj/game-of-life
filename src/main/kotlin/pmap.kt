import kotlinx.coroutines.*
import java.math.BigInteger
import java.util.*
import kotlin.system.measureTimeMillis

suspend fun <A, B> Iterable<A>.pmap(f: suspend (A) -> B): List<B> = coroutineScope {
    map { async(Dispatchers.Default) { f(it) } }.awaitAll()
}

suspend fun <A, B> Iterable<A>.pmapChunked(f: suspend (A) -> B): List<B> = coroutineScope {
    val deferred = chunked(5).map { async(Dispatchers.Default) { it.map { f(it) } } }
    val all = deferred.awaitAll()
    all.flatten()
}

fun main(args: Array<String>) = runBlocking {
    val time = measureTimeMillis {
        val output = (1..100).pmapChunked {
            val nextProbablePrime = BigInteger(1000, Random()).nextProbablePrime()
            "$it $nextProbablePrime"
        }
    }
    println("Total time: $time")
}