import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.math.BigInteger

@kotlin.ExperimentalStdlibApi
class PowerDigitSumSpec: StringSpec({
    mapOf(
        15 to 26,
        1000 to 1366
    ).forEach { (power, expected) ->
        "the sum of the digits of the number 2^$power should be $expected" {
            val sum = 2.toBigInteger()
                .pow(power)
                .toString()
                .sumBy { it.digitToInt() }

            sum shouldBe expected
        }
    }


    "2^1000 should be ?" {
        2.toBigInteger().pow(1000) shouldBe BigInteger("10715086071862673209484250490600018105614048117055336074437503883703510511249361224931983788156958581275946729175531468251871452856923140435984577574698574803934567774824230985421074605062371141877954182153046474983581941267398767559165543946077062914571196477686542167660429831652624386837205668069376")
    }
})