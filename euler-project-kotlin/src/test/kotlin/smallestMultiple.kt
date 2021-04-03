import assertk.assertThat
import assertk.assertions.isEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class SmallestMultipleSpek: Spek({
    describe("when searching for the smallest number evenly divisible by all of the numbers from 1 to 20") {
        it("will be 232792560") {
            val expected = 232792560

            var i = 1
            while (true){
                if ((2..20).all { i % it == 0 }) break
                i ++
            }

            assertThat(i).isEqualTo(expected)
        }
    }
})