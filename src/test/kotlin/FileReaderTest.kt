import day2.PasswordWithPolicy
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

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
        val result = reader.readSeatCodes("input_day5.txt")

        //expected
        assertEquals(4, result.size)
    }
}