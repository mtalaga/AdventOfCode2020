package day16

import FileReader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TicketDecoderTest() {

    private val reader = FileReader()

    @Test
    fun shouldCreateSumOfInvalidValues() {
        //Given
        val triple = reader.readRulesWithTickets("input_day16.txt")
        val ticketDecoder = TicketDecoder(triple.first, triple.second, triple.third)

        //When
        val result = ticketDecoder.sumOfNotValidValues()

        //Then
        assertEquals(71, result)
    }

    @Test
    fun shouldCreateMultiplicationOfDepartureValues() {
        //Given
        val triple = reader.readRulesWithTickets("input_day16_2.txt")
        val ticketDecoder = TicketDecoder(triple.first, triple.second, triple.third)

        //When
        val result = ticketDecoder.multiplyDepartureValuesFromMyTicket()

        //Then
        assertEquals(12, result)
    }

}
