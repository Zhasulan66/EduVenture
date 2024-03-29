package com.example.eduventure.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eduventure.presentation.LanguageManager
import com.example.eduventure.presentation.screens.*

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val languageManager = LanguageManager(LocalContext.current)

    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route
    ) {

        //SplashScreen
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(
                navController = navController
            )
        }

        //LoginScreen
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(
                navController = navController,
                languageManager = languageManager
            )
        }

        //RegistrationScreen
        composable(route = Screen.RegistrationScreen.route) {
            RegistrationScreen(
                navController = navController
            )
        }

        //VerifyEmailScreen
        /*composable(route = Screen.VerifyEmailScreen.route+ "/{email}",
            arguments = listOf(
                navArgument("email"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                }
            )
        ) { entry ->
            VerifyEmailScreen(
                navController = navController,
                userEmail = entry.arguments!!.getString("email").toString()
            )
        }*/

        //HomeScreen
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(
                navController = navController
            )
        }

        //UniversityScreen
        composable(route = Screen.UniversityScreen.route) {
            UniversityScreen(
                navController = navController
            )
        }

        //InternshipScreen
        composable(route = Screen.InternshipScreen.route) {
            InternshipScreen(
                navController = navController
            )
        }

        //ProfileScreen
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen(
                navController = navController
            )
        }
    }
}