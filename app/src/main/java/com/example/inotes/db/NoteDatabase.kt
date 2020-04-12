package com.example.inotes.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.inotes.db.dao.NoteDao
import com.example.inotes.db.dao.UserDao

@Database(
    entities = arrayOf(Note::class, UserEntity::class),
    version = 1
)
abstract class NoteDatabase : RoomDatabase(){
    abstract fun getNoteDao() : NoteDao
    abstract fun getUserDao() : UserDao

    companion object {
        @Volatile private var instance : NoteDatabase?  = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
            "notedatabase1"
        )
//            .allowMainThreadQueries()
            .build()
    }
}