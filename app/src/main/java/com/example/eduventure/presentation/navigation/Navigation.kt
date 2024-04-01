package com.example.eduventure.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.eduventure.presentation.LanguageManager
import com.example.eduventure.presentation.screens.*
import com.example.eduventure.presentation.screens.auth.ChangePasswordScreen
import com.example.eduventure.presentation.screens.auth.ForgotPasswordScreen
import com.example.eduventure.presentation.screens.auth.LoginScreen
import com.example.eduventure.presentation.screens.auth.RegistrationScreen
import com.example.eduventure.presentation.screens.auth.VerifyEmailScreen
import com.example.eduventure.presentation.screens.auth.VerifyPasswordScreen

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
        composable(route = Screen.VerifyEmailScreen.route + "/{email}",
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
        }

        //ForgotPasswordScreen
        composable(route = Screen.ForgotPasswordScreen.route){
            ForgotPasswordScreen(navController = navController)
        }

        //VerifyPasswordScreen
        composable(route = Screen.VerifyPasswordScreen.route + "/{email}",
            arguments = listOf(
                navArgument("email"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                }
            )
        ){ entry ->
            VerifyPasswordScreen(
                navController = navController,
                userEmail = entry.arguments!!.getString("email").toString(),
            )
        }

        //ChangePasswordScreen
        composable(route = Screen.ChangePasswordScreen.route + "/{email}" + "/{code}",
            arguments = listOf(
                navArgument("email"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                },
                navArgument("code"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                }
            )
        ){ entry ->
            ChangePasswordScreen(
                navController = navController,
                userEmail = entry.arguments!!.getString("email").toString(),
                code = entry.arguments!!.getString("code").toString()
            )
        }

        //HomeScreen
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(
                navController = navController,
                languageManager = languageManager
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

        //NewsInfoScreen
        composable(route = Screen.NewsInfoScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.IntType
                    defaultValue = 0
                    nullable = false
                }
            )
        ) { entry ->
            NewsInfoScreen(
                navController = navController,
                newsId = entry.arguments!!.getInt("id")
            )
        }


    }
}