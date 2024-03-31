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
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import com.example.eduventure.R
import com.example.eduventure.common.Constants
import com.example.eduventure.domain.model.News
import com.example.eduventure.presentation.components.NavigationView
import com.example.eduventure.presentation.components.NewsCard
import com.example.eduventure.presentation.ui.theme.PurpleDark
import com.example.eduventure.presentation.ui.theme.PurpleLight

@Composable
fun NewsInfoScreen(
    navController: NavController,
){

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
        ){

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
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
                ){
                    Icon(
                        imageVector = Icons.Rounded.KeyboardArrowLeft,
                        contentDescription = "icon",
                        tint = PurpleLight
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = stringResource(id = R.string.news),
                    fontSize = 20.sp,
                    fontFamily = Constants.INTER_FONT_FAMILY,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ){
            Spacer(modifier = Modifier.height(100.dp))
            Image(
                painter = painterResource(R.drawable.img_placeholder),
                contentDescription = "img",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn {
                item{
                    //date
                    Text(
                        text = "02.02.2024",
                        fontSize = 14.sp,
                        fontFamily = Constants.INTER_FONT_FAMILY,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    //title
                    Text(
                        text = "Some title of News",
                        fontSize = 20.sp,
                        fontFamily = Constants.INTER_FONT_FAMILY,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    //description text
                    Text(
                        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce congue dignissim sapien vestibulum dignissim. Praesent luctus nisl at fermentum ultricies. Nam vitae est quis neque lobortis pretium. In mattis sapien sit amet porttitor tincidunt. Suspendisse potenti. Nunc imperdiet risus est /n" +
                                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce congue dignissim sapien vestibulum dignissim. Praesent luctus nisl at fermentum ultricies. Nam vitae est quis neque lobortis pretium. In mattis sapien sit amet porttitor tincidunt. Suspendisse potenti. Nunc imperdiet risus est/n" +
                                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce congue dignissim sapien vestibulum dignissim. Praesent luctus nisl at fermentum ultricies. Nam vitae est quis neque lobortis pretium. In mattis sapien sit amet porttitor tincidunt. Suspendisse potenti. Nunc imperdiet risus est/n" +
                                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce congue dignissim sapien vestibulum dignissim. Praesent luctus nisl at fermentum ultricies. Nam vitae est quis neque lobortis pretium. In mattis sapien sit amet porttitor tincidunt. Suspendisse potenti. Nunc imperdiet risus est /n",
                        fontSize = 16.sp,
                        fontFamily = Constants.INTER_FONT_FAMILY,
                        color = Color.Black,
                    )
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }

        }

        NavigationView(
            modifier = Modifier.align(Alignment.BottomCenter),
            navController = navController,
            focusedOffset = 24
        )
    }


}