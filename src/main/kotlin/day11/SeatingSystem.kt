package day11

class SeatingSystem(private val initialMap: List<List<SeatState>>) {

    fun calculateOccupiedSeatsWhenChangesStops(): Int {
        var switchPlaces = true
        var previousMap = initialMap
        while (switchPlaces) {
            val newMap = mutableListOf<List<SeatState>>()
            for (row in previousMap.indices) {
                val newRow = mutableListOf<SeatState>()
                for (seat in previousMap[row].indices) {
                    val newState = previousMap[row][seat].calculateNewState(calculateNeighbors(Pair(row, seat), previousMap))
                    newRow.add(newState)
                }
                newMap.add(newRow)
            }
            if (newMap != previousMap) {
                previousMap = newMap
            } else {
                switchPlaces = false
            }
        }
        return previousMap.map { row -> row.filter { it == SeatState.OCCUPIED }.count() }.sum()
    }

    fun calculateOccupiedSeatsWhenChangesStopsPart2(): Int {
        var switchPlaces = true
        var previousMap = initialMap
        while (switchPlaces) {
            val newMap = mutableListOf<List<SeatState>>()
            for (row in previousMap.indices) {
                val newRow = mutableListOf<SeatState>()
                for (seat in previousMap[row].indices) {
                    val newState = previousMap[row][seat].calculateNewStatePart2(calculateAllNeighbors(Pair(row, seat), previousMap))
                    newRow.add(newState)
                }
                newMap.add(newRow)
            }
            if (newMap != previousMap) {
                previousMap = newMap
            } else {
                switchPlaces = false
            }
        }
        return previousMap.map { row -> row.filter { it == SeatState.OCCUPIED }.count() }.sum()
    }

    private fun calculateNeighbors(currentPlace: Pair<Int, Int>, map: List<List<SeatState>>): List<SeatState> {
        val result = mutableListOf<SeatState>()

        if (currentPlace.first != 0) {
            result.add(map[currentPlace.first - 1][currentPlace.second])
        }
        if (currentPlace.first != map.size-1) {
            result.add(map[currentPlace.first + 1][currentPlace.second])
        }
        if (currentPlace.second != map[currentPlace.first].size-1) {
            result.add(map[currentPlace.first][currentPlace.second + 1])
        }
        if (currentPlace.second != 0) {
            result.add(map[currentPlace.first][currentPlace.second - 1])
        }
        if (currentPlace.first != 0 && currentPlace.second != 0) {
            result.add(map[currentPlace.first - 1][currentPlace.second - 1])
        }
        if (currentPlace.first != map.size-1 && currentPlace.second != map[currentPlace.first].size-1) {
            result.add(map[currentPlace.first + 1][currentPlace.second + 1])
        }
        if (currentPlace.first != 0 && currentPlace.second != map[currentPlace.first].size-1) {
            result.add(map[currentPlace.first - 1][currentPlace.second + 1])
        }
        if (currentPlace.first != map.size-1 && currentPlace.second != 0) {
            result.add(map[currentPlace.first + 1][currentPlace.second - 1])
        }

        return result
    }

