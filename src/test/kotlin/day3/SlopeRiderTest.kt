package day3

import FileReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SlopeRiderTest {

    private val reader = FileReader()

    @Test
    fun countTreesCorrect() {
        //given
        val map = reader.readSlopeRiderMap("input_day3.txt")
        val slopeRider = SlopeRider(map)

        //when
        val result = slopeRider.countTreesOnRoad(Move(3, 1))

        //then
        assertEquals(7, result)
    }
}