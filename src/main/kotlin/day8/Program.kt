package day8

import java.util.function.BiFunction

class Program (private val operations: List<Operation>) {

    fun findAccumulatorValueBeforeLoop() : ProgramState {
        return calculateProgramState(this.operations)
    }

    fun fixProgramWithLoop() : ProgramState {
        var counter = 0

        while (counter < operations.size) {
            if (operations[counter].name != "acc") {
                val newOperations = ArrayList(operations.map { it.copy() })
                if (newOperations[counter].name == "jmp") {
                    newOperations[counter].name = "nop"
                } else {
                    newOperations[counter].name = "jmp"
                }

                val state = calculateProgramState(newOperations)
                if (state.counter == newOperations.size) {
                    return state
                }
                counter += 1
            } else {
                counter += 1
            }
        }

        return ProgramState(-1, -1)
    }

    private fun calculateProgramState(operations: List<Operation>) : ProgramState {
        val visitedOperation = BooleanArray(operations.size)
        var programState = ProgramState(0,0)

        while (true) {
            if (programState.counter == operations.size) {
                return programState
            }

            val currentOperation = operations[programState.counter]
            val newState = Instruction.valueOf(currentOperation.name).apply(programState, currentOperation.value)
            if (newState.counter < operations.size && !visitedOperation[newState.counter]) {
                visitedOperation[newState.counter] = true
            } else if (newState.counter != operations.size) {
                return programState
            }

            programState = newState
        }
    }
}

enum class Instruction : BiFunction<ProgramState, Int, ProgramState> {
    acc {
        override fun apply(t: ProgramState, u: Int): ProgramState {
            val newAccumulator  = t.accumulator + u
            val newCounter = t.counter + 1
            return ProgramState(newCounter, newAccumulator)
        }
        },
    jmp {
        override fun apply(t: ProgramState, u: Int): ProgramState {
            val newCounter  = t.counter + u
            return ProgramState(newCounter, t.accumulator)
        }
       },
    nop {
        override fun apply(t: ProgramState, u: Int): ProgramState {
            val newCounter = t.counter + 1
            return ProgramState(newCounter, t.accumulator)
        }
    }
}

data class Operation(var name: String, val value: Int)

data class ProgramState(var counter: Int, var accumulator: Int)

