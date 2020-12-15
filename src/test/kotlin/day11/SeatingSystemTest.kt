package day11

import FileReader
import kotlin.test.Test
import kotlin.test.assertEquals

class SeatingSystemTest {

    private val reader = FileReader()

    @Test
    fun calculateSeatsOccupiedWhenChangesStopTest() {
        //Given
        val seatingSystem = SeatingSystem(reader.readSeatMap("input_day11.txt"))

        //When
        val result = seatingSystem.calculateOccupiedSeatsWhenChangesStops()

        //Then
        assertEquals(37, result)
    }

    @Test
    fun calculateAllSeatsOccupiedWhenChangesStopTest() {
        //Given
        val seatingSystem = SeatingSystem(reader.readSeatMap("input_day11.txt"))

        //When
        val result = seatingSystem.calculateOccupiedSeatsWhenChangesStopsPart2()

        //Then
        assertEquals(26, result)
    }

}