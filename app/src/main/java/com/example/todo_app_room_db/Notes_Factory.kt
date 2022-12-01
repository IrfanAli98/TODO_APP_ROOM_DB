package com.example.todo_app_room_db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class Notes_Factory(private val repository: Notes_Repository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(Notes_ViewModel::class.java)){
            return Notes_ViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown Class")
    }
}