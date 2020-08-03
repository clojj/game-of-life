data class Cell(val x: Int, val y: Int) : Comparable<Cell> {
    override fun compareTo(other: Cell): Int = compareValuesBy(this, other, { it.x }, { it.y })
}

data class World(val cells: Set<Cell>)

val world = World(setOf(Cell(1, 1), Cell(1, 2), Cell(1, 3)))
println(world)

val neighbors = { (x, y): Cell ->
    listOf(
            Cell(x, y + 1),
            Cell(x, y - 1),
            Cell(x + 1, y),
            Cell(x - 1, y),
            Cell(x + 1, y + 1),
            Cell(x + 1, y - 1),
            Cell(x - 1, y + 1),
            Cell(x - 1, y - 1)
    )
}

// Nachbarschaft ist symmetrisch !

val neighborCounts = { world: World ->
    world.cells.flatMap { c: Cell -> neighbors(c) }.sorted().groupBy { it }.mapValues { (_, value) -> value.count() }
}

println("neighbors ${neighborCounts(world)}")

fun nextGeneration(world: World): World {
    val possibleCells = neighborCounts(world)
    return World(possibleCells.filter { (cell, count) -> count == 3 || (count == 2 && world.cells.contains(cell)) }.keys)
}

println("nextGeneration ${nextGeneration(world)}")

tailrec fun generate(world: World, n: Int): World =
        if (n == 0)
            world
        else {
            generate(nextGeneration(world), n - 1)
        }

println("generate ${generate(world, 103)}")

val golSequence = sequence {
    yieldAll(generateSequence(world) { world -> nextGeneration(world) })
}

println("golSequence ${golSequence.take(5).toList()}")

/*
[World(cells=[Cell(x=1, y=1), Cell(x=1, y=2), Cell(x=1, y=3)]),
 World(cells=[Cell(x=0, y=2), Cell(x=1, y=2), Cell(x=2, y=2)]),
 World(cells=[Cell(x=1, y=1), Cell(x=1, y=2), Cell(x=1, y=3)]),
 World(cells=[Cell(x=0, y=2), Cell(x=1, y=2), Cell(x=2, y=2)]),
 World(cells=[Cell(x=1, y=1), Cell(x=1, y=2), Cell(x=1, y=3)])]
*/

// Ausflug: in 3 Schritten von einer 'Methode' zur Funktion-als-Wert

fun neighbors1(cell: Cell): List<Cell> {
    val (x, y) = cell
    return listOf(
            Cell(x, y + 1),
            Cell(x, y - 1),
            Cell(x + 1, y),
            Cell(x - 1, y),
            Cell(x + 1, y + 1),
            Cell(x + 1, y - 1),
            Cell(x - 1, y + 1),
            Cell(x - 1, y - 1)
    )
}

fun neighbors2(): (Cell) -> List<Cell> = { (x, y) ->
    listOf(
            Cell(x, y + 1),
            Cell(x, y - 1),
            Cell(x + 1, y),
            Cell(x - 1, y),
            Cell(x + 1, y + 1),
            Cell(x + 1, y - 1),
            Cell(x - 1, y + 1),
            Cell(x - 1, y - 1)
    )
}

val neighbors3: (Cell) -> List<Cell> = { (x, y) ->
    listOf(
            Cell(x, y + 1),
            Cell(x, y - 1),
            Cell(x + 1, y),
            Cell(x - 1, y),
            Cell(x + 1, y + 1),
            Cell(x + 1, y - 1),
            Cell(x - 1, y + 1),
            Cell(x - 1, y - 1)
    )
}
