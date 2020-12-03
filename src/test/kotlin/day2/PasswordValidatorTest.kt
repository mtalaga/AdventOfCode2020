package day2

import FileReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PasswordValidatorTest {

    val fileReader = FileReader()

    @Test
    fun correctNumberOfValidPasswords() {
        //given
        val input = fileReader.readPasswordsWithPolicyFromFile("input_day2.txt")
        val validator = PasswordValidator(input)

        //when
        val result = validator.numberOfValidPasswords()

        //then
        assertEquals(1, result)
    }
}