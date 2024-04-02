package com.example.eduventure.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.eduventure.R
import com.example.eduventure.common.Constants
import com.example.eduventure.domain.model.Resource
import com.example.eduventure.domain.model.University
import com.example.eduventure.presentation.components.NavigationView
import com.example.eduventure.presentation.navigation.Screen
import com.example.eduventure.presentation.ui.theme.GrayLight
import com.example.eduventure.presentation.ui.theme.PurpleDark
import com.example.eduventure.presentation.ui.theme.PurpleLight
import com.example.eduventure.presentation.viewmodels.MainViewModel

@Composable
fun UniversityInfoScreen(
    navController: NavController,
    universityId: Int
) {
    val viewModel = hiltViewModel<MainViewModel>()
    val universityInfoState by viewModel.universityInfoState.collectAsState()

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
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .width(32.dp)
                        .aspectRatio(1f)
                        .shadow(4.dp, CircleShape)
                        .clip(CircleShape)
                        .background(Color.White)
                        .clickable {
                            navController.popBackStack()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Rounded.KeyboardArrowLeft,
                        contentDescription = "icon",
                        tint = PurpleLight
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = stringResource(id = R.string.universities),
                    fontSize = 20.sp,
                    fontFamily = Constants.INTER_FONT_FAMILY,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                )
            }
        }

        when (universityInfoState) {
            is Resource.Loading -> {
                LoadingScreen()
            }

            is Resource.Error -> {
                ErrorScreen(
                    modifier = Modifier,
                    retryAction = {
                        navController.navigate(Screen.UniversityInfoScreen.route + "/$universityId")
                    }
                )
            }

            is Resource.Success -> {
                val university = (universityInfoState as Resource.Success<University>).data
                UniversitySuccessScreen(university)
            }

            else -> {
                viewModel.fetchUniversityById(universityId)
            }
        }

        NavigationView(
            modifier = Modifier.align(Alignment.BottomCenter),
            navController = navController,
            focusedOffset = 114
        )
    }


}


