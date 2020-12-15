package day12

import FileReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GuidanceTest {

    private val reader = FileReader()

    @Test
    fun applyDirections() {
        //Given
        val guidance = Guidance()

        //When
        guidance.applyDirectionsToState(reader.readDirections("input_day12.txt"))

        //Then
        assertEquals(-8, guidance.state.x)
        assertEquals(17, guidance.state.y)
        assertEquals(Direction.SOUTH, guidance.state.direction)
    }

    @Test
    fun calculateManhattanDistance() {
        //Given
        val guidance = Guidance()
        guidance.applyDirectionsToState(reader.readDirections("input_day12.txt"))

        //When
        val result = guidance.calculateManhattanDistance()

        //Then
        assertEquals(25, result)
    }
}