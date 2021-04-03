import assertk.assertThat
import assertk.assertions.isEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
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

class SmallestMultipleSpek : Spek({
    describe("when searching for the smallest number evenly divisible by all of the numbers from 1 to 20") {
        it("will be 232792560") {
            val expected = 232792560

            assertThat(smallestMultiple(20)).isEqualTo(expected)
        }
    }
})
