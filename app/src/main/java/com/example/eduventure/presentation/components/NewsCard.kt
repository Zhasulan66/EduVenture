package com.example.eduventure.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import com.example.eduventure.R
import com.example.eduventure.common.Constants
import com.example.eduventure.domain.model.News
import com.example.eduventure.presentation.ui.theme.PurpleDark

@Composable
fun NewsCard(
    news: News
){
    Row(
       modifier = Modifier
           .shadow(
               elevation = 10.dp,
               shape = RoundedCornerShape(20.dp)
           )
           .fillMaxWidth()
           .height(140.dp)
           .clip(RoundedCornerShape(20.dp))
    ){
        Image(
            painter = painterResource(id = R.drawable.img_placeholder),
            contentDescription = "img",
            modifier = Modifier
                .width(140.dp),
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ){
            //date
            Text(
                text = news.date,
                fontSize = 12.sp,
                fontFamily = Constants.INTER_FONT_FAMILY,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.height(8.dp))

            //title
            Text(
                text = news.title,
                fontSize = 16.sp,
                fontFamily = Constants.INTER_FONT_FAMILY,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.height(8.dp))

            //body text
            Text(
                text = news.text,
                fontSize = 12.sp,
                fontFamily = Constants.INTER_FONT_FAMILY,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.height(8.dp))

            Box(
               modifier = Modifier
                   .fillMaxWidth()
                   .height(32.dp)
                   .clip(RoundedCornerShape(24.dp))
                   .background(PurpleDark),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = stringResource(id = R.string.read),
                    fontSize = 14.sp,
                    fontFamily = Constants.INTER_FONT_FAMILY,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                )
            }

        }
    }

}