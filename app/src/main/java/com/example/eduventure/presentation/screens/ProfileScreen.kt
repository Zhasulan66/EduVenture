package com.example.eduventure.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import androidx.navigation.NavController
import com.example.eduventure.R
import com.example.eduventure.common.Constants
import com.example.eduventure.presentation.components.NavigationView
import com.example.eduventure.presentation.navigation.Screen
import com.example.eduventure.presentation.ui.theme.BlueDark
import com.example.eduventure.presentation.ui.theme.PurpleDark
import com.example.eduventure.presentation.ui.theme.PurpleLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController
){
    var userEmail by remember { mutableStateOf("") }
    var userPhone by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    var isPasswordHide by remember { mutableStateOf(true) }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_bg),
            contentDescription = "img",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(52.dp))
            //User Image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Image(
                    painter = painterResource(id = R.drawable.person_ava),
                    contentDescription = "img",
                    modifier = Modifier
                        .width(140.dp)
                        .aspectRatio(1f)
                        .clip(CircleShape)
                        .align(Alignment.Center)
                        .border(
                            width = 2.dp,
                            shape = CircleShape,
                            color = Color.White
                        )
                )
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .aspectRatio(1f)
                        .offset(x = 50.dp)
                        .clip(CircleShape)
                        .background(PurpleDark)
                        .align(Alignment.BottomCenter)

                        .clickable { },
                    contentAlignment = Alignment.Center
                ){
                    Icon(
                        imageVector = Icons.Default.Create,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            //User Name
            Text(
                text = "User Name",
                fontSize = 20.sp,
                fontFamily = Constants.INTER_FONT_FAMILY,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(32.dp))

            //info card
            Column(
                modifier = Modifier
                    .shadow(
                        elevation = 10.dp,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .fillMaxWidth(0.8f)
                    .height(424.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                //email
                Text(
                    text = stringResource(id = R.string.mail),
                    fontSize = 16.sp,
                    fontFamily = Constants.INTER_FONT_FAMILY,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(4.dp))
                TextField(
                    value = userEmail,
                    onValueChange = { text ->
                        userEmail = text
                    },
                    textStyle = TextStyle(
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .border(
                            width = 2.dp,
                            color = PurpleDark,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.mail_placeholder),
                            fontSize = 14.sp,
                            fontFamily = Constants.INTER_FONT_FAMILY,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
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

                //phone
                Text(
                    text = stringResource(id = R.string.phone_number),
                    fontSize = 16.sp,
                    fontFamily = Constants.INTER_FONT_FAMILY,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(4.dp))
                TextField(
                    value = userPhone,
                    onValueChange = { text ->
                        userPhone = text
                    },
                    textStyle = TextStyle(
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .border(
                            width = 2.dp,
                            color = PurpleDark,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.phone_number),
                            fontSize = 14.sp,
                            fontFamily = Constants.INTER_FONT_FAMILY,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
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

                //password
                Text(
                    text = stringResource(id = R.string.password),
                    fontSize = 16.sp,
                    fontFamily = Constants.INTER_FONT_FAMILY,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(4.dp))
                TextField(
                    value = userPassword,
                    onValueChange = { text ->
                        userPassword = text
                    },
                    textStyle = TextStyle(
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .border(
                            width = 2.dp,
                            color = PurpleDark,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.password),
                            fontSize = 14.sp,
                            fontFamily = Constants.INTER_FONT_FAMILY,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
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
                            tint = PurpleDark,
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
                Spacer(modifier = Modifier.height(40.dp))

                //save button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .clip(RoundedCornerShape(50))
                        .background(
                            brush = Brush.horizontalGradient(
                                listOf(PurpleLight, BlueDark))
                        )
                        .clickable {

                        },
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = stringResource(R.string.save),
                        fontSize = 20.sp,
                        fontFamily = Constants.INTER_FONT_FAMILY,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }

        }


        NavigationView(
            modifier = Modifier.align(Alignment.BottomCenter),
            navController = navController,
            focusedOffset = 298
        )
    }
}