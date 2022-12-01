package com.example.todo_app_room_db

class Notes_Repository(private val notesDao:Notes_Dao) {

    suspend fun saveNotes( notes:Notes ){
        notesDao.insertNotes(notes)
    }

    val notes = notesDao.getAlleNotes()

    suspend fun updateNotes(notes: Notes){
        notesDao.updateNotes(notes)
    }

    suspend fun deleteNotes(notes: Notes){
        notesDao.deleteNotes(notes)
    }

    suspend fun deleteAllNotes(){
        notesDao.deleteAllUser()
    }

}