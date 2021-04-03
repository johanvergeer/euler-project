import java.math.BigInteger

fun primesOf(input: Int): List<Int> {
    var remainder = input
    val factors = mutableListOf<Int>()
    var divisor = 2

    while (remainder > 1){
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

    while (remainder > BigInteger.ONE){
        while (remainder % divisor == BigInteger.ZERO) {
            factors.add(divisor)
            remainder /= divisor
        }
        divisor++
    }
    return factors
}
