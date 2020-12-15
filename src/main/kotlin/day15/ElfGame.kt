package day15

class ElfGame {

    fun play(startingNumbers: List<Int>, numberOfTurns: Int): Int {

        val numbersAndPreviousOccurences = mutableMapOf<Int, Pair<Int, Int>>()
        var currentTurn = 1
        var lastSpokenNumber = 0

        for (number in startingNumbers) {
            numbersAndPreviousOccurences[number] = Pair(-1, currentTurn)
            lastSpokenNumber = number
            currentTurn++
        }

        while (currentTurn <= numberOfTurns) {
            val result = if (numbersAndPreviousOccurences[lastSpokenNumber]!!.first == -1) {
                0
            } else {
                val occurencesOfLastSpokenNumber = numbersAndPreviousOccurences[lastSpokenNumber]!!
                occurencesOfLastSpokenNumber.second - occurencesOfLastSpokenNumber.first
            }

            if (numbersAndPreviousOccurences.contains(result)) {
                numbersAndPreviousOccurences[result] = Pair(numbersAndPreviousOccurences[result]!!.second, currentTurn)
            } else {
                numbersAndPreviousOccurences[result] = Pair(-1, currentTurn)
            }

            lastSpokenNumber = result
            currentTurn++
        }

        return lastSpokenNumber
    }
}