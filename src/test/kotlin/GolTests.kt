import gol.Cell
import gol.neighbors
import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row

class GolTests : StringSpec({

    val cell = Cell(1, 1)

    "neighbors" {
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