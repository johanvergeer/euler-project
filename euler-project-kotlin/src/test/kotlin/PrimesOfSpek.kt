import assertk.assertThat
import assertk.assertions.isEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.math.BigInteger

class PrimesOfSpek : Spek({
    val values = mapOf(
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
    )

    values.forEach { (input, expected) ->
        describe("the value $input") {
            it("has the prime numbers $expected") {
                assertThat(primesOf(input)).isEqualTo(expected)
            }
        }
    }

    values.forEach { (input, expected) ->
        describe("the BigInteger value $input") {
            it("has the prime numbers $expected") {
                assertThat(primesOf(input.toBigInteger())).isEqualTo(expected.map { it.toBigInteger() })
            }
        }
    }
})