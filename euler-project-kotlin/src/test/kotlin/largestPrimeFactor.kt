import assertk.assertThat
import assertk.assertions.isEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.math.BigInteger

class LargestPrimeFactorSpek: Spek({
    describe("the number 600851475143") {
        it("has the largest prime factor of 6857") {
            val primes = primesOf(BigInteger.valueOf(600851475143))
            assertThat(primes.maxOrNull()).isEqualTo(6857.toBigInteger())
        }
    }
})