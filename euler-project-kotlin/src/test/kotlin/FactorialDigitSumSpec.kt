import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

@ExperimentalStdlibApi
class FactorialDigitSumSpec : StringSpec({
    "the factorial digit sum of 100 is 684" {
        val result = factorial(100)
            .toString()
            .sumOf(Char::digitToInt)

        result shouldBe 648
    }
})
