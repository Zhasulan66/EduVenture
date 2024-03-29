package com.example.eduventure.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.eduventure.R
import com.example.eduventure.presentation.navigation.Screen
import com.example.eduventure.presentation.ui.theme.Purple88
import com.example.eduventure.presentation.ui.theme.PurpleDark
import com.example.eduventure.presentation.ui.theme.PurpleLight
import com.example.eduventure.presentation.viewmodels.MainViewModel

@Composable
fun NavigationView(
    modifier: Modifier,
    navController: NavController,
    focusedOffset: Int
){

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(88.dp)
            .then(modifier)
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(84.dp)
                .background(PurpleDark)
                .align(Alignment.BottomCenter)
        )

        Box(
            modifier = Modifier
                .width(88.dp)
                .aspectRatio(1f)
                .offset(x = focusedOffset.dp)
                .clip(CircleShape)
                .background(Purple88)
                .border(
                    width = 1.dp,
                    color = PurpleLight,
                    shape = CircleShape
                )

        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(84.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //home
            Box(
                modifier = Modifier
                    .width(48.dp)
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .background(Color.White)
                    .clickable {
                        navController.navigate(Screen.HomeScreen.route)
                    },
                contentAlignment = Alignment.Center
            ){
                Image(
                    painter = painterResource(id = R.drawable.nav_home_icon),
                    contentDescription = "icon"
                )
            }

            //univer
            Box(
                modifier = Modifier
                    .width(48.dp)
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .background(Color.White)
                    .clickable {
                        navController.navigate(Screen.UniversityScreen.route)
                    },
                contentAlignment = Alignment.Center
            ){
                Image(
                    painter = painterResource(id = R.drawable.nav_univer_icon),
                    contentDescription = "icon"
                )
            }

            //intern
            Box(
                modifier = Modifier
                    .width(48.dp)
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .background(Color.White)
                    .clickable {
                        navController.navigate(Screen.InternshipScreen.route)
                    },
                contentAlignment = Alignment.Center
            ){
                Image(
                    painter = painterResource(id = R.drawable.nav_intern_icon),
                    contentDescription = "icon"
                )
            }

            //profile
            Box(
                modifier = Modifier
                    .width(48.dp)
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .background(Color.White)
                    .clickable {
                        navController.navigate(Screen.ProfileScreen.route)
                    },
                contentAlignment = Alignment.Center
            ){
                Image(
                    painter = painterResource(id = R.drawable.nav_profile_icon),
                    contentDescription = "icon"
                )
            }
        }
    }
}