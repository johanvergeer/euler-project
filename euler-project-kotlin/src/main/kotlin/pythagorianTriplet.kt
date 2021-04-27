import kotlin.math.pow

fun Int.pow(n: Int): Int = toDouble().pow(n).toInt()

fun (Triple<Int, Int, Int>).prod(): Int = first * second * third

fun Triple<Int, Int, Int>.isPythagoreanTriplet(): Boolean = first.pow(2) + second.pow(2) == third.pow(2)

fun Triple<Int, Int, Int>.sumEquals(sum: Int) = (first + second + third) == sum

fun pythagoreanTriplet(sum: Int): Triple<Int, Int, Int>? {
    (0..sum).forEach { c ->
        (0..c).forEach { b ->
            (0..b).forEach { a ->
                val triplet = Triple(a, b, c)
                if ((triplet.isPythagoreanTriplet() and triplet.sumEquals(1000))) {
                    return triplet
                }
            }
        }
    }
    return null
}

fun main() {
    println(pythagoreanTriplet(1000)?.prod())
}
