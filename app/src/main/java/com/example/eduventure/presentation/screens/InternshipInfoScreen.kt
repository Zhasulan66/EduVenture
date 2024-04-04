package com.example.eduventure.presentation.screens

import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
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
import com.example.eduventure.domain.model.Internship
import com.example.eduventure.domain.model.Resource
import com.example.eduventure.presentation.components.NavigationView
import com.example.eduventure.presentation.navigation.Screen
import com.example.eduventure.presentation.ui.theme.PurpleDark
import com.example.eduventure.presentation.ui.theme.PurpleLight
import com.example.eduventure.presentation.viewmodels.MainViewModel

@Composable
fun InternshipInfoScreen(
    navController: NavController,
    internshipId: Int
) {
    val viewModel = hiltViewModel<MainViewModel>()
    val internshipInfoState by viewModel.internshipInfoState.collectAsState()

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
                    text = stringResource(id = R.string.internship),
                    fontSize = 20.sp,
                    fontFamily = Constants.INTER_FONT_FAMILY,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                )
            }
        }

        when (internshipInfoState) {
            is Resource.Loading -> {
                LoadingScreen()
            }

            is Resource.Error -> {
                ErrorScreen(
                    modifier = Modifier,
                    retryAction = {
                        navController.navigate(Screen.InternshipInfoScreen.route + "/$internshipId")
                    }
                )
            }

            is Resource.Success -> {
                val internship = (internshipInfoState as Resource.Success<Internship>).data
                InternshipSuccessScreen(internship)
            }

            else -> {
                viewModel.fetchInternshipById(internshipId)
            }
        }

        val screenWidth = LocalConfiguration.current.screenWidthDp
        NavigationView(
            modifier = Modifier.align(Alignment.BottomCenter),
            navController = navController,
            focusedOffset = (screenWidth/2).toInt() //206
        )
    }


}


@Composable
fun InternshipSuccessScreen(
    internship: Internship
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(100.dp))

        LazyColumn {
            item {

                //company logo name
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                    ) {
                        //name
                        Text(
                            text = internship.title,
                            fontSize = 16.sp,
                            fontFamily = Constants.INTER_FONT_FAMILY,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        //country
                        Text(
                            text = internship.country,
                            fontSize = 14.sp,
                            fontFamily = Constants.INTER_FONT_FAMILY,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.End
                    ) {
                        Image(
                            painter = if (internship.organizationLogo != null) {
                                rememberAsyncImagePainter(
                                    model = internship.organizationLogo,
                                    placeholder = painterResource(id = R.drawable.img_placeholder),
                                )
                            } else painterResource(id = R.drawable.img_placeholder),
                            contentDescription = "img",
                            modifier = Modifier
                                .width(64.dp)
                                .aspectRatio(1f),
                            contentScale = ContentScale.FillWidth
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = internship.organization,
                            fontSize = 12.sp,
                            fontFamily = Constants.INTER_FONT_FAMILY,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))


                //duration
                internship.duration?.let{
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Duration",
                            fontSize = 16.sp,
                            fontFamily = Constants.INTER_FONT_FAMILY,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                        )
                        Text(
                            text = it,
                            fontSize = 14.sp,
                            fontFamily = Constants.INTER_FONT_FAMILY,
                            color = Color.Black,
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }


                //location
                Text(
                    text = "Location",
                    fontSize = 16.sp,
                    fontFamily = Constants.INTER_FONT_FAMILY,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = internship.location,
                    fontSize = 14.sp,
                    fontFamily = Constants.INTER_FONT_FAMILY,
                    color = Color.Black,
                )
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
                Text(
                    text = internship.description,
                    fontSize = 14.sp,
                    fontFamily = Constants.INTER_FONT_FAMILY,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(20.dp))

                //deadline
                internship.deadline?.let {
                    Text(
                        text = "DeadLine",
                        fontSize = 16.sp,
                        fontFamily = Constants.INTER_FONT_FAMILY,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = it,
                        fontSize = 14.sp,
                        fontFamily = Constants.INTER_FONT_FAMILY,
                        color = Color.Black,
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }

                //benefits
                internship.benefits?.let {
                    Text(
                        text = "Benefits",
                        fontSize = 16.sp,
                        fontFamily = Constants.INTER_FONT_FAMILY,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    InfoList(it)
                    Spacer(modifier = Modifier.height(20.dp))
                }

                //eligibility
                internship.eligibility?.let {
                    Text(
                        text = "Eligibility",
                        fontSize = 16.sp,
                        fontFamily = Constants.INTER_FONT_FAMILY,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    InfoList(it)
                    Spacer(modifier = Modifier.height(20.dp))
                }

                //required docs
                internship.requiredDocuments?.let {
                    Text(
                        text = "Required Documents",
                        fontSize = 16.sp,
                        fontFamily = Constants.INTER_FONT_FAMILY,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    InfoList(it)
                    Spacer(modifier = Modifier.height(20.dp))
                }

                //how to apply
                internship.howToApply?.let {
                    Text(
                        text = "How to Apply",
                        fontSize = 16.sp,
                        fontFamily = Constants.INTER_FONT_FAMILY,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = it,
                        fontSize = 14.sp,
                        fontFamily = Constants.INTER_FONT_FAMILY,
                        color = Color.Black,
                    )
                }
                Spacer(modifier = Modifier.height(140.dp))

            }
        }

    }
}

@Composable
fun InfoList(info: String) {
    val infoList = info.split("\r\n")
    Column(modifier = Modifier.padding(8.dp)) {
        infoList.forEach { benefit ->
            Row(verticalAlignment = Alignment.CenterVertically) {

                // Option 1: Using a Canvas to draw a circle
                Canvas(modifier = Modifier.size(6.dp).padding(end = 8.dp), onDraw = {
                    drawCircle(Color.Black, radius = 3.dp.toPx(), style = Fill)
                })

                // Option 2: Using a Unicode black circle
                /*Text(text = "● ", fontSize = 14.sp, color = Color.Black)*/

                Text(
                    text = benefit,
                    fontSize = 14.sp,
                    fontFamily = Constants.INTER_FONT_FAMILY,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }
}