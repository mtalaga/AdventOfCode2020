package day4

import FileReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DocumentValidationTest {

    val reader = FileReader()

    @Test
    fun correctNumberOfValidDocuments() {
        //Given
        val documentValidation = DocumentValidation(reader.readDocuments("input_day4.txt"))

        //When
        val result = documentValidation.numberOfPassports()

        //Then
        assertEquals(4, result)
    }
}