import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.lang.IllegalArgumentException


private val numbers = mapOf<Int, String>(
    0 to "zero",
    1 to "one",
    2 to "two",
    3 to "three",
    4 to "four",
    5 to "five",
    6 to "six",
    7 to "seven",
    8 to "eight",
    9 to "nine",
    10 to "ten",
    11 to "eleven",
    12 to "twelve",
    13 to "thirteen",
    14 to "fourteen",
    15 to "fifteen",
    16 to "sixteen",
    17 to "seventeen",
    18 to "eighteen",
    19 to "nineteen",
    20 to "twenty",
    30 to "thirty",
    40 to "forty",
    50 to "fifty",
    60 to "sixty",
    70 to "seventy",
    80 to "eighty",
    90 to "ninety",
)

private fun Int.lastTwoDigitsValue(): Int {
    return this.toString().takeLast(2).toInt()
}

@kotlin.ExperimentalStdlibApi
private fun Int.firstDigitValue(): Int {
    return this.toString().first().digitToInt()
}

@kotlin.ExperimentalStdlibApi
private fun Int.lastDigitValue(): Int {
    return this.toString().last().digitToInt()
}


@kotlin.ExperimentalStdlibApi
private fun numberUnder100ToWords(number: Int): String {
    if ((number > 20) and (number % 10 > 0)) {
        return "${numbers[number.firstDigitValue() * 10]}-${numbers[number.lastDigitValue()]}"
    }

    return numbers.getOrElse(number) { throw IllegalArgumentException("$number could not be processed") }
}

@kotlin.ExperimentalStdlibApi
private fun hundredthsToWords(number: Int): String {
    return when {
        number < 100 -> numbers[number].orEmpty()
        number % 100 == 0 -> "${numbers[number.firstDigitValue()]} hundred"
        else -> "${numbers[number.firstDigitValue()]} hundred and ${numberUnder100ToWords(number.lastTwoDigitsValue())}"
    }
}


@kotlin.ExperimentalStdlibApi
fun numberToWords(number: Int): String {

    if (number >= 1000) {
        if (number % 1000 > 0) {
            return "${hundredthsToWords(number.firstDigitValue())} thousand ${
                hundredthsToWords(
                    number.toString().takeLast(3).toInt()
                )
            }"
        }
        return "${hundredthsToWords(number.firstDigitValue())} thousand"
    }

    if (number >= 100) {
        return hundredthsToWords(number)
    }

    return numberUnder100ToWords(number)
}


@kotlin.ExperimentalStdlibApi
class ConvertToWordsSpec : StringSpec({
    mapOf(
        0 to "zero",
        1 to "one",
        2 to "two",
        3 to "three",
        4 to "four",
        5 to "five",
        6 to "six",
        7 to "seven",
        8 to "eight",
        9 to "nine",
        10 to "ten",
        11 to "eleven",
        12 to "twelve",
        13 to "thirteen",
        14 to "fourteen",
        15 to "fifteen",
        16 to "sixteen",
        17 to "seventeen",
        18 to "eighteen",
        19 to "nineteen",
        20 to "twenty",
        21 to "twenty-one",
        22 to "twenty-two",
        30 to "thirty",
        31 to "thirty-one",
        40 to "forty",
        44 to "forty-four",
        50 to "fifty",
        55 to "fifty-five",
        60 to "sixty",
        66 to "sixty-six",
        70 to "seventy",
        77 to "seventy-seven",
        80 to "eighty",
        88 to "eighty-eight",
        90 to "ninety",
        99 to "ninety-nine",
        100 to "one hundred",
        101 to "one hundred and one",
        102 to "one hundred and two",
        111 to "one hundred and eleven",
        121 to "one hundred and twenty-one",
        200 to "two hundred",
        300 to "three hundred",
        333 to "three hundred and thirty-three",
        1000 to "one thousand",
        1100 to "one thousand one hundred",
        2000 to "two thousand",
        3333 to "three thousand three hundred and thirty-three",
    ).forEach { (input, expected) ->
        "$input should be written as $expected" {
            numberToWords(input) shouldBe expected
        }

    }
})

@kotlin.ExperimentalStdlibApi
fun numberLetterCount(number: Int): Int {
    return numberToWords(number)
        .toCharArray()
        .filter { it in 'a'..'z' }
        .count()
}

@kotlin.ExperimentalStdlibApi
class NumberLetterCountSpec : StringSpec({
    mapOf(
        342 to 23,
        115 to 20
    ).forEach { (input, expected) ->
        "$input should count $expected letters" {
            numberLetterCount(input) shouldBe expected
        }
    }

    "number of letters for all numbers from 1 until 1000 should be 21124" {
        (1..1000).sumBy { println(it); numberLetterCount(it) } shouldBe 21124
    }
})
