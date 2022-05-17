package com.example.q4_hw18_1.ui.showuser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.q4_hw18_1.data.network.model.UserItemList
import com.example.q4_hw18_1.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.q4_hw18_1.data.Result
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class ShowViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {

    private val _show = MutableStateFlow<Result<UserItemList>>(Result.Success(null))
    val show = _show.asStateFlow()

    fun uploadPic(id: String, image: ByteArray) {
        val multipartBody = MultipartBody.create(MediaType.parse("image/*"), image)
        val req = MultipartBody.Part.createFormData("image", "image.jpg", multipartBody)
        viewModelScope.launch {
            userRepository.uploadPic(id, req)
        }
    }
    fun getUserListById(id: String) {
        viewModelScope.launch {
            userRepository.getUserListById(id).collect {
                _show.emit(it)
            }
        }
    }
}