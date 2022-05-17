package com.example.q4_hw18_1.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.q4_hw18_1.data.network.model.UserItem
import com.example.q4_hw18_1.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val userRepository: UserRepository):ViewModel() {

    fun createUser(user: UserItem) {
        viewModelScope.launch {
            userRepository.createUser(user)
        }
    }
}