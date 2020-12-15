package day13

import FileReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BusScheduleTest {

    private val reader = FileReader()

    @Test
    fun calculateEarlierBusWithWaitingTimeMultiplication() {
        //Given
        val input = reader.readBusSchedule("input_day13.txt")
        val busSchedule = BusSchedule(input.first, input.second)

        //When
        val result = busSchedule.calculateEarlierBusWithWaitingTimeMultiplication()

        //Then
        assertEquals(295, result)

    }
}