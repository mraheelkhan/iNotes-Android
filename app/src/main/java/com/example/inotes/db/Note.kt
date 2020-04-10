package com.example.inotes.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note (
    val title: String,
    @ColumnInfo(name = "note_description")
    val description: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
