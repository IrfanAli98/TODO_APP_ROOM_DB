package com.example.todo_app_room_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Notes::class], version = 1 )
abstract class Notes_Database:RoomDatabase() {

    abstract val notesDao:Notes_Dao

    companion object{
        @Volatile
        private var INSTANCE: Notes_Database? = null
        fun getInstance(context:Context):Notes_Database{
            var instance= INSTANCE
            synchronized(this ){
                if (instance == null){
                    instance = Room.databaseBuilder(context, Notes_Database::class.java, "Notes_DATABASE").build()
                    INSTANCE = instance
                }
            }
            return instance!!
        }
    }
}