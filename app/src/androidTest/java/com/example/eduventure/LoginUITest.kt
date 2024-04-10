package com.example.eduventure

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.eduventure.presentation.navigation.Navigation
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginUITest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun given_validCredentials_when_loginButtonClicked_then_navigateToHomeScreen() {

        composeTestRule.setContent {
            Navigation()
        }

        composeTestRule.onNodeWithText("Username").performTextInput("username")
        composeTestRule.onNodeWithText("Password").performTextInput("password")
        composeTestRule.onNodeWithText("Login").performClick()

        // Verify expected behavior
        composeTestRule.onNodeWithText("News").assertExists()
    }

}