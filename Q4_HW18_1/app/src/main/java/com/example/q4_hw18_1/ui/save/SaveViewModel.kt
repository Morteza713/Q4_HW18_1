package com.example.q4_hw18_1.ui.save


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.q4_hw18_1.data.datasource.model.User
import com.example.q4_hw18_1.data.datasource.model.UserAndHobie
import com.example.q4_hw18_1.data.network.model.UserItem
import com.example.q4_hw18_1.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveViewModel @Inject constructor(private val userRepository: UserRepository):ViewModel() {

    private val _getUsersInDataBase = MutableStateFlow<List<UserAndHobie>>(emptyList())
    val getUsersInDataBase = _getUsersInDataBase.asStateFlow()

    init {
        getUsersInDataBase()
    }
    private fun getUsersInDataBase() {
        viewModelScope.launch {
            userRepository.getUsersFromDataBase().collect{
                _getUsersInDataBase.emit(it)
            }
        }
    }
    fun removeUser(id: String) {
        viewModelScope.launch {
            userRepository.removeUser(id)
        }
    }
}