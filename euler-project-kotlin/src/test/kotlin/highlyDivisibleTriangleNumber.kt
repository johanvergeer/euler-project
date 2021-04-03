import java.lang.RuntimeException

fun main() {
    var number = 1
    var triangleNumber = 0
    while (true) {
        triangleNumber += number
        number++

        if (triangleNumber < 0) throw RuntimeException("triangle out of bounds for an Int")

        val primes = primesOf(triangleNumber)
        // https://www.quora.com/What-is-the-fastest-algorithm-to-get-the-number-of-divisors-of-number-x-limits-up-to-10-12
        val primesCount = primes.countOccurrences().values.map { it + 1 }

        if (primesCount.isEmpty()) continue
        else {
            val divisorsCount = primesCount.reduce { acc, i -> acc * i }

            val requiredDivisors = 500
            if (divisorsCount > requiredDivisors) {
                println("$triangleNumber has over $requiredDivisors divisors")
                return
            }
        }
    }
}