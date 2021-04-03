import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isTrue
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.math.pow

class IsPythagoreanTripletSpek : Spek({
    describe("a pythagorean triplet") {
        it("returns true") {
            assertThat(Triple(3, 4, 5).isPythagoreanTriplet()).isTrue()
        }
    }
    describe("a sum") {
        it("should have a triplet") {
            assertThat(pythagoreanTriplet(1000)?.prod()).isEqualTo(31875000)
        }
    }
})

