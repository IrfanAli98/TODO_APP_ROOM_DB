package com.example.todo_app_room_db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface Notes_Dao {
    @Insert
    suspend fun insertNotes(vararg notes: Notes)

    @Query("SELECT * From notes")
    fun getAlleNotes():LiveData<List<Notes>>

    @Delete
    suspend fun deleteNotes(notes: Notes)

    @Update
    suspend fun updateNotes(notes: Notes)

    @Query("DELETE FROM notes")
    suspend fun deleteAllUser()
}