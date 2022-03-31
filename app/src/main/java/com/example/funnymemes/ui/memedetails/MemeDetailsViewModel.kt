package com.example.funnymemes.ui.memedetails

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.funnymemes.model.MemeRepository
import com.example.funnymemes.model.response.MemeResponse

class MemeDetailsViewModel(private val savedStateInstance: SavedStateHandle): ViewModel() {
    private val repository: MemeRepository = MemeRepository.getInstance()

    val memeState = mutableStateOf<MemeResponse?>(null)
    init {
        //this will fetch from the navigation params
        val memeId = savedStateInstance.get<String>("meme_id")?:""
        memeState.value = repository.getMeme(memeId)
    }
}