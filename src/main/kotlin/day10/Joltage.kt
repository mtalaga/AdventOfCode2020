package day10

class Joltage(val input: List<Int>) {

    fun findNumberOfJoltageDifferences(input: List<Int>): Map<Int, Int> {
        val result = mutableMapOf<Int, Int>()
        val orderedInput = input.sorted()
        var previousJoltage = 0

        for (i in 0..orderedInput.size) {
            var difference: Int
            if (i < orderedInput.size) {
                difference = orderedInput[i] - previousJoltage
                previousJoltage = orderedInput[i]
            } else {
                difference = 3
            }
            if (result.containsKey(difference)) {
                result[difference] = result[difference]!! + 1
            } else {
                result[difference] = 1
            }
        }

        return result
    }

    fun findNumberOfPossibleCombinations() :Long {
        val orderedInput = input.sorted()
        val combinationCount = arrayOfNulls<Long>(orderedInput[orderedInput.size - 1] + 1)
        combinationCount.fill(0L)
        combinationCount[0] = 1L
        for (element in 0 until orderedInput.size) {
            val diff3 = if (orderedInput[element] >= 3) combinationCount[orderedInput[element] - 3] else 0
            val diff2 = if (orderedInput[element] >= 2) combinationCount[orderedInput[element] - 2] else 0
            val diff1 = if (orderedInput[element] >= 1) combinationCount[orderedInput[element] - 1] else 0

            combinationCount[orderedInput[element]] = diff3!! + diff2!! + diff1!!
        }

        return combinationCount[combinationCount.size - 1]!!
    }
}