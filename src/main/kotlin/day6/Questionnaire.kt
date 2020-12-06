class Questionnaire(private val groups: List<Group>) {

    fun calculateAllYesAnswersInGroups() : Int {
        var result = 0
        for (group in groups) {
            result += group.numberOfQuestionsWithYesAnswerThroughWholeGroup()
        }
        return result
    }

}

data class Group(val questionsWithYesAnswer: List<String>) {

    private val asciiSmallACode = 97

    fun numberOfQuestionsWithYesAnswerThroughWholeGroup(): Int {
        val questions = IntArray(26)

        for (question in questionsWithYesAnswer) {
            for (answer in question) {
                questions[answer.toInt()-asciiSmallACode] += 1
            }
        }

        return questions.filter { it == questionsWithYesAnswer.size }.count()
    }
}