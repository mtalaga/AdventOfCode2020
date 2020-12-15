package day15

import org.junit.jupiter.api.Test
import kotlin.test.Ignore
import kotlin.test.assertEquals

class ElfGameTest {

    @Test
    fun check2020thElfGameResultsWithInputParameters() {
        testElfGameResult(listOf(0,3,6), 2020, 436)
        testElfGameResult(listOf(1,3,2), 2020, 1)
        testElfGameResult(listOf(2,1,3), 2020, 10)
        testElfGameResult(listOf(1,2,3), 2020, 27)
        testElfGameResult(listOf(2,3,1), 2020, 78)
        testElfGameResult(listOf(3,2,1), 2020, 438)
        testElfGameResult(listOf(3,1,2), 2020, 1836)
    }

    @Ignore
    @Test
    fun check30000000thElfGameResultsWithInputParameters() {
        testElfGameResult(listOf(0,3,6), 30000000, 175594)
        testElfGameResult(listOf(1,3,2), 30000000, 2578)
        testElfGameResult(listOf(2,1,3), 30000000, 3544142)
        testElfGameResult(listOf(1,2,3), 30000000, 261214)
        testElfGameResult(listOf(2,3,1), 30000000, 6895259)
        testElfGameResult(listOf(3,2,1), 30000000, 18)
        testElfGameResult(listOf(3,1,2), 30000000, 362)
    }

    fun testElfGameResult(startingNumbers: List<Int>, numberOfTurns: Int, resultAfterNumberOfTurns: Int) {
        //Given
        val elfGame = ElfGame()

        //When
        val result = elfGame.play(startingNumbers, numberOfTurns)

        //Expected
        assertEquals(resultAfterNumberOfTurns, result)
    }
}