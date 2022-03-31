package com.example.funnymemes.ui.memedetails

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.funnymemes.model.response.MemeResponse
import com.example.funnymemes.ui.AppBar

@Composable
fun MemeDetailsScreen(meme:MemeResponse?,navController: NavHostController?) {
    Scaffold(
        topBar = { AppBar(
            title = "Meme Details",
            icon = Icons.Default.ArrowBack,
            ){
                //TODO navigate back
                navController?.navigateUp()
            }
    },
    content = {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.LightGray
        ) {
            //code goes here
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(meme!!.memeURL)
                        .crossfade(true)
                        .build() ,
                    contentDescription = "Meal Pic",
                    modifier = Modifier
                        .size(400.dp)
                )
                Text(
                    text = meme!!.memeName,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    })
}