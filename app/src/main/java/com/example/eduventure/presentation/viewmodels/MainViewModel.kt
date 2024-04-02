package com.example.eduventure.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eduventure.domain.model.*
import com.example.eduventure.domain.repository.EduVentureRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: EduVentureRepository,
) : ViewModel() {

    /*private val _news = MutableStateFlow<List<News>>(emptyList())
    val news: StateFlow<List<News>> = _news*/

    //news
    private val _newsState = MutableStateFlow<Resource<List<News>>>(Resource.Initial)
    val newsState: StateFlow<Resource<List<News>>> = _newsState.asStateFlow()

    fun fetchAllNews() {
        viewModelScope.launch {
            _newsState.value = Resource.Loading
            try {
                val newsList = repository.getAllNews()
                _newsState.value = Resource.Success(newsList)
            } catch (e: Exception) {
                _newsState.value = Resource.Error(e)
            }
        }
    }

    //university
    private val _universityState = MutableStateFlow<Resource<List<University>>>(Resource.Loading)
    val universityState: StateFlow<Resource<List<University>>> = _universityState.asStateFlow()

    fun fetchAllUniversities() {
        viewModelScope.launch {
            try {
                val universityList = repository.getAllUniversities()
                _universityState.value = Resource.Success(universityList)
            } catch (e: Exception) {
                _universityState.value = Resource.Error(e)
            }
        }
    }

    //internship
    private val _internshipState = MutableStateFlow<Resource<List<Internship>>>(Resource.Loading)
    val internshipState: StateFlow<Resource<List<Internship>>> = _internshipState.asStateFlow()

    fun fetchAllInternships() {
        viewModelScope.launch {
            try {
                val internshipList = repository.getAllInternships()
                _internshipState.value = Resource.Success(internshipList)
            } catch (e: Exception) {
                _internshipState.value = Resource.Error(e)
            }
        }
    }

    //news info
    private val _newsInfoState = MutableStateFlow<Resource<News>>(Resource.Initial)
    val newsInfoState: StateFlow<Resource<News>> = _newsInfoState.asStateFlow()

    fun fetchNewsById(id: Int) {
        viewModelScope.launch {
            _newsInfoState.value = Resource.Loading
            try {
                val news = repository.getNewsById(id)
                _newsInfoState.value = Resource.Success(news)
            } catch (e: Exception) {
                _newsInfoState.value = Resource.Error(e)
            }
        }
    }

    //university info
    private val _universityInfoState = MutableStateFlow<Resource<University>>(Resource.Initial)
    val universityInfoState: StateFlow<Resource<University>> = _universityInfoState.asStateFlow()

    fun fetchUniversityById(id: Int) {
        viewModelScope.launch {
            _universityInfoState.value = Resource.Loading
            try {
                val university = repository.getUniversityById(id)
                _universityInfoState.value = Resource.Success(university)
            } catch (e: Exception) {
                _universityInfoState.value = Resource.Error(e)
            }
        }
    }

    //internship info
    private val _internshipInfoState = MutableStateFlow<Resource<Internship>>(Resource.Initial)
    val internshipInfoState: StateFlow<Resource<Internship>> = _internshipInfoState.asStateFlow()

    fun fetchInternshipById(id: Int) {
        viewModelScope.launch {
            _internshipInfoState.value = Resource.Loading
            try {
                val internship = repository.getInternshipById(id)
                _internshipInfoState.value = Resource.Success(internship)
            } catch (e: Exception) {
                _internshipInfoState.value = Resource.Error(e)
            }
        }
    }

    //user info
    private val _userInfoState = MutableStateFlow<Resource<User>>(Resource.Initial)
    val userInfoState: StateFlow<Resource<User>> = _userInfoState.asStateFlow()

    fun fetchUserById(token: String, id: Int) {
        viewModelScope.launch {
            _userInfoState.value = Resource.Loading
            try {
                val user = repository.getUserById("Token $token",id)
                _userInfoState.value = Resource.Success(user)
            } catch (e: Exception) {
                _userInfoState.value = Resource.Error(e)
            }
        }
    }

    fun updateUser(token: String, id: Int, user: User) {
        viewModelScope.launch {
            val updatedUser = repository.updateUser("Token $token",id, user)

        }
    }


}