package day1

import day1.ExpensesReport
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ExpensesReportTest {

    @Test
    fun testCorrectFindingPair() {
        //given
        val report = ExpensesReport(listOf(1721, 979, 366, 299, 675, 1456))

        //when
        val result = report.findPairMakingSum2020()

        //then
        assertNotNull(result)
        assertEquals(1, result.size)
        assertEquals(Pair(1721, 299), result[0])
    }

    @Test
    fun testCorrectFindingTriples() {
        //given
        val report = ExpensesReport(listOf(1721, 979, 366, 299, 675, 1456))

        //when
        val result = report.findTriplesMakingSum2020()

        //then
        assertNotNull(result)
        assertEquals(1, result.size)
        assertEquals(Triple(979, 366, 675), result[0])
    }

    @Test
    fun testCorrectFindingMultiplicationOfPairsGiving2020() {
        //given
        val report = ExpensesReport(listOf(1721, 979, 366, 299, 675, 1456))

        //when
        val result = report.findMultiplicationOfPairsMakingSum2020()

        //then
        assertNotNull(result);
        assertEquals(1, result.size)
        assertEquals(514579, result[0])
    }

    @Test
    fun testCorrectFindingMultiplicationOfTriplesGiving2020() {
        //given
        val report = ExpensesReport(listOf(1721, 979, 366, 299, 675, 1456))

        //when
        val result = report.findMultiplicationsOfTriplesMakingSum2020()

        //then
        assertNotNull(result);
        assertEquals(1, result.size)
        assertEquals(241861950, result[0])
    }
}