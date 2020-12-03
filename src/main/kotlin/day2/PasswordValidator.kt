package day2

class PasswordValidator(val passwordWithPolicyList: List<PasswordWithPolicy>) {

    fun numberOfValidPasswords(): Int {
        var result = 0
        for (password in passwordWithPolicyList) {
            if (password.isPasswordValid()) {
                result+=1
            }
        }
        return result
    }
}