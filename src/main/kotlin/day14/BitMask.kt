package day14

class BitMask(private val memory: List<Memory>) {

    fun decodeValuesAndSumNonZeroMemory(): Long {
        val memoryState = mutableMapOf<Int, Long>()

        for (chunk in memory) {
            for (element in chunk.addressChanges) {
                var elementInBinaryValue = Integer.toBinaryString(element.second)
                elementInBinaryValue = addLeadingZeros(36, elementInBinaryValue)
                for (maskChange in chunk.mask) {
                    if (maskChange.second != BitMaskCharacter.X) {
                        elementInBinaryValue = elementInBinaryValue.replaceRange(
                            IntRange(
                                elementInBinaryValue.length - maskChange.first - 1,
                                elementInBinaryValue.length - maskChange.first - 1
                            ), maskChange.second.character.toString()
                        )
                    }
                }
                memoryState[element.first] = elementInBinaryValue.toLong(2)
            }
        }

        return memoryState.values.sum()
    }

    fun decodeMemoryAddressAndSumNonZeroValues(): Long {
        val memoryState = mutableMapOf<Long, Long>()

        for (chunk in memory) {
            for (element in chunk.addressChanges) {
                var memoryAddressInBinaryValue = Integer.toBinaryString(element.first)
                memoryAddressInBinaryValue = addLeadingZeros(36, memoryAddressInBinaryValue)
                val memoryAddress = mutableListOf(memoryAddressInBinaryValue)
                for (maskChange in chunk.mask) {
                    for (i in 0 until memoryAddress.size) {
                        if (maskChange.second == BitMaskCharacter.ONE) {
                            memoryAddress[i] = memoryAddress[i].replaceRange(
                                IntRange(
                                    memoryAddressInBinaryValue.length - maskChange.first - 1,
                                    memoryAddressInBinaryValue.length - maskChange.first - 1
                                ), maskChange.second.character.toString()
                            )
                        } else if (maskChange.second == BitMaskCharacter.X) {
                            memoryAddress[i] = memoryAddress[i].replaceRange(
                                IntRange(
                                    memoryAddressInBinaryValue.length - maskChange.first - 1,
                                    memoryAddressInBinaryValue.length - maskChange.first - 1
                                ), "1"
                            )
                            memoryAddress.add(memoryAddress[i].replaceRange(
                                IntRange(
                                    memoryAddressInBinaryValue.length - maskChange.first - 1,
                                    memoryAddressInBinaryValue.length - maskChange.first - 1
                                ), "0"
                            ))
                        }
                    }
                }
                for (address in memoryAddress) {
                    memoryState[address.toLong(2)] = element.second.toLong()
                }
            }
        }

        return memoryState.values.sum()
    }

    private fun addLeadingZeros(numberOfBits: Int, value: String): String {
        val zerosLength = numberOfBits - value.length
        val zero = "0".repeat(zerosLength)
        return zero + value
    }
}

data class Memory(val mask: List<Pair<Int, BitMaskCharacter>>, val addressChanges: List<Pair<Int, Int>>)

enum class BitMaskCharacter(val character: Char) {
    ONE('1'), ZERO('0'), X('X');

    companion object {
        fun fromChar(char: Char): BitMaskCharacter {
            return when (char) {
                '1' -> ONE
                '0' -> ZERO
                'X' -> X
                else -> throw Exception("Not parseable character")
            }
        }
    }
}