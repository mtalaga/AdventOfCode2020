package day5

import kotlin.math.ceil

class SeatCalculator {

    private val rowSeatAlgorithm = SeatAlgorithm('B', 'F')
    private val columnSeatAlgorithm = SeatAlgorithm('R', 'L')

    fun missingSeatIDs(input: List<String>) : List<Int> {
        val result = mutableListOf<Int>()
        val seats = calculateSeats(input)
        val max = highestSeatID(seats.keys)

        for (i in 0..max) {
            if (!seats.containsKey(i)) {
                result.add(i)
            }
        }

        return result;
    }

    fun highestSeatID(seatIds: Set<Int>) : Int {
        var highestSeatId = 0
        for (seat in seatIds) {
            if (seat > highestSeatId) {
                highestSeatId = seat
            }
        }
        return highestSeatId
    }

    fun calculateSeats(input: List<String>): Map<Int, Seat> {
        val result = mutableMapOf<Int, Seat>()
        for (seatCode in input) {
            val seat = calculateSeat(seatCode)
            result[seat.seatId] = seat
        }
        return result
    }

    fun calculateSeat(input: String): Seat {
        val row = calculateRow(input.substring(0, 7))
        val column = calculateColumn(input.substring(7, 10))
        val seatId = calculateSeatId(row, column)
        return Seat(row, column, seatId)
    }

    private fun calculateRow(input: String): Int {
        var range = Pair(0, 127)
        for (character in input) {
            range = rowSeatAlgorithm.returnRange(character, range)
        }
        return range.first
    }

    private fun calculateColumn(input: String) : Int {
        var range = Pair(0, 7)
        for (character in input) {
            range = columnSeatAlgorithm.returnRange(character, range)
        }
        return range.second
    }

    private fun calculateSeatId(row: Int, column: Int) : Int {
        return (row * 8) + column
    }
}

data class Seat(val row: Int, val column: Int, val seatId: Int)

data class SeatAlgorithm(private val upperHalfChar: Char, private val lowerHalfChar: Char) {
    fun returnRange(halfChar: Char, range: Pair<Int, Int>) : Pair<Int, Int> {
        val pivot = ceil((range.second - range.first).toDouble() / 2).toInt()
        return if (halfChar == upperHalfChar) Pair(range.first + pivot, range.second) else Pair(range.first, range.second - pivot)
    }
}