package com.example.m014_retrofit

sealed class State(val isLoading: Boolean = false,
    val isEnable : Boolean = true
                    ) {


   object Loading : State(isLoading = true, isEnable = false)

    object Success: State()

  object Error : State()
}