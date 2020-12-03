package day2

class PasswordWithPolicy(val possiblePositionOne: Int, val possiblePositionTwo: Int, val character: Char, val password: String) {

    fun isPasswordValid(): Boolean {
        if (possiblePositionOne > password.length) {
            return false
        }

        var validPassword = false

        if (password[possiblePositionOne-1] == character) {
            validPassword = !validPassword
        }

        if (possiblePositionTwo <= password.length && password[possiblePositionTwo-1] == character) {
            validPassword = !validPassword
        }

        return validPassword
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PasswordWithPolicy

        if (possiblePositionOne != other.possiblePositionOne) return false
        if (possiblePositionTwo != other.possiblePositionTwo) return false
        if (character != other.character) return false
        if (password != other.password) return false

        return true
    }

    override fun hashCode(): Int {
        var result = possiblePositionOne
        result = 31 * result + possiblePositionTwo
        result = 31 * result + character.hashCode()
        result = 31 * result + password.hashCode()
        return result
    }
}
