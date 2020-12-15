package day9

class XmasEncoding(private val preamble: Int, private val values: List<Long>) {

    fun findNumbersWhichAreNotSumOfPreviousPreambleValues(): List<Long> {
        val result = mutableListOf<Long>()

        for (i in preamble until values.size) {
            var couldBeSumOfTwoPreviousNumbers = false
            for (j in i-preamble until i) {
                for (k in j+1 until i) {
                    if ((values[j] + values[k]) == values[i]) {
                        couldBeSumOfTwoPreviousNumbers = true
                    }
                }
            }

            if (!couldBeSumOfTwoPreviousNumbers) {
                result.add(values[i])
            }
        }

        return result
    }

    fun findContiguouSetOfAtLeastTwoNumbersWhichAreNotSumOfPreviousPreambleValues() : List<Pair<Long, List<Long>>> {
        val result = mutableListOf<Pair<Long, List<Long>>>()

        val notValidIndexes = findNumbersWhichAreNotSumOfPreviousPreambleValues().map { values.indexOf(it) }

        for (i in notValidIndexes) {
            for (j in 0 until i) {
                var currentSum = values[j]
                val currentList = mutableListOf(values[j])
                for (k in j + 1 until i) {
                    if (currentSum + values[k] != values[i]) {
                        currentList.add(values[k])
                        currentSum += values[k]
                    } else {
                        currentList.add(values[k])
                        result.add(Pair(values[i], currentList))
                        break
                    }
                }
            }
        }

        return result
    }
}