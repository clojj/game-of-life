import funk.Cell
import funk.World

val c1 = Cell(3, 4)
println(c1)

val c2 = Cell(3, 5)

println(c2 == c1)
println(c2 > c1)


val world = World(
    setOf(
        Cell(3, 1),
        Cell(1, 2),
        Cell(1, 3),
        Cell(2, 3)
    )
)

println(world.cells.joinToString(","))
