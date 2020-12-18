package day18

import org.junit.jupiter.api.Test
import kotlin.test.Ignore
import kotlin.test.assertEquals

class ExpressionEvaluatorTest {

    @Test
    fun shouldSumExpressions() {
        //Given
        val input = listOf("2 * 3 + (4 * 5)", "5 + (8 * 3 + 9 + 3 * 4 * 3)", "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))", "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2")
        val expressionEvaluator = ExpressionEvaluator()

        //When
        val result = expressionEvaluator.sumExpressionResults(input)

        //Then
        assertEquals(26335L, result)

    }

    @Test
    fun shouldSumExpressionsVersion2() {
        //Given
        val input = listOf("1 + (2 * 3) + (4 * (5 + 6))", "2 * 3 + (4 * 5)", "5 + (8 * 3 + 9 + 3 * 4 * 3)", "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))", "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2")
        val expressionEvaluator = ExpressionEvaluator()

        //When
        val result = expressionEvaluator.sumExpressionResultsWithPlusFirst(input)

        //Then
        assertEquals(693942L, result)
    }
}