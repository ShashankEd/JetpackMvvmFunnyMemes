package com.example.funnymemes.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.funnymemes.ui.memedetails.MemeDetailsScreen
import com.example.funnymemes.ui.memedetails.MemeDetailsViewModel
import com.example.funnymemes.ui.memes.MemeListScreen
import com.example.funnymemes.ui.theme.FunnyMemesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FunnyMemesTheme {
                FunnyMemes()
            }
        }
    }
}


@Composable
fun FunnyMemes() {
    //construct the nav controller graph
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "meme_list"
    ) {
        composable("meme_list") {
            MemeListScreen(){
                //implement the navigationCallback
                    navigationMemeId -> navController?.navigate("meme_details/${navigationMemeId}"  )
            }
        }
        composable(
            route="meme_details/{meme_id}",
            arguments = listOf(navArgument("meme_id"){
                type = NavType.StringType
            }
            )
        ) {
            val viewmodel:MemeDetailsViewModel = viewModel()
            MemeDetailsScreen( viewmodel.memeState.value,navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FunnyMemesTheme {
        FunnyMemes()
    }
}