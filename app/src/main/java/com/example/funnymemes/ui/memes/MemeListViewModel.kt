package com.example.funnymemes.ui.memes

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.funnymemes.model.MemeRepository
import com.example.funnymemes.model.response.MemeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MemeListViewModel (private val repository: MemeRepository = MemeRepository.getInstance()): ViewModel() {
    //Create the live data to be observed by the view(composable)
    val memeState: MutableState<List<MemeResponse>> = mutableStateOf(emptyList())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            //Call the suspend method
            val memes = getMemes()
            memeState.value  = memes
        }
    }

    suspend fun getMemes(): List<MemeResponse> {
        //call the api using the MemeRepository instance
        return repository.getMemes().data.memes
    }
}