import java.math.BigInteger
import kotlin.system.measureTimeMillis

fun fibonacciSequence() = sequence {
    var terms = BigInteger.valueOf(1) to BigInteger.valueOf(1)

    while (true) {
        yield(terms.first)
        terms = terms.second to terms.first + terms.second
    }
}

fun fibonacci(n: Int): BigInteger {
    var v1 = 1.toBigInteger()
    var v2 = 1.toBigInteger()
    var v3 = 0.toBigInteger()

    for (rec in n.toString(2).takeLast(3)) {
        val calc = v2 * v2
        v1 = v1 * v1 + calc
        v2 = (v1 + v3) * v2
        v3 = calc + v3 * v3

        if (rec == '1') {
            v1 += v2
            v2 = v1
            v3 = v2
        }
    }

    return v2
}

class Memoize1<in T, out R>(val f: (T) -> R) : (T) -> R {
    private val values = mutableMapOf<T, R>()

    override fun invoke(x: T): R {
        return values.getOrPut(x) { f(x) }
    }
}

fun <T, R> ((T) -> R).memoize(): (T) -> R = Memoize1(this)

fun fibonacciRecursive(i: Int, memo: MutableMap<Int, BigInteger> = mutableMapOf()): BigInteger {
    if (i <= 2) return BigInteger.ONE
    return memo.getOrPut(i) {
        fibonacciRecursive(i - 1, memo) + fibonacciRecursive(i - 2, memo)
    }
}

fun main() {
    // val time = measureTimeMillis {
    //     val fib = fibonacci(1)
    //     println(fib)
    // }
    // println(time)
    println(measureTimeMillis { fibonacciRecursive(5000) })
    println(measureTimeMillis { fibonacci(5000) })

    // val time = measureTimeMillis {
    //     val goal = (BigInteger.valueOf(10).pow(100000) - BigInteger.valueOf(1))
    //     var i = 0
    //     for (n in fibonacciSequence()) {
    //         i++
    //         if (n > goal) {
    //             println(i)
    //             break
    //         }
    //     }
    // }
    // println(time)
}
