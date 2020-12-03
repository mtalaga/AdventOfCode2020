package day1

class ExpensesReport(private val expenses: List<Int>) {

    fun findMultiplicationOfPairsMakingSum2020() : List<Int> {
        val result = mutableListOf<Int>()
        val listOfPairs = findPairMakingSum2020()

        for (pair in listOfPairs) {
            result.add(pair.first * pair.second)
        }

        return result
    }

    fun findMultiplicationsOfTriplesMakingSum2020() : List<Int> {
        val result = mutableListOf<Int>()
        val listOfTriples = findTriplesMakingSum2020()

        for (triple in listOfTriples) {
            result.add(triple.first * triple.second * triple.third)
        }

        return result
    }

    fun findPairMakingSum2020(): MutableList<Pair<Int, Int>> {
        val result = mutableListOf<Pair<Int, Int>>()
        for (i in expenses.indices) {
            for (j in i until expenses.size) {
                if (i != j) {
                    if (expenses[i] + expenses[j] == 2020) {
                        result.add(Pair(expenses[i], expenses[j]))
                    }
                }
            }
        }
        return result
    }

    fun findTriplesMakingSum2020(): MutableList<Triple<Int, Int, Int>> {
        val result = mutableListOf<Triple<Int, Int, Int>>()
        for (i in expenses.indices) {
            for (j in i until expenses.size) {
                for (k in j until expenses.size) {
                    if (i != j && j != k) {
                        if (expenses[i] + expenses[j] + expenses[k] == 2020) {
                            result.add(Triple(expenses[i], expenses[j], expenses[k]))
                        }
                    }
                }
            }
        }
        return result
    }

}