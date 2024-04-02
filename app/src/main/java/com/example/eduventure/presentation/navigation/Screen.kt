package com.example.eduventure.presentation.navigation

sealed class Screen(val route: String) {

    data object SplashScreen: Screen("splash_screen")

    data object LoginScreen : Screen("login_screen")

    data object RegistrationScreen : Screen("registration_screen")

    data object VerifyEmailScreen : Screen("verify_email_screen")

    data object ForgotPasswordScreen : Screen("forgot_password_screen")
    data object VerifyPasswordScreen : Screen("verify_password_screen")
    data object ChangePasswordScreen : Screen("change_password_screen")

    data object HomeScreen : Screen("home_screen")
    data object UniversityScreen : Screen("university_screen")
    data object InternshipScreen : Screen("internship_screen")
    data object ProfileScreen : Screen("profile_screen")

    data object NewsInfoScreen : Screen("news_info_screen")
    data object UniversityInfoScreen : Screen("university_info_screen")
    data object InternshipInfoScreen : Screen("internship_info_screen")




}