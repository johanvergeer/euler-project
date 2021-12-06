import java.math.BigInteger

fun main() {
    val distinctPowers = mutableSetOf<BigInteger>()

    for (a in 2..100) {
        for (b in 2..100)
            distinctPowers.add(a.toBigInteger().pow(b))
    }

    println(distinctPowers.size)
}