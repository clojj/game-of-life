import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

val e = 10

suspend fun <A, B> Iterable<A>.pmap(f: suspend (A) -> B): List<B> = coroutineScope {
    map { async { f(it) } }.awaitAll()
}
fun main(args: Array<String>) = runBlocking {
    val time = measureTimeMillis {
        val output = (1..1000).pmap {
            delay(1000)
            it * 2
        }

        println(output)
    }

    println("Total time: $time")
}