import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import kotlin.math.*

fun smallestMultipleNaiveApproach(): Int {
    var i = 1
    while (true) {
        if ((2..20).all { i % it == 0 }) return i
        i++
    }
}

private fun exponent(prime: Int, limit: Float, of: Int): Int {
    return if (prime <= limit) {
        floor(log2(of.toFloat()) / log2(prime.toFloat())).toInt()
    } else {
        1
    }
}

/**
 * Optimized version based on https://projecteuler.net/overview=005
 */
fun smallestMultiple(of: Int): Int {
    val limit = sqrt(of.toFloat())

    return (listOf(1) + primesUpTo(of))
        .reduce { multiple, prime ->
            multiple * prime.pow(exponent(prime, limit, of))
        }
}

class SmallestMultipleSpec: StringSpec({
    "the smallest number evenly divisible by all of the numbers from 1 to 20 should be 232792560" {
        smallestMultiple(20) shouldBe 232792560
    }
})
