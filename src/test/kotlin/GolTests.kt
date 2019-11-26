import gol.Cell
import gol.World
import gol.neighborCounts
import gol.neighbors
import io.kotlintest.data.forall
import io.kotlintest.matchers.maps.shouldContainAll
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row

class GolTests : StringSpec({

    "neighborCounts of a single cell" {
        val cell = Cell(1, 1)
        val cellNeighbors = neighbors(cell).map { Pair(it, 1) }.toMap()
        neighborCounts(World(setOf(cell))) shouldContainAll cellNeighbors
    }

    "neighbor cells" {
        forall(
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
})