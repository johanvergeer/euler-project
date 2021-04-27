import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlin.math.floor
import kotlin.math.sqrt

fun amicableNumbersOf(number: Int): Set<Int> {

    val numbers = mutableSetOf(1)

    for (i in 2 until floor(sqrt(number.toDouble())).toInt()) {

        if (i in numbers) continue

        for (j in i + 1 until number) {
            if (j in numbers) continue

            if (i * j == number) {
                numbers.addAll(listOf(i, j))
            }
        }
    }

    return numbers.toSet()
}

class AmicableNumbersSpec : StringSpec({
    mapOf(
        220 to 284,
        284 to 220,
    )
        .forEach { (input, expected) ->
            "the sum of all amicable numbers of $input is $expected" {
                amicableNumbersOf(input).sum() shouldBe expected
            }
        }
})

suspend fun <A, B> Iterable<A>.pmap(f: suspend (A) -> B): List<B> = coroutineScope {
    map { async { f(it) } }.awaitAll()
}

fun amicableNumberPairs(upto: Int) = runBlocking(Dispatchers.Default) {
    (1..upto).pmap { it to amicableNumbersOf(it).sum() }
}

class SumOfAmicableNumbers : StringSpec({
    "The sum of all amicable numbers under 10000 should be 31626" {
        val amicableNumbersPairs = amicableNumberPairs(10000)
            .toMap()
            .filter { it.value > 1 }
            .filter { it.value != it.key }

        val matches = mutableMapOf<Int, Int>()

        for (pair in amicableNumbersPairs) {
            if ((amicableNumbersPairs.containsKey(pair.value)) and
                (amicableNumbersPairs[pair.value] == pair.key)
            )
                matches[pair.key] = pair.value
        }

        matches.keys.sum() shouldBe 31626
    }
})
