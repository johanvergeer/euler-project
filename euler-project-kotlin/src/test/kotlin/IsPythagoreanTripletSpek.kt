import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class IsPythagoreanTripletSpek : StringSpec({
    "${Triple(3, 4, 5)} should be a Pythagorean triplet" {
        Triple(3, 4, 5).isPythagoreanTriplet() shouldBe true
    }

    "The product of the Pythagorean triplet of 1000 should be 31875000" {
        pythagoreanTriplet(1000)?.prod() shouldBe 31875000
    }
})
