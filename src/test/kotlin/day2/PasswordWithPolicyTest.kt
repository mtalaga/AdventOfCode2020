package day2

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PasswordWithPolicyTest {

    @Test
    fun validPasswordFirstPositionMatching() {
        //given
        val passswordWithPolicy = PasswordWithPolicy(1, 9, 'a', "aaabbaabbhhhhaaaauu")

        //when
        val result = passswordWithPolicy.isPasswordValid()

        //then
        assertTrue(result)
    }

    @Test
    fun validPasswordSecondPositionMatching() {
        //given
        val passswordWithPolicy = PasswordWithPolicy(1, 9, 'a', "bbuhdsuaauhhusd")

        //when
        val result = passswordWithPolicy.isPasswordValid()

        //then
        assertTrue(result)
    }

    @Test
    fun invalidPasswordBothPositionsMatching() {
        //given
        val passswordWithPolicy = PasswordWithPolicy(1, 9, 'a', "aaaaaaaaaa")

        //when
        val result = passswordWithPolicy.isPasswordValid()

        //then
        assertFalse(result)
    }

    @Test
    fun invalidPassswordNonePositionMatching() {
        //given
        val passswordWithPolicy = PasswordWithPolicy(1, 9, 'a', "bbbbbbbbbbbb")

        //when
        val result = passswordWithPolicy.isPasswordValid()

        //then
        assertFalse(result)
    }
}