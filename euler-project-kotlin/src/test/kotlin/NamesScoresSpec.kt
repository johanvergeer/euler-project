import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

fun sumCharacterValues(text: String): Int {
    return text.map { it - 'A' + 1 }.sum()
}

class NamesScoresSpec : StringSpec({
    mapOf(
        "A" to 1,
        "B" to 2,
        "AB" to 3,
        "JOHAN" to 48,
    ).forEach { (text, value) ->
        "$text should have a total characters value of $value" {
            sumCharacterValues(text) shouldBe value
        }
    }


    "all names in the list together should have a value of 871198282" {
        val names = NamesScoresSpec::class.java.getResource("names.txt")
            .readText()
            .split(",")
            .map { it.trim('"') }
            .sorted()

        val result = names
            .mapIndexed { index, name -> sumCharacterValues(name) * (index + 1) }
            .sum()

        result shouldBe 871198282
    }

})