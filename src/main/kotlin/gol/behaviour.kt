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