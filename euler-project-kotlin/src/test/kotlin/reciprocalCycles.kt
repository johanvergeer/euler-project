import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

fun cycleLength(denominator: Int): Int {
    var remainder = 1
    var recurringCycleLength = 0
    do {
        remainder = (remainder * 10) % denominator
        recurringCycleLength++
    } while (remainder != 1)
    return recurringCycleLength
}

class CycleLengthSpec : StringSpec({
    mapOf(
        3 to 1,
        7 to 6,
        9 to 1,
    ).forEach { (denominator, expectedCycleLength) ->
        "$denominator should have a cycle length of $expectedCycleLength" {
            cycleLength(denominator) shouldBe expectedCycleLength
        }
    }

    "integer with the longest recurring cycle under 1000 is 983" {
        primesUpTo(1000)
            .asSequence()
            .filterNot { (it % 2 == 0) or (it % 5 == 0) }
            .maxByOrNull { cycleLength(it) } shouldBe 983
    }
})