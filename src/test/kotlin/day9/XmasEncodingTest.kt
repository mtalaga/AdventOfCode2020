package day9

import FileReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class XmasEncodingTest {

    private val reader = FileReader()

    @Test
    fun shouldReturnNotValidNextNumber() {
        //Given
        val encoding = XmasEncoding(5, reader.readLongValuesFromFile("input_day9.txt"))

        //When
        val result = encoding.findNumbersWhichAreNotSumOfPreviousPreambleValues()

        //Then
        assertEquals(1, result.size)
        assertEquals(127, result[0])
    }

    @Test
    fun findContiguouSetOfAtLeastTwoNumbersWhichAreNotSumOfPreviousPreambleValues() {
        //Given
        val encoding = XmasEncoding(5, reader.readLongValuesFromFile("input_day9.txt"))

        //When
        val result = encoding.findContiguouSetOfAtLeastTwoNumbersWhichAreNotSumOfPreviousPreambleValues()

        //Then
        assertEquals(1, result.size)
        assertEquals(Pair(127L, listOf(15L,25L,47L,40L)), result[0])
    }
}