package com.example.eduventure.presentation.screens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.eduventure.R
import com.example.eduventure.common.Constants
import com.example.eduventure.common.Constants.Companion.INTER_FONT_FAMILY
import com.example.eduventure.data.local.LocalCountryListData
import com.example.eduventure.domain.model.News
import com.example.eduventure.domain.model.Resource
import com.example.eduventure.domain.model.University
import com.example.eduventure.presentation.components.NavigationView
import com.example.eduventure.presentation.components.NewsCard
import com.example.eduventure.presentation.components.UniversityCard
import com.example.eduventure.presentation.navigation.Screen
import com.example.eduventure.presentation.ui.theme.PurpleDark
import com.example.eduventure.presentation.ui.theme.PurpleLight
import com.example.eduventure.presentation.viewmodels.MainViewModel


@Composable
fun UniversityScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<MainViewModel>()
    val universityState by viewModel.universityState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
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
                    text = stringResource(id = R.string.universities),
                    fontSize = 20.sp,
                    fontFamily = INTER_FONT_FAMILY,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                )
            }
        }

        when (universityState) {
            is Resource.Loading -> {
                LoadingScreen()
                viewModel.fetchAllUniversities("All")
            }

            is Resource.Error -> {
                ErrorScreen(
                    modifier = Modifier,
                    retryAction = {
                        navController.navigate(Screen.UniversityScreen.route)
                    }
                )
            }

            is Resource.Success -> {
                val universityList = (universityState as Resource.Success<List<University>>).data
                UniversityListScreen(navController, universityList, viewModel)
            }
            else -> {}
        }



        NavigationView(
            modifier = Modifier.align(Alignment.BottomCenter),
            navController = navController,
            focusedOffset = 114
        )
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UniversityListScreen(
    navController: NavController,
    universityList: List<University>,
    viewModel: MainViewModel
){
    var userSearch by remember { mutableStateOf("") }

    var expandedCountry by remember { mutableStateOf(false) }
    var selectedCountryOption by remember { mutableStateOf("All") }

    val sortedUniversityList =
        if(userSearch.isNotEmpty()) {
            universityList.filter { university ->
                university.name.startsWith(userSearch, ignoreCase = true)
            }
        } else universityList

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 64.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextField(
                    value = userSearch,
                    onValueChange = { text ->
                        userSearch = text
                    },
                    textStyle = TextStyle(
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .width(240.dp)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(12.dp)),
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.search),
                            fontSize = 14.sp,
                            fontFamily = INTER_FONT_FAMILY,
                            color = Color.Black,
                        )
                    },
                    trailingIcon = {
                        Box(
                            modifier = Modifier
                                .width(60.dp)
                                .fillMaxHeight()
                                .background(PurpleDark)
                                .clickable {  },
                            contentAlignment = Alignment.Center
                        ){
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "icon",
                                tint = Color.White
                            )
                        }
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.LightGray,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )

                Column(
                    modifier = Modifier
                        .width(112.dp)
                        .fillMaxHeight()
                ) {
                    Box(
                        modifier = Modifier
                            .width(112.dp)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(12.dp))
                            .background(PurpleDark)
                            .clickable { expandedCountry = true },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = selectedCountryOption,
                            fontFamily = INTER_FONT_FAMILY,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }

                    DropdownMenu(
                        expanded = expandedCountry,
                        onDismissRequest = { expandedCountry = false },
                        modifier = Modifier
                            .fillMaxWidth(0.4f)
                            .fillMaxHeight(0.6f)
                            .background(Color.White)
                            .align(Alignment.End)
                    ) {
                        LocalCountryListData.countryList.forEach { country ->
                            DropdownMenuItem(
                                text = { Text(country) },
                                onClick = {
                                    selectedCountryOption = country
                                    expandedCountry = false
                                    viewModel.fetchAllUniversities(country = country)
                                },
                            )
                        }
                    }

                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
        items(sortedUniversityList.size) { index ->
            UniversityCard(
                sortedUniversityList[index]
            ) {
                navController.navigate(Screen.UniversityInfoScreen.route +"/${sortedUniversityList[index].id}")
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}