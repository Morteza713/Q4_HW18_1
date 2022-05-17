package com.example.q4_hw18_1.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.q4_hw18_1.data.Result
import com.example.q4_hw18_1.data.datasource.model.User
import com.example.q4_hw18_1.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) :ViewModel() {

    private val channel = Channel<Boolean> { }

    val getUsers = channel.receiveAsFlow().flatMapLatest {
        userRepository.getUsers()
    }

    fun addUser(user: User) {
        viewModelScope.launch {
            userRepository.addUser(user)
        }
    }

    fun addHobbies(id: String) {
        viewModelScope.launch {
            userRepository.addHobbies(id)
        }
    }
}