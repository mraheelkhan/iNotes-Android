package com.example.inotes.db.dao

import androidx.room.*
import com.example.inotes.db.Note

@Dao
interface NoteDao {

    @Insert
    fun addNote(note: Note)

    @Query("SELECT * FROM note_table")
    fun getAllNotes() : List<Note>

    @Insert
    fun addMultipleNotes(vararg note: Note)

    @Update
    fun updateNote (note : Note)

    @Query("Select * from note_table where id = :id")
    fun getNoteById(id : Int) : Note

    @Update
    fun updateNotebyId(note : Note)

    @Delete
    fun deleteNote(note: Note)

}