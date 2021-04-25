import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe


class PrimesSpec : StringSpec({
    mapOf(
        1 to listOf(),
        2 to listOf(2),
        3 to listOf(3),
        4 to listOf(2, 2),
        5 to listOf(5),
        6 to listOf(2, 3),
        7 to listOf(7),
        8 to listOf(2, 2, 2),
        9 to listOf(3, 3),
        2 * 2 * 2 * 3 * 5 * 7 * 11 to listOf(2, 2, 2, 3, 5, 7, 11),
    ).forEach { (input, expected) ->
        "$input should have the prime numbers $expected" {
            primesOf(input) shouldBe expected
        }
    }
})

class IsPrimeSpec : BehaviorSpec({
    val primeNumbers = IsPrimeSpec::class.java.getResource("/primes-to-100k.txt")
        .readText()
        .split("\n")
        .mapNotNull { it.trim().toIntOrNull() }
    val nonPrimeNumbers = (0..100_000).filterNot { it in primeNumbers }

    given("a set of prime numbers up to ${primeNumbers.maxOrNull()}") {
        When("checking whether all numbers are primes") {
            then("the return value for each number should be true") {
                primeNumbers.forEach {
                    it.isPrime() shouldBe true
                }
            }
        }
    }

    given("a set of non-prime numbers up to ${nonPrimeNumbers.maxOrNull()}") {
        When("checking whether all numbers are primes") {
            then("the return value for each number should be false") {
                nonPrimeNumbers.forEach {
                    it.isPrime() shouldBe false
                }
            }
        }
    }
})

class SummationOfPrimesSpec : StringSpec({
    "the sum of all prime numbers up to 2.000.000 should be 142913828922" {
        summationOfPrimes(2_000_000) shouldBe 142913828922.toBigInteger()
    }
})
