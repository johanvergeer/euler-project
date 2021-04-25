import io.kotest.core.Tuple2
import io.kotest.core.Tuple3
import io.kotest.core.datatest.forAll
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

fun createGrid(): List<List<Int>> {
    val grid = """08 02 22 97 38 15 00 40 00 75 04 05 07 78 52 12 50 77 91 08
49 49 99 40 17 81 18 57 60 87 17 40 98 43 69 48 04 56 62 00
81 49 31 73 55 79 14 29 93 71 40 67 53 88 30 03 49 13 36 65
52 70 95 23 04 60 11 42 69 24 68 56 01 32 56 71 37 02 36 91
22 31 16 71 51 67 63 89 41 92 36 54 22 40 40 28 66 33 13 80
24 47 32 60 99 03 45 02 44 75 33 53 78 36 84 20 35 17 12 50
32 98 81 28 64 23 67 10 26 38 40 67 59 54 70 66 18 38 64 70
67 26 20 68 02 62 12 20 95 63 94 39 63 08 40 91 66 49 94 21
24 55 58 05 66 73 99 26 97 17 78 78 96 83 14 88 34 89 63 72
21 36 23 09 75 00 76 44 20 45 35 14 00 61 33 97 34 31 33 95
78 17 53 28 22 75 31 67 15 94 03 80 04 62 16 14 09 53 56 92
16 39 05 42 96 35 31 47 55 58 88 24 00 17 54 24 36 29 85 57
86 56 00 48 35 71 89 07 05 44 44 37 44 60 21 58 51 54 17 58
19 80 81 68 05 94 47 69 28 73 92 13 86 52 17 77 04 89 55 40
04 52 08 83 97 35 99 16 07 97 57 32 16 26 26 79 33 27 98 66
88 36 68 87 57 62 20 72 03 46 33 67 46 55 12 32 63 93 53 69
04 42 16 73 38 25 39 11 24 94 72 18 08 46 29 32 40 62 76 36
20 69 36 41 72 30 23 88 34 62 99 69 82 67 59 85 74 04 36 16
20 73 35 29 78 31 90 01 74 31 49 71 48 86 81 16 23 57 05 54
01 70 54 71 83 51 54 69 16 92 33 48 61 43 52 01 89 19 67 48"""

    return grid
        .split("\n")
        .map { row ->
            row.split(" ")
                .map { value -> value.toInt() }
        }
}

class CreateGridSpec : StringSpec({
    "the input grid is split into a list of lists" {
        val grid = createGrid()

        grid shouldHaveSize 20
        grid[0] shouldHaveSize 20
        grid[0][0] shouldBe 8
    }
})

enum class Direction(
    val incrementRowBy: Int = 0,
    val incrementColumnBy: Int = 0
) {
    HORIZONTAL(incrementColumnBy = 1),
    VERTICAL(incrementRowBy = 1),
    DIAGONAL_FORWARD(incrementRowBy = 1, incrementColumnBy = -1),
    DIAGONAL_BACKWARD(incrementRowBy = 1, incrementColumnBy = 1)
}

data class Coordinate(val x: Int, val y: Int)

/**
 * Returns all possible grid coordinates where a group can be found of size [groupSize] for the given [direction]
 */
private fun getCoordinates(grid: List<List<Int>>, direction: Direction, groupSize: Int = 4): List<Coordinate> {
    val endRow = grid.size - direction.incrementRowBy * (groupSize - 1)
    val endColumn =
        if (direction.incrementColumnBy > 0) grid.size - direction.incrementColumnBy * (groupSize - 1)
        else grid.size
    val startColumn =
        if (direction.incrementColumnBy < 0) direction.incrementColumnBy * -(groupSize - 1)
        else 0

    return (0 until endRow).flatMap { row ->
        (startColumn until endColumn).map { column ->
            Coordinate(row, column)
        }
    }
}

fun getAdjacentNumbers(grid: List<List<Int>>, direction: Direction, groupSize: Int = 4): List<List<Int>> {

    val coordinates = getCoordinates(grid, direction, groupSize)

    return coordinates.map { coordinate ->
        (0 until groupSize).map {
            grid[coordinate.x + it * direction.incrementRowBy][coordinate.y + it * direction.incrementColumnBy]
        }
    }
}

class GetAdjacentNumbersSpec : BehaviorSpec({
    given("the original 20x20 grid") {
        val grid = createGrid()

        forAll(
            Tuple3(Direction.HORIZONTAL, listOf(8, 2, 22, 97), listOf(89, 19, 67, 48)),
            Tuple3(Direction.VERTICAL, listOf(8, 49, 81, 52), listOf(36, 16, 54, 48)),
            Tuple3(Direction.DIAGONAL_FORWARD, listOf(97, 99, 49, 52), listOf(36, 36, 57, 89)),
            Tuple3(Direction.DIAGONAL_BACKWARD, listOf(8, 49, 31, 23), listOf(40, 4, 5, 48)),
        ) { (direction, expectedFirstNumbers, expectedLastNumbers) ->
            When("retrieving ${direction.name} groups") {
                val numbers = getAdjacentNumbers(grid, direction)

                then("the first set of items in the groups should be $expectedFirstNumbers") {
                    numbers.first() shouldBe expectedFirstNumbers
                }
                then("the last set of items in the groups should be $expectedLastNumbers") {
                    numbers.last() shouldBe expectedLastNumbers
                }
            }
        }
    }

    given("a 5x5 grid") {
        val grid = createGrid().slice(0..4).map { it.slice(0..4) }

        and("size of each group is equal to the grid size") {
            val groupSize = grid.size

            forAll(
                Tuple2(Direction.HORIZONTAL, 5),
                Tuple2(Direction.VERTICAL, 5),
                Tuple2(Direction.DIAGONAL_FORWARD, 1),
                Tuple2(Direction.DIAGONAL_BACKWARD, 1),
            ) { (direction, expectedGroupCount) ->
                When("retrieving ${direction.name} groups") {
                    val numbers = getAdjacentNumbers(grid, direction, groupSize)

                    then("$expectedGroupCount groups should be found") {
                        numbers.size shouldBe expectedGroupCount
                        numbers.forEach { it.size shouldBe groupSize }
                    }
                }
            }
        }
    }
})

class GetHighestAdjacentValuesSpec : StringSpec({
    "the largest product of all groups in the grid should be 70600674" {
        val grid = createGrid()

        val allValues = Direction.values().flatMap { direction -> getAdjacentNumbers(grid, direction) }
        val highestValue = allValues.map { it.reduce { acc, i -> acc * i } }.maxOrNull()

        highestValue shouldBe 70600674
    }
})
