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
    private val _newsState = MutableStateFlow<Resource<List<News>>>(Resource.Loading)
    val newsState: StateFlow<Resource<List<News>>> = _newsState.asStateFlow()

    fun fetchAllNews() {
        viewModelScope.launch {
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


}