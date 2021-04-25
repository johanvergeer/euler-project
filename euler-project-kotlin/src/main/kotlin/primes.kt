import java.math.BigInteger

fun primesOf(input: Int): List<Int> {
    var remainder = input
    val factors = mutableListOf<Int>()
    var divisor = 2

    while (remainder > 1) {
        while (remainder % divisor == 0) {
            factors.add(divisor)
            remainder /= divisor
        }
        divisor++
    }
    return factors
}

fun primesOf(input: BigInteger): List<BigInteger> {
    var remainder = input
    val factors = mutableListOf<BigInteger>()
    var divisor = BigInteger.TWO

    while (remainder > BigInteger.ONE) {
        while (remainder % divisor == BigInteger.ZERO) {
            factors.add(divisor)
            remainder /= divisor
        }
        divisor++
    }
    return factors
}

fun Int.isPrime(): Boolean {
    if (this <= 3) return this > 1
    if ((this % 2 == 0) or (this % 3 == 0)) return false

    var i = 5
    while (i.pow(2) <= this) {
        if ((this % i == 0) or (this % (i + 2) == 0)) {
            return false
        }
        i += 6
    }

    return true
}

fun primesUpTo(upTo: Int) = (0..upTo).filter { it.isPrime() }

fun summationOfPrimes(upTo: Int) = primesUpTo(upTo)
    .map { it.toBigInteger() }
    .sumOf { it }