@Composable
fun UniversitySuccessScreen(
    university: University
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(100.dp))



        LazyColumn {
            item {

                //university logo name
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    Image(
                        painter = if(university.logo != null) {
                            rememberAsyncImagePainter(
                                model = university.logo,
                                placeholder = painterResource(id = R.drawable.img_placeholder),
                            )
                        } else painterResource(id = R.drawable.img_placeholder),
                        contentDescription = "img",
                        modifier = Modifier
                            .width(112.dp)
                            .aspectRatio(1f),
                        contentScale = ContentScale.FillWidth
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ){
                        //name
                        Text(
                            text = university.name,
                            fontSize = 16.sp,
                            fontFamily = Constants.INTER_FONT_FAMILY,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        //location
                        Text(
                            text = university.country + ", " + university.city,
                            fontSize = 14.sp,
                            fontFamily = Constants.INTER_FONT_FAMILY,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

                //university rank
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    Box(
                        modifier = Modifier
                            .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp))
                            .width(128.dp)
                            .height(52.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(GrayLight),
                        contentAlignment = Alignment.Center
                    ){
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Text(
                                text = university.ratingByCountry,
                                fontSize = 20.sp,
                                fontFamily = Constants.INTER_FONT_FAMILY,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black,
                            )
                            Text(
                                text = "Country Rank",
                                fontSize = 14.sp,
                                fontFamily = Constants.INTER_FONT_FAMILY,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black,
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp))
                            .width(128.dp)
                            .height(52.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(GrayLight),
                        contentAlignment = Alignment.Center
                    ){
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Text(
                                text = university.worldRanking,
                                fontSize = 20.sp,
                                fontFamily = Constants.INTER_FONT_FAMILY,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black,
                            )
                            Text(
                                text = "World Rank",
                                fontSize = 14.sp,
                                fontFamily = Constants.INTER_FONT_FAMILY,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black,
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

                //requirements
                Text(
                    text = "Requirements",
                    fontSize = 16.sp,
                    fontFamily = Constants.INTER_FONT_FAMILY,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(12.dp))

                //essay
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "Essay",
                        fontSize = 16.sp,
                        fontFamily = Constants.INTER_FONT_FAMILY,
                        fontWeight = FontWeight.SemiBold,
                        color = PurpleDark,
                    )
                    Text(
                        text = university.essay,
                        fontSize = 14.sp,
                        fontFamily = Constants.INTER_FONT_FAMILY,
                        color = Color.Black,
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))

                //recommend letter
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "Recommendation\nletter",
                        fontSize = 16.sp,
                        fontFamily = Constants.INTER_FONT_FAMILY,
                        fontWeight = FontWeight.SemiBold,
                        color = PurpleDark,
                    )
                    Text(
                        text = university.recommendationLetter,
                        fontSize = 14.sp,
                        fontFamily = Constants.INTER_FONT_FAMILY,
                        color = Color.Black,
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))

                //toefl/ielts
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "TOEFL/IELTS",
                        fontSize = 16.sp,
                        fontFamily = Constants.INTER_FONT_FAMILY,
                        fontWeight = FontWeight.SemiBold,
                        color = PurpleDark,
                    )
                    Text(
                        text = university.toeflAndIelts,
                        fontSize = 14.sp,
                        fontFamily = Constants.INTER_FONT_FAMILY,
                        color = Color.Black,
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))

                //sat
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "SAT",
                        fontSize = 16.sp,
                        fontFamily = Constants.INTER_FONT_FAMILY,
                        fontWeight = FontWeight.SemiBold,
                        color = PurpleDark,
                    )
                    Text(
                        text = university.sat,
                        fontSize = 14.sp,
                        fontFamily = Constants.INTER_FONT_FAMILY,
                        color = Color.Black,
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))

                //cost of request
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "Cost of \nRequest",
                        fontSize = 16.sp,
                        fontFamily = Constants.INTER_FONT_FAMILY,
                        fontWeight = FontWeight.SemiBold,
                        color = PurpleDark,
                    )
                    Text(
                        text = university.costOfRequest,
                        fontSize = 14.sp,
                        fontFamily = Constants.INTER_FONT_FAMILY,
                        color = Color.Black,
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

                //deadlines
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Box(
                        modifier = Modifier
                            .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp))
                            .width(140.dp)
                            .height(52.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(GrayLight),
                        contentAlignment = Alignment.Center
                    ){
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Text(
                                text = university.deadline1,
                                fontSize = 14.sp,
                                fontFamily = Constants.INTER_FONT_FAMILY,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black,
                            )
                            Text(
                                text = "Start deadline",
                                fontSize = 12.sp,
                                fontFamily = Constants.INTER_FONT_FAMILY,
                                color = Color.Gray,
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp))
                            .width(140.dp)
                            .height(52.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(GrayLight),
                        contentAlignment = Alignment.Center
                    ){
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Text(
                                text = university.deadline2,
                                fontSize = 14.sp,
                                fontFamily = Constants.INTER_FONT_FAMILY,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black,
                            )
                            Text(
                                text = "End Deadline",
                                fontSize = 12.sp,
                                fontFamily = Constants.INTER_FONT_FAMILY,
                                color = Color.Gray,
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

                //info
                Text(
                    text = "Info",
                    fontSize = 16.sp,
                    fontFamily = Constants.INTER_FONT_FAMILY,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(12.dp))

                //Full Grant
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "Full Grant",
                        fontSize = 16.sp,
                        fontFamily = Constants.INTER_FONT_FAMILY,
                        fontWeight = FontWeight.SemiBold,
                        color = PurpleDark,
                    )
                    Text(
                        text = university.fullGrant,
                        fontSize = 14.sp,
                        fontFamily = Constants.INTER_FONT_FAMILY,
                        color = Color.Black,
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

                //Full Grant
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "Income Percentage",
                        fontSize = 16.sp,
                        fontFamily = Constants.INTER_FONT_FAMILY,
                        fontWeight = FontWeight.SemiBold,
                        color = PurpleDark,
                    )
                    Text(
                        text = university.percentageOfIncome,
                        fontSize = 14.sp,
                        fontFamily = Constants.INTER_FONT_FAMILY,
                        color = Color.Black,
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

                //tuition
                Text(
                    text = "Tuition",
                    fontSize = 16.sp,
                    fontFamily = Constants.INTER_FONT_FAMILY,
                    fontWeight = FontWeight.SemiBold,
                    color = PurpleDark,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = university.tuition,
                    fontSize = 14.sp,
                    fontFamily = Constants.INTER_FONT_FAMILY,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(20.dp))

                //max
                Text(
                    text = "Max",
                    fontSize = 16.sp,
                    fontFamily = Constants.INTER_FONT_FAMILY,
                    fontWeight = FontWeight.SemiBold,
                    color = PurpleDark,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = university.max,
                    fontSize = 14.sp,
                    fontFamily = Constants.INTER_FONT_FAMILY,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(20.dp))

                //profession
                Text(
                    text = "Professions",
                    fontSize = 16.sp,
                    fontFamily = Constants.INTER_FONT_FAMILY,
                    fontWeight = FontWeight.SemiBold,
                    color = PurpleDark,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = university.professions,
                    fontSize = 14.sp,
                    fontFamily = Constants.INTER_FONT_FAMILY,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(20.dp))

                //additional comment
                Text(
                    text = "Additional comments",
                    fontSize = 16.sp,
                    fontFamily = Constants.INTER_FONT_FAMILY,
                    fontWeight = FontWeight.SemiBold,
                    color = PurpleDark,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = university.additionalComments,
                    fontSize = 14.sp,
                    fontFamily = Constants.INTER_FONT_FAMILY,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(20.dp))

                //for application
                Text(
                    text = "For application",
                    fontSize = 16.sp,
                    fontFamily = Constants.INTER_FONT_FAMILY,
                    fontWeight = FontWeight.SemiBold,
                    color = PurpleDark,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = university.linkForm,
                    fontSize = 14.sp,
                    fontFamily = Constants.INTER_FONT_FAMILY,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(20.dp))

                //website
                Text(
                    text = "WebSite",
                    fontSize = 16.sp,
                    fontFamily = Constants.INTER_FONT_FAMILY,
                    fontWeight = FontWeight.SemiBold,
                    color = PurpleDark,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = university.website,
                    fontSize = 14.sp,
                    fontFamily = Constants.INTER_FONT_FAMILY,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(100.dp))
            }
        }

    }
}