package com.example.todo_app_room_db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Notes(
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "Id")
    var id: Int,

    @ColumnInfo(name = "Title")
    var Title: String,

    @ColumnInfo(name = " Descryption")
    var Descryption: String,

    @ColumnInfo(name= "Created_Modified_At")
    var Created_At: String
)
