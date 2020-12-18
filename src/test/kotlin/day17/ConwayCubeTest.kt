package day17

import FileReader
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

class ConwayCubeTest {

    private val reader = FileReader()

    @Ignore
    @Test
    fun countActivesCubesAfterSixCycles() {
        //Given
        val conwayCube = ConwayCube(reader.readCubeSlice("input_day17.txt"))

        //When
        val result = conwayCube.cubesLeftInStateAfterCycles(CubeState.ACTIVE, 6)

        //Then
        assertEquals(112, result)

    }
}