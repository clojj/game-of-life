import gol.*
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.maps.shouldContainAll
import io.kotest.matchers.shouldBe

class GolTests : StringSpec({

    "neighborCounts of a single cell" {
        val cell = Cell(1, 1)
        val cellNeighbors = neighbors(cell).map { Pair(it, 1) }.toMap()
        neighborCounts(World(setOf(cell))) shouldContainAll cellNeighbors
    }

    "neighbor cells" {
        forAll(
            row(42, 42),
            row(0, 0),
            row(-1, -1)
        ) { x, y ->
            neighbors(Cell(x, y)) shouldBe setOf(
                Cell(x, y + 1),
                Cell(x, y - 1),
                Cell(x + 1, y),
                Cell(x - 1, y),
                Cell(x + 1, y + 1),
                Cell(x - 1, y + 1),
                Cell(x + 1, y - 1),
                Cell(x - 1, y - 1)
            )

        }
    }

    "blinker" {
        val world1 = World(setOf(Cell(1, 1), Cell(1, 2), Cell(1, 3)))
        val world2 = World(setOf(Cell(0, 2), Cell(1, 2), Cell(2, 2)))
        val world3 = World(setOf(Cell(1, 1), Cell(1, 2), Cell(1, 3)))
        val expected = listOf(world1, world2, world3)
        golSequence(world1).take(3).toList() shouldBe expected
    }
})