    private fun calculateAllNeighbors(currentPlace: Pair<Int, Int>, map: List<List<SeatState>>): List<SeatState> {
        val result = mutableListOf<SeatState>()

        if (currentPlace.first != 0) {
            var counter = currentPlace.first-1
            var visibleSeat = SeatState.FLOOR
            while (counter >= 0 && visibleSeat == SeatState.FLOOR) {
                if (map[counter][currentPlace.second] != SeatState.FLOOR) {
                    visibleSeat = map[counter][currentPlace.second]
                }
                counter-=1
            }
            result.add(visibleSeat)
        }
        if (currentPlace.first != map.size-1) {
            var counter = currentPlace.first+1
            var visibleSeat = SeatState.FLOOR
            while (counter < map.size && visibleSeat == SeatState.FLOOR) {
                if (map[counter][currentPlace.second] != SeatState.FLOOR) {
                    visibleSeat = map[counter][currentPlace.second]
                }
                counter+=1
            }
            result.add(visibleSeat)
        }
        if (currentPlace.second != map[currentPlace.first].size-1) {
            var counter = currentPlace.second+1
            var visibleSeat = SeatState.FLOOR
            while (counter < map[currentPlace.first].size && visibleSeat == SeatState.FLOOR) {
                if (map[currentPlace.first][counter] != SeatState.FLOOR) {
                    visibleSeat = map[currentPlace.first][counter]
                }
                counter+=1
            }
            result.add(visibleSeat)
        }
        if (currentPlace.second != 0) {
            var counter = currentPlace.second-1
            var visibleSeat = SeatState.FLOOR
            while (counter >= 0 && visibleSeat == SeatState.FLOOR) {
                if (map[currentPlace.first][counter] != SeatState.FLOOR) {
                    visibleSeat = map[currentPlace.first][counter]
                }
                counter-=1
            }
            result.add(visibleSeat)
        }

        if (currentPlace.first != 0 && currentPlace.second != 0) {
            var counterFirst = currentPlace.first-1
            var counterSecond = currentPlace.second-1
            var visibleSeat = SeatState.FLOOR
            while (counterFirst >= 0 && counterSecond >= 0 && visibleSeat == SeatState.FLOOR) {
                if (map[counterFirst][counterSecond] != SeatState.FLOOR) {
                    visibleSeat = map[counterFirst][counterSecond]
                }
                counterFirst-=1
                counterSecond-=1
            }
            result.add(visibleSeat)
        }

        if (currentPlace.first != map.size-1 && currentPlace.second != map[currentPlace.first].size-1) {
            var counterFirst = currentPlace.first+1
            var counterSecond = currentPlace.second+1
            var visibleSeat = SeatState.FLOOR
            while (counterFirst < map.size && counterSecond < map[currentPlace.first].size && visibleSeat == SeatState.FLOOR) {
                if (map[counterFirst][counterSecond] != SeatState.FLOOR) {
                    visibleSeat = map[counterFirst][counterSecond]
                }
                counterFirst+=1
                counterSecond+=1
            }
            result.add(visibleSeat)
        }
        if (currentPlace.first != 0 && currentPlace.second != map[currentPlace.first].size-1) {
            var counterFirst = currentPlace.first-1
            var counterSecond = currentPlace.second+1
            var visibleSeat = SeatState.FLOOR
            while (counterFirst >= 0 && counterSecond < map[currentPlace.first].size && visibleSeat == SeatState.FLOOR) {
                if (map[counterFirst][counterSecond] != SeatState.FLOOR) {
                    visibleSeat = map[counterFirst][counterSecond]
                }
                counterFirst-=1
                counterSecond+=1
            }
            result.add(visibleSeat)
        }
        if (currentPlace.first != map.size-1 && currentPlace.second != 0) {
            var counterFirst = currentPlace.first+1
            var counterSecond = currentPlace.second-1
            var visibleSeat = SeatState.FLOOR
            while (counterFirst < map.size && counterSecond >= 0 && visibleSeat == SeatState.FLOOR) {
                if (map[counterFirst][counterSecond] != SeatState.FLOOR) {
                    visibleSeat = map[counterFirst][counterSecond]
                }
                counterFirst+=1
                counterSecond-=1
            }
            result.add(visibleSeat)
        }

        return result
    }
}

enum class SeatState(val symbol: Char) {
    FLOOR('.'), OCCUPIED('#'), FREE('L');

    fun calculateNewState(neighbor: List<SeatState>): SeatState {
        if (this == FLOOR) return this
        if (this == FREE && neighbor.filter { it == OCCUPIED }.count() == 0) return OCCUPIED
        if (this == OCCUPIED && neighbor.filter { it == OCCUPIED }.count() > 3) return FREE
        return this
    }

    fun calculateNewStatePart2(neighbor: List<SeatState>): SeatState {
        if (this == FLOOR) return this
        if (this == FREE && neighbor.filter { it == OCCUPIED }.count() == 0) return OCCUPIED
        if (this == OCCUPIED && neighbor.filter { it == OCCUPIED }.count() > 4) return FREE
        return this
    }

    companion object {
        fun ofSymbol(symbol: Char): SeatState {
            if (symbol == OCCUPIED.symbol) return OCCUPIED
            if (symbol == FREE.symbol) return FREE
            return FLOOR
        }
    }
}