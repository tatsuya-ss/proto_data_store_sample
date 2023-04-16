package com.example.proto_data_store_sample.app

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proto_data_store_sample.domain.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _complete = MutableLiveData<Boolean>()
    val complete: LiveData<Boolean> = _complete

    private var isComplete = false

    fun onSave() {
        viewModelScope.launch {
            isComplete = !isComplete
            userRepository.saveUser(isComplete)
            val user = userRepository.getUser()
            _complete.value = user.first().showCompleted
        }
    }
}