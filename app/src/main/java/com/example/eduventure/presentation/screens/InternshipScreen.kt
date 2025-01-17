package com.example.eduventure.presentation.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.eduventure.R
import com.example.eduventure.common.Constants
import com.example.eduventure.data.local.LocalCountryListData
import com.example.eduventure.domain.model.Internship
import com.example.eduventure.domain.model.Profession
import com.example.eduventure.domain.model.Resource
import com.example.eduventure.domain.model.University
import com.example.eduventure.presentation.components.InternshipCard
import com.example.eduventure.presentation.components.NavigationView
import com.example.eduventure.presentation.components.UniversityCard
import com.example.eduventure.presentation.navigation.Screen
import com.example.eduventure.presentation.ui.theme.PurpleDark
import com.example.eduventure.presentation.viewmodels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InternshipScreen(
    navController: NavController
) {

    val viewModel = hiltViewModel<MainViewModel>()
    val internshipState by viewModel.internshipState.collectAsState()
    val professionState by viewModel.professionState.collectAsState()

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

            Text(
                text = stringResource(id = R.string.internship),
                fontSize = 20.sp,
                fontFamily = Constants.INTER_FONT_FAMILY,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
            )
        }

        when (internshipState) {
            is Resource.Loading -> {
                LoadingScreen()
                viewModel.fetchAllProfession()
                viewModel.fetchAllInternships(0)
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
                val internshipList = (internshipState as Resource.Success<List<Internship>>).data
                val professionList = (professionState as Resource.Success<List<Profession>>).data
                InternshipListScreen(navController, internshipList, professionList, viewModel)
            }
            else -> {}
        }

        val screenWidth = LocalConfiguration.current.screenWidthDp
        NavigationView(
            modifier = Modifier.align(Alignment.BottomCenter),
            navController = navController,
            focusedOffset = (screenWidth/2).toInt() //206
        )
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InternshipListScreen(
    navController: NavController,
    internshipList: List<Internship>,
    professionList: List<Profession>,
    viewModel: MainViewModel
){
    var userSearch by remember { mutableStateOf("") }

    var expandedProfession by remember { mutableStateOf(false) }
    var selectedProfessionOption by remember { mutableStateOf("All") }

    val sortedInternshipList =
        if(userSearch.isNotEmpty()) {
            internshipList.filter { internship ->
                internship.title.startsWith(userSearch, ignoreCase = true)
            }
        } else internshipList

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
                            fontFamily = Constants.INTER_FONT_FAMILY,
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
                            .clickable { expandedProfession = true },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = selectedProfessionOption,
                            fontFamily = Constants.INTER_FONT_FAMILY,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }

                    DropdownMenu(
                        expanded = expandedProfession,
                        onDismissRequest = { expandedProfession = false },
                        modifier = Modifier
                            .fillMaxWidth(0.4f)
                            .fillMaxHeight(0.6f)
                            .background(Color.White)
                            .align(Alignment.End)
                    ) {
                        DropdownMenuItem(
                            text = { Text("All") },
                            onClick = {
                                selectedProfessionOption = "All"
                                expandedProfession = false
                                viewModel.fetchAllInternships(profession = 0)
                            },
                        )
                        professionList.forEach { profession ->
                            DropdownMenuItem(
                                text = { Text(profession.name) },
                                onClick = {
                                    selectedProfessionOption = profession.name
                                    expandedProfession = false
                                    viewModel.fetchAllInternships(profession = profession.id)
                                },
                            )
                        }
                    }

                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
        items(items = sortedInternshipList){ internship ->
            InternshipCard(internship){
                navController.navigate(Screen.InternshipInfoScreen.route +"/${internship.id}")
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
        item{
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}