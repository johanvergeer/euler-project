fun quadratic(n: Int, a: Int, b: Int): Int {
    return n * n + a * n + b
}

data class Result(val a: Int, val b: Int, val n: Int)

fun main() {
    val results = mutableListOf<Result>()

    for (a in -1000 until 1000) {
        for (b in -1000..1000) {
            var n = 0
            while (true) {
                if (!quadratic(n, a, b).isPrime()) break
                n++
            }
            results.add(Result(a, b, n))
        }
    }

    println(results.maxByOrNull { it.n })

    results
        .maxByOrNull { it.n }
        .also { print(it!!.a * it.b) }
}