package com.example.eduventure.presentation.screens

import android.util.Log
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.eduventure.R
import com.example.eduventure.common.Constants.Companion.INTER_FONT_FAMILY
import com.example.eduventure.domain.model.News
import com.example.eduventure.domain.model.Resource
import com.example.eduventure.presentation.LanguageManager
import com.example.eduventure.presentation.components.NavigationView
import com.example.eduventure.presentation.components.NewsCard
import com.example.eduventure.presentation.navigation.Screen
import com.example.eduventure.presentation.ui.theme.PurpleDark
import com.example.eduventure.presentation.ui.theme.PurpleLight
import com.example.eduventure.presentation.viewmodels.MainViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    languageManager: LanguageManager
) {
    val viewModel = hiltViewModel<MainViewModel>()
    val newsState by viewModel.newsState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        //Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                .background(PurpleDark)
                .padding(horizontal = 24.dp),
            contentAlignment = Alignment.CenterStart
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.news),
                    fontSize = 20.sp,
                    fontFamily = INTER_FONT_FAMILY,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                )

                Box(
                    modifier = Modifier
                        .width(104.dp)
                        .height(24.dp)
                        .shadow(4.dp, RoundedCornerShape(12.dp))
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White)
                        .clickable {
                            if (languageManager.getCurrentLanguage() == "kk") {
                                languageManager.setLanguage("ru")
                                languageManager.restartActivity()
                            } else {
                                languageManager.setLanguage("kk")
                                languageManager.restartActivity()
                            }

                        }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.language),
                            fontSize = 14.sp,
                            fontFamily = INTER_FONT_FAMILY,
                            fontWeight = FontWeight.SemiBold,
                            color = PurpleLight,
                        )
                        Icon(
                            imageVector = Icons.Rounded.KeyboardArrowDown,
                            contentDescription = "icon",
                            tint = PurpleLight
                        )
                    }
                }
            }
        }

        when (newsState) {
            is Resource.Loading -> {
                LoadingScreen()
            }

            is Resource.Error -> {
                ErrorScreen(
                    modifier = Modifier,
                    retryAction = {
                        navController.popBackStack()
                    }
                )
            }

            is Resource.Success -> {
                val newsList = (newsState as Resource.Success<List<News>>).data
                NewsListScreen(navController, newsList)
            }
            else -> {
                viewModel.fetchAllNews()
            }
        }

        val screenWidth = LocalConfiguration.current.screenWidthDp
        NavigationView(
            modifier = Modifier.align(Alignment.BottomCenter),
            navController = navController,
            focusedOffset = screenWidth/17 //24
        )


    }

}

@Composable
fun NewsListScreen(
    navController: NavController,
    newsList: List<News>,
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 64.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
        items(newsList.size) { index ->
            NewsCard(
                newsList[index]
            ) {
                navController.navigate(Screen.NewsInfoScreen.route + "/${newsList[index].id}")
            }
            Spacer(modifier = Modifier.height(40.dp))
        }

    }


}