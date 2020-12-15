package day13

class BusSchedule(private val timestamp: Int, private val busLines: List<Int>) {

    fun calculateEarlierBusWithWaitingTimeMultiplication(): Int {
        var minimumPairBusIdDifference = Pair(Int.MAX_VALUE, Int.MAX_VALUE)

        for (line in busLines) {
            if (timestamp+line-(timestamp%line) < minimumPairBusIdDifference.second) {
                minimumPairBusIdDifference = Pair(line, timestamp+line-(timestamp%line))
            }
        }

        return minimumPairBusIdDifference.first * (minimumPairBusIdDifference.second - timestamp)
    }
}