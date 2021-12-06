package euler.numberSpriralDiagonals

import sqared

enum class Direction {
    D,
    L,
    U,
    R
}

fun numberSpiral(size: Int): List<IntArray> {
    val collection = (0 until size).map { IntArray(size) }

    // Start in the center of the spiral
    var x = (size / 2)
    var y = (size / 2)

    var direction = Direction.R

    for (n in 1..(size.sqared())) {
        collection[y][x] = n

        if ((x == size - 1) and (y == 0)) break

        when (direction) {
            Direction.R -> {
                x++
                if (collection[y + 1][x] == 0) direction = Direction.D
            }
            Direction.D -> {
                y++
                if (collection[y][x - 1] == 0) direction = Direction.L
            }
            Direction.L -> {
                x--
                if (collection[y - 1][x] == 0) direction = Direction.U
            }
            Direction.U -> {
                y--
                if (collection[y][x + 1] == 0) direction = Direction.R
            }
        }
    }

    return collection
}

fun diagonalsSum(grid: List<IntArray>) : Int{
    var sum = 0

    for (i in grid.indices) {
        sum += grid[i][i]
        sum += grid[i][grid.size - i - 1]
    }

    sum -= grid[grid.size / 2][grid.size / 2]

    return sum
}

fun main() {
    print(diagonalsSum(numberSpiral(1001)))
}