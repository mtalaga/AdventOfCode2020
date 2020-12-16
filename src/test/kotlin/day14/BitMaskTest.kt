package day14

import FileReader
import org.junit.jupiter.api.Test
import kotlin.test.Ignore
import kotlin.test.assertEquals

class BitMaskTest {

    private val reader = FileReader()

    @Test
    fun calculateSumOfNonZeroAddressesShouldReturn165() {
        //Given
        val bitmask = BitMask(reader.readMemory("input_day14.txt"))

        //When
        val result = bitmask.decodeValuesAndSumNonZeroMemory()

        //Then
        assertEquals(165L, result)
    }

    @Ignore
    @Test
    fun calculateSumOfNonZeroAddressesWhenApplyingMaskToMemoryBankShouldReturn201() {
        //Given
        val bitmask = BitMask(reader.readMemory("input_day14.txt"))

        //When
        val result = bitmask.decodeMemoryAddressAndSumNonZeroValues()

        //Then
        assertEquals(208L, result)
    }
}