package day17

class ConwayCube(private val firstSlice: List<List<CubeState>>) {

    fun cubesLeftInStateAfterCycles(state: CubeState, cycles: Int): Int {
        var cube = mutableListOf<List<List<CubeState>>>()
        cube.add(createEmptySlice(firstSlice.size))
        cube.add(firstSlice)
        cube.add(createEmptySlice(firstSlice.size))

        var currentCycle = cycles
        while (currentCycle > 0) {
            val newCube = mutableListOf<List<List<CubeState>>>()
            newCube.add(firstSlice)
            for (i in cube.indices) {
                val newCubeSlice = mutableListOf<List<CubeState>>()
                for (j in cube[i].indices) {
                    val newCubeRow = mutableListOf<CubeState>()
                    for (k in cube[i][j].indices) {
                        newCubeRow.add(cube[i][j][k].newState(calculateNeighbors(Triple(i, j, k), cube)))
                    }
                    newCubeSlice.add(newCubeRow)
                }
                newCube.add(newCubeSlice)
            }
            newCube.add(firstSlice)
            cube = newCube
            currentCycle -= 1
        }

        return cube.map { slice -> slice.map { row -> row.filter { it == state }.count() }.sum() }.sum()
    }

    private fun createEmptySlice(size: Int): List<List<CubeState>> {
        val result = mutableListOf<List<CubeState>>()
        for (i in 0 until size) {
            val element = mutableListOf<CubeState>()
            IntRange(0, size - 1).forEach { _ -> element.add(CubeState.INACTIVE) }
            result.add(element)
        }
        return result
    }

    private fun calculateNeighbors(element: Triple<Int, Int, Int>, cube: List<List<List<CubeState>>>): List<CubeState> {
        val firstValues = listOf(element.first - 1, element.first, element.first + 1)
        val secondValues = listOf(element.second - 1, element.second, element.second + 1)
        val thirdValues = listOf(element.third - 1, element.third, element.third + 1)

        val places = listOf(firstValues, secondValues, thirdValues)
            .fold(listOf(listOf<Int>())) { acc, set ->
                acc.flatMap { list -> set.map { element -> list + element } }
            }
            .map { Triple(it[0], it[1], it[2]) }
            .filter { it != element }
            .toSet()

        val neighborStates = mutableListOf<CubeState>()
        for (triple in places) {
            neighborStates.add(cube[triple.first][triple.second][triple.third])
        }

        return neighborStates
    }
}

enum class CubeState(val symbol: Char) {
    ACTIVE('#'), INACTIVE('.');

    companion object {
        fun ofSymbol(symbol: Char): CubeState {
            return values().first { it.symbol == symbol }
        }
    }

    fun newState(neighbors: List<CubeState>): CubeState {
        return when (this) {
            ACTIVE -> if (IntRange(2, 3).contains(neighbors.filter { it == ACTIVE }.count())) ACTIVE else INACTIVE
            INACTIVE -> if (3 == neighbors.filter { it == ACTIVE }.count()) ACTIVE else INACTIVE
        }
    }

    override fun toString(): String {
        return symbol.toString()
    }
}