package com.example.todo_app_room_db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class Notes_ViewModel(private val repository: Notes_Repository):ViewModel() {

    fun saveNotes(notes:Notes){
        viewModelScope.launch {
            repository.saveNotes(notes)
        }
    }
    val notes = repository.notes

    fun updateNotes(notes: Notes){
        viewModelScope.launch {
            repository.updateNotes(notes) }
    }

    fun deleteNotes(notes: Notes){
        viewModelScope.launch {
            repository.deleteNotes(notes)
        }
    }

    fun deleteAllNotes(){
        viewModelScope.launch {
            repository.deleteAllNotes()
        }
    }
}