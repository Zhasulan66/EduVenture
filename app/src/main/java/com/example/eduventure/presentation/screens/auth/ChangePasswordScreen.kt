package com.example.eduventure.presentation.screens.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.example.eduventure.domain.model.Auth.ResetPasswordRequest
import com.example.eduventure.domain.model.Auth.VerifyCodeRequest
import com.example.eduventure.domain.model.Resource
import com.example.eduventure.presentation.navigation.Screen
import com.example.eduventure.presentation.screens.ErrorScreen
import com.example.eduventure.presentation.screens.LoadingScreen
import com.example.eduventure.presentation.ui.theme.BlueDark
import com.example.eduventure.presentation.ui.theme.MyGray
import com.example.eduventure.presentation.ui.theme.NeutralColor
import com.example.eduventure.presentation.ui.theme.PurpleLight
import com.example.eduventure.presentation.viewmodels.AuthViewModel


@Composable
fun ChangePasswordScreen(
    navController: NavController,
    userEmail: String,
    code: String
){

    val viewModel = hiltViewModel<AuthViewModel>()
    val resetPasswordState by viewModel.resetPasswordState.collectAsState()

    when(resetPasswordState){
        is Resource.Loading -> {
            LoadingScreen()
        }
        is Resource.Error -> {
            ErrorScreen(modifier = Modifier, retryAction = {
                navController.popBackStack()
            })
        }
        is Resource.Success -> {
            Toast.makeText(LocalContext.current, "Password changed!", Toast.LENGTH_SHORT).show()
            navController.navigate(Screen.LoginScreen.route){
                popUpTo(Screen.ChangePasswordScreen.route){
                    inclusive = true
                }
            }
            viewModel.resetPasswordSuccess()
        }
        is Resource.Initial -> {
            ChangePasswordField(
                navController = navController,
                viewModel = viewModel,
                userEmail = userEmail,
                code = code
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordField(
    navController: NavController,
    viewModel: AuthViewModel,
    userEmail: String,
    code: String
){
    var userPassword by remember { mutableStateOf("") }
    var userPassword2 by remember { mutableStateOf("") }

    var isPasswordHide by remember { mutableStateOf(true) }
    var isPasswordHide2 by remember { mutableStateOf(true) }

    var isUserPasswordIncorrect by remember { mutableStateOf(false) }
    var isUserPassword2Incorrect by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        //Back Button
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(NeutralColor)
                    .clickable {
                        navController.navigate(Screen.LoginScreen.route){
                            popUpTo(Screen.ChangePasswordScreen.route)
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = ""
                )
            }
        }
        Spacer(modifier = Modifier.height(22.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = stringResource(id = R.string.create_new_password),
                fontFamily = INTER_FONT_FAMILY,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(22.dp))

            //Password
            Text(
                text = stringResource(id = R.string.password),
                fontFamily = INTER_FONT_FAMILY,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = userPassword,
                onValueChange = { text ->
                    userPassword = text
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .border(
                        width = 1.dp,
                        color = if (isUserPasswordIncorrect) Color.Red else MyGray,
                        shape = RoundedCornerShape(12.dp)
                    ),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.password),
                        fontSize = 14.sp,
                        fontFamily = INTER_FONT_FAMILY,
                        color = MyGray,
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
                        tint = MyGray,
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
            Spacer(modifier = Modifier.height(16.dp))

            //Password2
            TextField(
                value = userPassword2,
                onValueChange = { text ->
                    userPassword2 = text
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .border(
                        width = 1.dp,
                        color = if (isUserPassword2Incorrect) Color.Red else MyGray,
                        shape = RoundedCornerShape(12.dp)
                    ),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.confirm_password),
                        fontSize = 14.sp,
                        fontFamily = INTER_FONT_FAMILY,
                        color = MyGray,
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                visualTransformation = if (isPasswordHide2) PasswordVisualTransformation()
                else VisualTransformation.None,
                trailingIcon = {
                    Icon(
                        painter = if (isPasswordHide2) painterResource(id = R.drawable.baseline_visibility_off_24)
                        else painterResource(id = R.drawable.baseline_visibility_24),
                        contentDescription = "",
                        tint = MyGray,
                        modifier = Modifier.clickable {
                            isPasswordHide2 = !isPasswordHide2
                        })
                },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(22.dp))

            //Continue button
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
                        isUserPasswordIncorrect = userPassword.isEmpty()
                        isUserPassword2Incorrect = userPassword2.isEmpty()
                        if (userPassword != userPassword2) {
                            isUserPasswordIncorrect = true
                            isUserPassword2Incorrect = true
                        }
                        if (!isUserPasswordIncorrect && !isUserPassword2Incorrect) {
                            viewModel.resetPassword(ResetPasswordRequest(code, userEmail, userPassword, userPassword2))
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.register_continue),
                    fontSize = 20.sp,
                    fontFamily = INTER_FONT_FAMILY,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }


    }
}