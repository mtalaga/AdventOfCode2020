package day10

import FileReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class JoltageTest {

    private val reader = FileReader()

    @Test
    fun findNumberOfJoltageDifferencesAmongList() {
        findNumberOfJoltageDifferencesAmongList("input_day10_1.txt", mapOf(Pair(1, 7), Pair(3, 5)))
        findNumberOfJoltageDifferencesAmongList("input_day10_2.txt", mapOf(Pair(1, 22), Pair(3, 10)))
    }

    @Test
    fun findCountOfPossibleCombinations() {
        findCountOfPossibleCombinations("input_day10_1.txt", 8L)
        findCountOfPossibleCombinations("input_day10_2.txt", 19208L)
    }

    private fun findNumberOfJoltageDifferencesAmongList(path: String, correctResult: Map<Int, Int>) {
        //Given
        val joltage = Joltage(reader.readIntValuesFromFile(path))

        //When
        val result = joltage.findNumberOfJoltageDifferences(joltage.input)

        //Then
        assertEquals(correctResult.size, result.size)
        assertEquals(correctResult, result)
    }

    private fun findCountOfPossibleCombinations(path: String, correctResult: Long) {
        //Given
        val joltage = Joltage(reader.readIntValuesFromFile(path))

        //When
        val result = joltage.findNumberOfPossibleCombinations()

        //Then
        assertEquals(correctResult, result)
    }

}