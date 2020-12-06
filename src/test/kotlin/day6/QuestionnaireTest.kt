package day6

import FileReader
import Questionnaire
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class QuestionnaireTest {

    private val reader = FileReader()

    @Test
    fun countYesAnswersInGroups() {
        //given
        val questionare = Questionnaire(reader.readQuestionnaires("input_day6.txt"))

        //when
        val result = questionare.calculateAllYesAnswersInGroups()

        //then
        assertEquals(6, result)
    }
}