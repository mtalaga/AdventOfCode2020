package day16

class TicketDecoder(private val ruleList: RuleList, private val yourTicket: Ticket, private val ticketList: List<Ticket>) {

    fun sumOfNotValidValues(): Int {
        return findValuesFulfilsNoRules().sum()
    }

    fun multiplyDepartureValuesFromMyTicket(): Long {
        val correctTickets = findCorrectTickets()
        val typeWithPosition = mutableListOf<Pair<Int, String>>()
        var typeWithMoreThanOnePosition = mutableListOf<Pair<String, List<Int>>>()

        val countList = IntRange(0, yourTicket.valueList.size-1)

        for (rule in ruleList.rule) {
            val possiblePositions = mutableListOf<Int>()
            for (position in countList) {
                var ruleApply = true
                for (ticket in correctTickets) {
                    if (!rule.second.contains(ticket.valueList[position])) {
                        ruleApply = false
                        break
                    }
                }
                if (ruleApply) {
                    possiblePositions.add(position)
                }
            }
            if (possiblePositions.size == 1) {
                typeWithPosition.add(Pair(possiblePositions[0], rule.first))
            } else {
                typeWithMoreThanOnePosition.add(Pair(rule.first, possiblePositions))
            }
        }

        while (typeWithMoreThanOnePosition.size > 0) {
            val newTypeWithMoreThanOnePosition = mutableListOf<Pair<String, List<Int>>>()
            for (type in typeWithMoreThanOnePosition) {
                val newList = type.second.filter { position -> !typeWithPosition.map { it.first }.contains(position) }
                if (newList.size == 1) {
                    typeWithPosition.add(Pair(newList[0], type.first))
                } else {
                   newTypeWithMoreThanOnePosition.add(Pair(type.first, newList))
                }
            }
            typeWithMoreThanOnePosition = newTypeWithMoreThanOnePosition
        }

        var result = 1L
        for (position in typeWithPosition.filter { it.second.startsWith("departure") }) {
            result *= yourTicket.valueList[position.first]
        }

        return result
    }

    private fun findCorrectTickets(): List<Ticket> {
        val result = mutableListOf<Ticket>()

        val allRuleValues = mutableListOf<Int>()
        for (rule in ruleList.rule) {
            allRuleValues.addAll(rule.second)
        }

        for (ticket in ticketList) {
            var ticketValid = true
            for (value in ticket.valueList) {
                if (!allRuleValues.contains(value)) {
                    ticketValid = false
                }
            }
            if (ticketValid) {
                result.add(ticket)
            }
        }

        return result
    }

    private fun findValuesFulfilsNoRules(): List<Int> {
        val result = mutableListOf<Int>()

        val allRuleValues = mutableListOf<Int>()
        for (rule in ruleList.rule) {
            allRuleValues.addAll(rule.second)
        }

        for (ticket in ticketList) {
            for (value in ticket.valueList) {
                if (!allRuleValues.contains(value)) {
                    result.add(value)
                }
            }
        }

        return result
    }

}


data class RuleList(val rule: List<Pair<String, List<Int>>>)
data class Ticket (val valueList: List<Int>)