package day12

import java.util.function.BiFunction
import kotlin.math.abs

class Guidance {

    var state = State(Direction.EAST, 0, 0)

    fun applyDirectionsToState(directions: List<Pair<Action, Int>>) {
        var newState = state
        for (direction in directions) {
            newState = direction.first.calculateNewState(newState, direction.second)
        }
        state = newState
    }

    fun calculateManhattanDistance(): Int {
        return abs(state.x) + abs(state.y)
    }
}

data class State(val direction: Direction, val x: Int, val y: Int)

interface Action {
    fun calculateNewState(currentState: State, value: Int): State
}

enum class Direction(val position: Int, private val newState: BiFunction<State, Int, State>) : Action {
    NORTH(0, BiFunction { state, value -> State(state.direction, state.x + value, state.y) }),
    EAST(1, BiFunction { state, value -> State(state.direction, state.x, state.y + value) }),
    SOUTH(2, BiFunction { state, value -> State(state.direction, state.x - value, state.y) }),
    WEST(3, BiFunction { state, value -> State(state.direction, state.x, state.y - value ) });

    override fun calculateNewState(currentState: State, value: Int) : State {
        return this.newState.apply(currentState, value)
    }

    companion object {
        fun ofPosition(position: Int): Direction {
            return when(position) {
                0 -> NORTH
                1 -> EAST
                2 -> SOUTH
                3 -> WEST
                else -> throw Exception("Number $position could not be casted")
            }
        }
    }
}

enum class Move(private val newState: BiFunction<State, Int, State>) : Action {
    LEFT({ state, value -> State(Direction.ofPosition(abs(state.direction.position - value / 90 + 4) %4), state.x, state.y) }),
    RIGHT({ state, value -> State(Direction.ofPosition(abs(state.direction.position + value / 90 + 4) %4), state.x, state.y) }),
    FORWARD({ state, value -> state.direction.calculateNewState(state,value)});

    override fun calculateNewState(currentState: State, value: Int) : State {
        return this.newState.apply(currentState, value)
    }
}