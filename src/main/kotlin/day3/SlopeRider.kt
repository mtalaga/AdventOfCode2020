package day3

class SlopeRider (val map: List<CharArray>) {

    private val tree = '#'

    fun countTreesOnRoad(move: Move): Int {
        val patternLength = map[0].size
        var currentRow = 0
        var currentPosition = 0;
        var trees = 0

        while (currentRow < map.size-1) {
            currentPosition = (currentPosition+move.right) % patternLength
            currentRow += move.down

            if (map[currentRow][currentPosition] == tree) {
                trees+=1
            }
        }

        return trees
    }
}

data class Move(val right: Int, val down: Int)