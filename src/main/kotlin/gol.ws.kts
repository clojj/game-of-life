data class Cell(val x: Int, val y: Int) : Comparable<Cell> {
    override fun compareTo(other: Cell): Int = compareValuesBy(this, other, { it.x }, { it.y })
}

data class World(val cells: Set<Cell>)

val world = World(setOf(Cell(3, 1), Cell(1, 2), Cell(1, 3), Cell(2, 3)))

println(world)


// in 4 Schritten von einer 'Methode' zur Funktion-als-Wert

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

println(neighbors1(Cell(1, 1)))

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

println(neighbors2()(Cell(1, 1)))

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

println(neighbors3(Cell(1, 1)))

val neighbors4 = { (x, y): Cell ->
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

println(neighbors4(Cell(1, 1)))

