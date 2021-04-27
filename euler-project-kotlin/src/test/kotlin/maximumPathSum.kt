import kotlin.math.max

fun getResourceAsText(path: String): String {
    return object {}.javaClass.getResource(path).readText()
}

fun loadTriangle(fileName: String): List<MutableList<Int>> {
    return getResourceAsText(fileName)
        .split("\n")
        .map { row ->
            row.split(" ")
                .map(String::toInt)
                .toMutableList()
        }
}

fun maximumPathSum(triangle: List<MutableList<Int>>): Int {
    for (row in triangle.size - 2 downTo 0) {
        for (item in 0 until triangle[row].size) {
            triangle[row][item] += max(triangle[row + 1][item], triangle[row + 1][item + 1])
        }
    }

    return triangle[0][0]
}

fun main() {
    println(maximumPathSum(loadTriangle("maximum-path-sum-2.txt")))
}
