package com.example.eduventure.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eduventure.domain.model.Auth.*
import com.example.eduventure.domain.model.*
import com.example.eduventure.domain.repository.EduVentureRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: EduVentureRepository,
) : ViewModel() {

    private val _registrationState = MutableStateFlow<Resource<UserResponse>>(Resource.Initial)
    val registrationState: StateFlow<Resource<UserResponse>> = _registrationState.asStateFlow()

    fun registerUser(userRequest: UserRequest) {
        viewModelScope.launch {
            _registrationState.value = Resource.Loading
            try {
                val response = repository.registerUser(userRequest)
                _registrationState.value = Resource.Success(response)
            } catch (e: Exception) {
                _registrationState.value = Resource.Error(e)
            }
        }
    }

    fun registrationSuccess() {
        // This function called by the UI layer to signal a successful registration
        _registrationState.value = Resource.Initial
    }
}