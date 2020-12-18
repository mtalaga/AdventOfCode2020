package day18

class ExpressionEvaluator {

    fun sumExpressionResults(expressions: List<String>): Long {
        return expressions.sumOf { evaluateExpression(it, false) }
    }

    fun sumExpressionResultsWithPlusFirst(expressions: List<String>): Long {
        return expressions.sumOf { evaluateExpression(it, true) }
    }

    fun evaluateExpression(expression: String, version2: Boolean): Long {
        val elements = expression.replace(" ", "")

        var currentValue = 0L
        var currentFunction: (Long, Long) -> Long = Long::plus

        var counter = 0
        while (counter < elements.length) {
            when (elements[counter]) {
                '*' -> {
                    if (!version2) {
                        currentFunction = Long::times
                    } else {
                        currentValue *= evaluateExpression(elements.substring(counter + 1, elements.length), version2)
                        counter = elements.length-1
                    }
                }
                '+' -> currentFunction = Long::plus
                '(' -> {
                    val endOfExpression = counter + findEndOfExpressionIndex(elements.substring(counter+1, elements.length))
                    currentValue = currentFunction.invoke(evaluateExpression(elements.substring(counter+1, endOfExpression), version2), currentValue)
                    counter = endOfExpression
                }
                else -> currentValue = currentFunction.invoke(currentValue, elements[counter].toString().toLong())
            }
            counter += 1
        }

        return currentValue
    }

    fun findEndOfExpressionIndex(expression: String): Int {
        var expressionCount = 0
        for (i in expression.indices) {
            if (expression[i] == '(') {
                expressionCount += 1
            } else if (expression[i] == ')') {
                if (expressionCount == 0) {
                    return i+1
                } else {
                    expressionCount -= 1
                }
            }
        }

        return -1
    }


}