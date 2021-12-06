fun Long.isEven() = this % 2 == 0L
fun Long.isOdd() = !this.isEven()

fun Int.sqared() = this * this