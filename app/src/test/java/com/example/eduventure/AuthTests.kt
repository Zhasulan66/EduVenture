package com.example.eduventure

import com.example.eduventure.presentation.screens.auth.isPasswordValid
import com.example.eduventure.presentation.screens.auth.isValidEmail
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class AuthTests {

    @Test
    fun isValidEmail_inputValidEmail_returnsTrue(){
        val email = "example@email.com"
        assertTrue(isValidEmail(email))
    }

    @Test
    fun given_shortPassword_when_isPasswordValid_then_returnsFalse(){
        val password = "hello12"
        assertFalse(isPasswordValid(password))
    }
}