package day7

import FileReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BagListTest {

    private val reader = FileReader()

    @Test
    fun numberOfBagsWhichCanContainBagWithName() {
        //Given
        val handler = BagList(reader.readBags("input_day7.txt"))

        //When
        val result = handler.residesInBags("shiny gold")

        //Then
        assertEquals(4, result)
    }

    @Test
    fun countBagsInside() {
        //Given
        val handler = BagList(reader.readBags("input_day7_2.txt"))

        //When
        val result = handler.countBagsInside("shiny gold")

        //Then
        assertEquals(126, result)
    }
}