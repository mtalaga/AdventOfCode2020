package day8

import FileReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ProgramTest {

    private val reader = FileReader()

    @Test
    fun counterBeforeOverload() {
        //Given
        val program = Program(reader.readProgram("input_day8.txt"))

        //When
        val result = program.findAccumulatorValueBeforeLoop()

        //Then
        assertEquals(5, result.accumulator)
    }

    @Test
    fun correctEndValues() {
        //Given
        val program = Program(reader.readProgram("input_day8.txt"))

        //When
        val result = program.fixProgramWithLoop()

        //Then
        assertEquals(8, result.accumulator)
    }
}