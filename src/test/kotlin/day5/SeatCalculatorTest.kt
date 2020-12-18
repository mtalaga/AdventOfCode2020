package day5

import FileReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SeatCalculatorTest {

    private val reader = FileReader()

    @Test
    fun shouldCalculateSeatNumber() {
        //given
        val seatCalculator = SeatCalculator()

        //when
        val result = seatCalculator.calculateSeats(reader.readStringLines("input_day5.txt"))

        //then
        assertEquals(4, result.size)
        assertEquals(Seat(44, 5, 357), result[357])
        assertEquals(Seat(70, 7, 567), result[567])
        assertEquals(Seat(14, 7, 119), result[119])
        assertEquals(Seat(102, 4, 820), result[820])
    }

    @Test
    fun shouldReturnHighestSeatId() {
        //given
        val seatCalculator = SeatCalculator()
        val seats = seatCalculator.calculateSeats(reader.readStringLines("input_day5.txt"))

        //when
        val result = seatCalculator.highestSeatID(seats.keys)

        //then
        assertEquals(820, result)
    }
}