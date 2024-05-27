package com.example.m014_retrofit
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel : ViewModel(){
    private val _state: MutableLiveData<State> = MutableLiveData(State.Success)
    val state: LiveData<State> get() = _state


    private val _userLiveData = MutableLiveData<User>()
    val userLiveData: LiveData<User> get() = _userLiveData

    private var _initialLoad = true

    fun loadUserInfo() {
        viewModelScope.launch {
            try {
                _state.value = State.Loading
                val response = RetrofitInstance.getUserAPI.getUser()
                _userLiveData.postValue(response.results.first())
                _state.value = State.Success
            } catch (e: Exception) {
                _state.value = State.Error

            }
        }
    }

    fun loadInitialUserInfo() {
        if (_initialLoad) {
            loadUserInfo()
            _initialLoad = false
        }
    }
}