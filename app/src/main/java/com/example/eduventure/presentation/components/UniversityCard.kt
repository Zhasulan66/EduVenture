package com.example.eduventure.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import coil.compose.rememberAsyncImagePainter
import com.example.eduventure.R
import com.example.eduventure.common.Constants
import com.example.eduventure.domain.model.University
import com.example.eduventure.presentation.ui.theme.PurpleDark

@Composable
fun UniversityCard(
    university: University,
    onClick: () -> Unit
){

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(88.dp)
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable {
                onClick()
            }
    ){
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
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
                    .width(68.dp),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ){

                //title
                Text(
                    text = university.name,
                    fontSize = 12.sp,
                    fontFamily = Constants.INTER_FONT_FAMILY,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(8.dp))

                //body text
                Text(
                    text = "rating by country: ${university.ratingByCountry}",
                    fontSize = 12.sp,
                    fontFamily = Constants.INTER_FONT_FAMILY,
                    color = Color.Black,
                )

            }
        }
    }
}