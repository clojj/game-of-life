package gol

val neighbors = { (x, y): Cell ->
    (-1..1).flatMap { dx -> (-1..1).map { dy -> Cell(x + dx, y + dy) } }.toSet() - Cell(x, y)
}

val neighborCounts = { world: World ->
    world.cells
        .flatMap { cell -> neighbors(cell) }
        .sorted()
        .groupBy({ it }, { _ -> Unit })
        .mapValues { (_, cells) -> cells.size }
}

fun nextGeneration(world: World): World {
    val possibleCells = neighborCounts(world)
    return World(possibleCells.filter { (cell, count) -> count == 3 || (count == 2 && world.cells.contains(cell)) }.keys)
}

tailrec fun generate(world: World, n: Int): World =
    if (n == 0)
        world
    else {
        generate(nextGeneration(world), n - 1)
    }

val golSequence: (World) -> Sequence<World> =
    { world: World ->
        sequence {
            yieldAll(generateSequence(world) { world -> nextGeneration(world) })
        }
    }

