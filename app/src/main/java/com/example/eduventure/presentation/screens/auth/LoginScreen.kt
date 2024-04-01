package com.example.eduventure.presentation.screens.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.eduventure.R
import com.example.eduventure.common.Constants.Companion.INTER_FONT_FAMILY
import com.example.eduventure.domain.model.Auth.TokenResponse
import com.example.eduventure.domain.model.Auth.UserLogin
import com.example.eduventure.domain.model.Auth.UserRequest
import com.example.eduventure.domain.model.Auth.UserResponse
import com.example.eduventure.domain.model.Auth.VerificationRequest
import com.example.eduventure.domain.model.Resource
import com.example.eduventure.presentation.LanguageManager
import com.example.eduventure.presentation.navigation.Screen
import com.example.eduventure.presentation.screens.ErrorScreen
import com.example.eduventure.presentation.screens.LoadingScreen
import com.example.eduventure.presentation.ui.theme.BlueDark
import com.example.eduventure.presentation.ui.theme.PurpleLight
import com.example.eduventure.presentation.viewmodels.AuthViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    languageManager: LanguageManager
) {

    val viewModel = hiltViewModel<AuthViewModel>()
    val loginState by viewModel.loginState.collectAsState()

    Box(modifier = Modifier
        .fillMaxSize()){

        when(loginState){
            is Resource.Initial -> {
                LoginFields(navController, languageManager, viewModel)
            }
            is Resource.Loading -> {
                LoadingScreen()
            }
            is Resource.Error -> {
                ErrorScreen(modifier = Modifier, retryAction = {
                    navController.navigate(Screen.LoginScreen.route)
                })
            }
            is Resource.Success -> {
                val tokenResponse = (loginState as Resource.Success<TokenResponse>).data
                navController.navigate(Screen.HomeScreen.route){
                    popUpTo(Screen.RegistrationScreen.route){ inclusive = true }
                }
                viewModel.loginSuccess()

            }
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginFields(
    navController: NavController,
    languageManager: LanguageManager,
    viewModel: AuthViewModel
){
    var userEmail by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    var isPasswordHide by remember { mutableStateOf(true) }

    var isUserEmailIncorrect by remember { mutableStateOf(false) }
    var isUserPasswordIncorrect by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(R.drawable.city_bg),
            contentDescription = "img",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .padding(horizontal = 24.dp)
                .alpha(0.5f)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.Black)
                .border(
                    width = 2.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp)
                )
                .align(Alignment.Center)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .padding(horizontal = 24.dp)
                .align(Alignment.Center)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.84f)
                    .align(Alignment.CenterHorizontally)
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(R.string.entrance),
                    fontSize = 32.sp,
                    fontFamily = INTER_FONT_FAMILY,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "Kaz",
                        fontSize = 16.sp,
                        fontFamily = INTER_FONT_FAMILY,
                        fontWeight = FontWeight.SemiBold,
                        color = if (languageManager.getCurrentLanguage() == "kk") PurpleLight else Color.White,
                        modifier = Modifier
                            .clickable {
                                languageManager.setLanguage("kk")
                                languageManager.restartActivity()
                            }
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Рус",
                        fontSize = 16.sp,
                        fontFamily = INTER_FONT_FAMILY,
                        fontWeight = FontWeight.SemiBold,
                        color = if (languageManager.getCurrentLanguage() == "kk") Color.White else PurpleLight,
                        modifier = Modifier
                            .clickable {
                                languageManager.setLanguage("ru")
                                languageManager.restartActivity()
                            }
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(R.string.mail),
                    fontSize = 20.sp,
                    fontFamily = INTER_FONT_FAMILY,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    value = userEmail,
                    onValueChange = { text ->
                        userEmail = text
                    },
                    textStyle = TextStyle(
                        color = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .border(
                            width = 1.dp,
                            color = if(isUserEmailIncorrect) Color.Red else Color.White,
                            shape = RoundedCornerShape(50)
                        ),
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.mail_placeholder),
                            fontSize = 14.sp,
                            fontFamily = INTER_FONT_FAMILY,
                            color = Color.White,
                        )
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(R.string.password),
                    fontSize = 20.sp,
                    fontFamily = INTER_FONT_FAMILY,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    value = userPassword,
                    onValueChange = { text ->
                        userPassword = text
                    },
                    textStyle = TextStyle(
                        color = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .border(
                            width = 1.dp,
                            color = if(isUserPasswordIncorrect) Color.Red else Color.White,
                            shape = RoundedCornerShape(50)
                        ),
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.password),
                            fontSize = 14.sp,
                            fontFamily = INTER_FONT_FAMILY,
                            color = Color.White,
                        )
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    visualTransformation = if (isPasswordHide) PasswordVisualTransformation()
                    else VisualTransformation.None,
                    trailingIcon = {
                        Icon(
                            painter = if (isPasswordHide) painterResource(id = R.drawable.baseline_visibility_off_24)
                            else painterResource(id = R.drawable.baseline_visibility_24),
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier.clickable {
                                isPasswordHide = !isPasswordHide
                            })
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))

                //Forgot password btn
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = stringResource(R.string.forgot_password),
                        fontSize = 16.sp,
                        fontFamily = INTER_FONT_FAMILY,
                        fontWeight = FontWeight.SemiBold,
                        color = PurpleLight,
                        textAlign = TextAlign.End,
                        modifier = Modifier
                            .clickable {
                                navController.navigate(Screen.ForgotPasswordScreen.route)
                            }
                        )
                }
                Spacer(modifier = Modifier.height(40.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .clip(RoundedCornerShape(50))
                        .background(
                            brush = Brush.horizontalGradient(
                                listOf(PurpleLight, BlueDark)
                            )
                        )
                        .clickable {
                            isUserEmailIncorrect = userEmail.isEmpty()
                            isUserPasswordIncorrect = userPassword.isEmpty()
                            if (!isValidEmail(userEmail)) {
                                isUserEmailIncorrect = true
                            }
                            if (!isPasswordValid(userPassword)) {
                                isUserPasswordIncorrect = true
                            }
                            if (!isUserEmailIncorrect && !isUserPasswordIncorrect) {
                                viewModel.loginUser(UserLogin(userEmail, userPassword))
                            }

                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.enter),
                        fontSize = 20.sp,
                        fontFamily = INTER_FONT_FAMILY,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.dont_have_account),
                        fontSize = 16.sp,
                        fontFamily = INTER_FONT_FAMILY,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stringResource(R.string.registration),
                        fontSize = 16.sp,
                        fontFamily = INTER_FONT_FAMILY,
                        fontWeight = FontWeight.SemiBold,
                        color = PurpleLight,
                        modifier = Modifier
                            .clickable {
                                navController.navigate(Screen.RegistrationScreen.route) {
                                    popUpTo(Screen.LoginScreen.route) { inclusive = true }
                                }
                            }

                    )
                }
            }
        }
    }
}