package com.example.eduventure.presentation.screens.auth

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.eduventure.R
import com.example.eduventure.common.Constants.Companion.INTER_FONT_FAMILY
import com.example.eduventure.domain.model.Auth.VerifyCodeRequest
import com.example.eduventure.domain.model.Resource
import com.example.eduventure.presentation.navigation.Screen
import com.example.eduventure.presentation.screens.ErrorScreen
import com.example.eduventure.presentation.screens.LoadingScreen
import com.example.eduventure.presentation.ui.theme.BlueDark
import com.example.eduventure.presentation.ui.theme.MyGray
import com.example.eduventure.presentation.ui.theme.NeutralColor
import com.example.eduventure.presentation.ui.theme.PurpleDark
import com.example.eduventure.presentation.ui.theme.PurpleLight
import com.example.eduventure.presentation.viewmodels.AuthViewModel

@Composable
fun VerifyPasswordScreen(
    navController: NavController,
    userEmail: String
){
    val viewModel = hiltViewModel<AuthViewModel>()
    val verifyPasswordCodeState by viewModel.verifyPasswordCodeState.collectAsState()

    when(verifyPasswordCodeState){
        is Resource.Loading -> {
            LoadingScreen()
        }
        is Resource.Error -> {
            ErrorScreen(modifier = Modifier, retryAction = {
                navController.popBackStack()
            })
        }
        is Resource.Success -> {
            navController.navigate(Screen.ChangePasswordScreen.route + "/$userEmail" + "/${(verifyPasswordCodeState as Resource.Success<String>).data}"){
                popUpTo(Screen.LoginScreen.route)
            }
            viewModel.verifyResetCodeSuccess()
        }
        is Resource.Initial -> {
            VerifyPasswordField(
                navController = navController,
                viewModel = viewModel,
                userEmail = userEmail
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun VerifyPasswordField(
    navController: NavController,
    viewModel: AuthViewModel,
    userEmail: String
){
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val userCode = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        //Back button
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp)
        ){
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(NeutralColor)
                    .clickable {
                        navController.navigate(Screen.LoginScreen.route){
                            popUpTo(Screen.VerifyPasswordScreen.route){
                                inclusive = true
                            }
                        }
                    },
                contentAlignment = Alignment.Center
            ){
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = ""
                )
            }
        }

        Spacer(modifier = Modifier.height(108.dp))
        Text(
            text = stringResource(id = R.string.enter_verify_code),
            fontFamily = INTER_FONT_FAMILY,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.email_sent_code),
            fontFamily = INTER_FONT_FAMILY,
            fontSize = 12.sp,
            color = MyGray,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = userEmail,
            fontFamily = INTER_FONT_FAMILY,
            fontSize = 12.sp,
            color = MyGray,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(40.dp))
        Box(
            modifier= Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ){
            OtpTextField(codeText = userCode) {
            }

            TextField(
                value = userCode.value,
                onValueChange = {
                    if (it.length <= 4) {
                        userCode.value = it
                        if(it.length == 4){
                            keyboardController?.hide()
                            focusManager.clearFocus()
                        }
                    } else {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }
                    Log.d("CODE", "Now: ${userCode.value}")
                },
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(50.dp)
                    //.focusRequester(textFieldRequester)
                    .alpha(0f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword
                )
            )


        }
        Spacer(modifier = Modifier.height(124.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = stringResource(id = R.string.send_code_again),
                fontFamily = INTER_FONT_FAMILY,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = PurpleDark,
                textAlign = TextAlign.Center,
                modifier = Modifier.clickable {

                }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ){
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
                        if(userCode.value.isNotEmpty()){
                            viewModel.verifyResetCode(VerifyCodeRequest(userCode.value, userEmail))
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