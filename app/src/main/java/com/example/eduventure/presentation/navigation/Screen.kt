package com.example.eduventure.presentation.navigation

sealed class Screen(val route: String) {

    data object SplashScreen: Screen("splash_screen")

    data object LoginScreen : Screen("login_screen")

    data object RegistrationScreen : Screen("registration_screen")

    data object VerifyEmailScreen : Screen("verify_email_screen")

    data object HomeScreen : Screen("home_screen")
    data object UniversityScreen : Screen("university_screen")
    data object InternshipScreen : Screen("internship_screen")
    data object ProfileScreen : Screen("profile_screen")
}