import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.math.BigInteger

class LargestPrimeFactorsSpec : StringSpec({
    "the number 600851475143 should have the largest prime factor of 6857" {
        primesOf(BigInteger.valueOf(600851475143)).maxOrNull() shouldBe 6857.toBigInteger()
    }
})
