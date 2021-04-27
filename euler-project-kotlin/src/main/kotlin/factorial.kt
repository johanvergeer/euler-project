import java.math.BigInteger

fun factorial(number: Int): BigInteger {
    return (1..number)
        .map { it.toBigInteger() }
        .reduce { acc, bigInteger -> acc * bigInteger }
}
