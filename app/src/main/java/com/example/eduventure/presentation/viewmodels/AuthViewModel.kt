package com.example.eduventure.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eduventure.domain.model.Auth.*
import com.example.eduventure.domain.model.*
import com.example.eduventure.domain.repository.EduVentureRepository
import com.example.eduventure.domain.model.Auth.VerifyCodeRequest
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

    private val _confirmEmailCodeState = MutableStateFlow<Resource<MessageResponse>>(Resource.Initial)
    val confirmEmailCodeState: StateFlow<Resource<MessageResponse>> = _confirmEmailCodeState.asStateFlow()

    fun verifyEmail(verificationRequest: VerificationRequest){
        viewModelScope.launch {
            repository.verifyEmail(verificationRequest)
        }
    }

    fun confirmEmailCode(confirmCodeRequest: ConfirmCodeRequest){
        viewModelScope.launch {
            _confirmEmailCodeState.value = Resource.Loading
            try {
                val response = repository.confirmEmailCode(confirmCodeRequest)
                _confirmEmailCodeState.value = Resource.Success(response)
            } catch (e: Exception) {
                _confirmEmailCodeState.value = Resource.Error(e)
            }
        }
    }

    fun confirmCodeSuccess() {
        _confirmEmailCodeState.value = Resource.Initial
    }

    private val _loginState = MutableStateFlow<Resource<TokenResponse>>(Resource.Initial)
    val loginState: StateFlow<Resource<TokenResponse>> = _loginState.asStateFlow()

    fun loginUser(userLogin: UserLogin) {
        viewModelScope.launch {
            _loginState.value = Resource.Loading
            try {
                val token = repository.loginUser(userLogin)
                _loginState.value = Resource.Success(token)
            } catch (e: Exception) {
                _loginState.value = Resource.Error(e)
            }
        }
    }

    fun loginSuccess() {
        _loginState.value = Resource.Initial
    }

    fun sendResetCode(resetCodeRequest: ResetCodeRequest){
        viewModelScope.launch {
            repository.sendResetCode(resetCodeRequest)
        }
    }

    private val _verifyPasswordCodeState = MutableStateFlow<Resource<String>>(Resource.Initial)
    val verifyPasswordCodeState: StateFlow<Resource<String>> = _verifyPasswordCodeState.asStateFlow()

    fun verifyResetCode(verifyCodeRequest: VerifyCodeRequest){
        viewModelScope.launch {
            _verifyPasswordCodeState.value = Resource.Loading
            try {
                val response = repository.verifyResetCode(verifyCodeRequest)
                _verifyPasswordCodeState.value = Resource.Success(verifyCodeRequest.code)
            } catch (e: Exception) {
                _verifyPasswordCodeState.value = Resource.Error(e)
            }

        }
    }

    fun verifyResetCodeSuccess() {
        _verifyPasswordCodeState.value = Resource.Initial
    }

    private val _resetPasswordState = MutableStateFlow<Resource<ResetPasswordResponse>>(Resource.Initial)
    val resetPasswordState: StateFlow<Resource<ResetPasswordResponse>> = _resetPasswordState.asStateFlow()

    fun resetPassword(resetPasswordRequest: ResetPasswordRequest){
        viewModelScope.launch {
            _resetPasswordState.value = Resource.Loading
            try {
                val response = repository.resetPassword(resetPasswordRequest)
                _resetPasswordState.value = Resource.Success(response)
            } catch (e: Exception) {
                _resetPasswordState.value = Resource.Error(e)
            }

        }
    }

    fun resetPasswordSuccess() {
        _resetPasswordState.value = Resource.Initial
    }

}