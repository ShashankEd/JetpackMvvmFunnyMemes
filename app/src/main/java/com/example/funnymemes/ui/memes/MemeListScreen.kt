package com.example.funnymemes.ui.memes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.funnymemes.model.response.MemeResponse
import com.example.funnymemes.ui.AppBar
import com.example.funnymemes.ui.theme.FunnyMemesTheme

@Composable
fun MemeListScreen(navigationCallback: (String) -> Unit) {
    //get the view model reference
    val viewModel:MemeListViewModel = viewModel()
    val memes = viewModel.memeState.value

    Scaffold(
        topBar = { AppBar(
            title = "Funny Memes",
            icon = Icons.Default.Home){} }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.LightGray,
        ) {
            LazyColumn(contentPadding = PaddingValues(16.dp)) {
                items(memes) {
                        meme-> Meme(meme, navigationCallback)
                }
            }
        }
    }
}

@Composable
fun Meme(meme:MemeResponse,navigationCallback: (String) -> Unit ) {
    //Design the item composable for Lazy column
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .padding(8.dp)
            .clickable {
                navigationCallback(meme.memeId)
            }) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            //Image
            Card(
                shape = CircleShape,
                border = BorderStroke(
                    width = 2.dp,
                    color = Color.LightGray
                ),
                modifier = Modifier.padding(16.dp),
                elevation = 4.dp,
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(meme.memeURL)
                        .crossfade(true)
                        .build() ,
                    contentDescription = "Meme Pic",
                    modifier = Modifier
                        .size(70.dp)
                        .align(Alignment.CenterVertically)
                )
            }
            //content
            Column(
                modifier = Modifier
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = meme.memeName,
                    style = MaterialTheme.typography.body1
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FunnyMemesTheme {
        MemeListScreen({})
    }
}