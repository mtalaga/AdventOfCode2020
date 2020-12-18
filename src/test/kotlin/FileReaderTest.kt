import day14.BitMaskCharacter
import day2.PasswordWithPolicy
import day8.Operation
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FileReaderTest {

    private val reader = FileReader()

    @Test
    fun readIntsFromFileWhichExists() {
        //given
        val result = reader.readIntValuesFromFile("input_day1.txt")

        //expected
        assertEquals(11, result.size)
        assertEquals(listOf(1511, 1112, 1958, 1778, 1769, 1946, 1800, 1911, 1821, 1886, 1649), result)
    }

    @Test
    fun readLongsFromFileWhichExists() {
        //given
        val result = reader.readLongValuesFromFile("input_day1.txt")

        //expected
        assertEquals(11, result.size)
        assertEquals(listOf(1511L, 1112L, 1958L, 1778L, 1769L, 1946L, 1800L, 1911L, 1821L, 1886L, 1649L), result)
    }


    @Test
    fun readPasswordWithPolicyFromFileWhichExists() {
        //given
        val result = reader.readPasswordsWithPolicyFromFile("input_day2.txt")

        //expected
        assertEquals(3, result.size)
        assertEquals(PasswordWithPolicy(1, 3, 'a', "abcde"), result[0])
        assertEquals(PasswordWithPolicy(1, 3, 'b', "cdefg"), result[1])
        assertEquals(PasswordWithPolicy(2, 9, 'c', "ccccccccc"), result[2])
    }

    @Test
    fun readSlopeRiderMap() {
        //given
        val result = reader.readSlopeRiderMap("input_day3.txt")

        //expected
        assertEquals(11, result.size)
        for (array in result) {
            assertEquals(11, array.size)
        }
    }

    @Test
    fun readDocumentList() {
        //given
        val result = reader.readDocuments("input_day4.txt")

        //expected
        assertEquals(8, result.size)
    }

    @Test
    fun readSeatCodes() {
        //given
        val result = reader.readStringLines("input_day5.txt")

        //expected
        assertEquals(4, result.size)
    }

    @Test
    fun readQuestionnaires() {
        //given
        val result = reader.readQuestionnaires("input_day6.txt")

        //expected
        assertEquals(5, result.size)
    }

    @Test
    fun readBags() {
        //given
        val result = reader.readBags("input_day7.txt")

        //expected
        assertEquals(9, result.size)
    }

    @Test
    fun readProgram() {
        //given
        val result = reader.readProgram("input_day8.txt")

        //expected
        assertEquals(9, result.size)
        assertEquals(Operation("acc", -99), result[5])
    }

    @Test
    fun readSeatMap() {
        //given
        val result = reader.readSeatMap("input_day11.txt")

        //expected
        assertEquals(10, result.size)
        for (list in result) {
            assertEquals(10, list.size)
        }
    }

    @Test
    fun readDirections() {
        //given
        val result = reader.readSeatMap("input_day12.txt")

        //expected
        assertEquals(5, result.size)
    }

    @Test
    fun readBusSchedule() {
        //given
        val result = reader.readBusSchedule("input_day13.txt")

        //expected
        assertEquals(939, result.first)
        assertEquals(5, result.second.size)
    }

    @Test
    fun readMemory() {
        //given
        val result = reader.readMemory("input_day14.txt")

        //expected
        assertEquals(1, result.size)
        assertEquals(36, result[0].mask.size)
        assertEquals(3, result[0].addressChanges.size)
        assertTrue(result[0].mask.contains(Pair(1, BitMaskCharacter.ZERO)))
        assertTrue(result[0].mask.contains(Pair(6, BitMaskCharacter.ONE)))
        assertTrue(result[0].addressChanges.contains(Pair(8, 11)))
        assertTrue(result[0].addressChanges.contains(Pair(7, 101)))
        assertTrue(result[0].addressChanges.contains(Pair(8, 0)))
    }

    @Test
    fun readRulesWithTickets() {
        //given
        val result = reader.readRulesWithTickets("input_day16.txt")

        //expected
        assertEquals(3, result.first.rule.size)
        assertEquals("class", result.first.rule[0].first)
        assertTrue(result.second.valueList.containsAll(listOf(7,1,14)))
        assertEquals(4, result.third.size)
    }

    @Test
    fun readCubeSlice() {
        //given
        val result = reader.readCubeSlice("input_day17.txt")

        //expected
        assertEquals(3, result.size)
        for (array in result) {
            assertEquals(3, array.size)
        }
    }
}