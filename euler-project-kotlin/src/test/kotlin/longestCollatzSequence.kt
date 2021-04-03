import kotlin.system.measureTimeMillis

interface Collatz {
    fun countChain(n: Long): Int
}


class CollatzNaive : Collatz {
    private fun collatzSequence(startsAt: Long): List<Long> {
        var currentValue = startsAt
        val sequence = mutableListOf(startsAt)

        while (currentValue != 1L) {
            if (currentValue % 2 == 0L) currentValue /= 2
            else currentValue = 3 * currentValue + 1

            try {
                sequence.add(currentValue)
            } catch (e: OutOfMemoryError) {
                println(currentValue)
            }
        }

        return sequence
    }

    override fun countChain(n: Long): Int {
        return this.collatzSequence(n).size
    }
}

class CollatzEfficient : Collatz {
    private val values = mutableMapOf(1L to 1)

    override fun countChain(n: Long): Int {
        val existingValue = this.values.getOrDefault(n, null)
        if (existingValue != null) {
            return existingValue
        }

        val value =
            if (n.isEven()) 1 + countChain(n / 2)
            else 2 + countChain((3 * n + 1) / 2)

        values[n] = value
        return value
    }
}

fun getNumberWithLongestSequenceCount(collatz: Collatz): Long {
    var longestSequenceCount = 0
    var numberWithLongestSequence = 0L

    for (it in 500_000L..1_000_000L) {
        val sequenceSize = collatz.countChain(it)
        if (sequenceSize > longestSequenceCount) {
            longestSequenceCount = sequenceSize
            numberWithLongestSequence = it
        }
    }

    return numberWithLongestSequence
}

fun main() {
    val collatzNaive = CollatzNaive()
    val timeNaive = measureTimeMillis {
        println(getNumberWithLongestSequenceCount(collatzNaive))
    }
    println(timeNaive)

    val collatzEfficient = CollatzEfficient()
    val timeEfficient = measureTimeMillis {
        getNumberWithLongestSequenceCount(collatzEfficient)
    }
    println(timeEfficient)
}