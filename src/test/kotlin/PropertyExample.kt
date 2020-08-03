import gol.Cell
import gol.World
import gol.golSequence
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.checkAll

class PropertyExample: StringSpec({
    "blinker" {
        val worldForUneven = World(setOf(Cell(1, 1), Cell(1, 2), Cell(1, 3)))
        val worldForEven = World(setOf(Cell(0, 2), Cell(1, 2), Cell(2, 2)))
        fun Int.even() = this % 2 == 0
        checkAll(Arb.int(1..1000)) { i ->
            when (i.even()) {
               false -> golSequence(worldForUneven).take(i).last() shouldBe worldForUneven
               true -> golSequence(worldForUneven).take(i).last() shouldBe worldForEven
            }
        }
    }
})