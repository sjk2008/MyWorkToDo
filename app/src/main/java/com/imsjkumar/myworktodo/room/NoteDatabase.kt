package com.imsjkumar.myworktodo.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.imsjkumar.myworktodo.model.Note
import java.security.AccessControlContext

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
//"entities" specifies an array of entity classes that represent the tables in the database.
// Note:- this db has the table to store note.
//exportSchema:- means that the Room library won't generate a schema file for the database.
// This can help reduce unnecessary overhead.
abstract class NoteDatabase : RoomDatabase() //It serves as the main entry point to the Room database functionalities.
{
    abstract fun getNotesDao(): NotesDao //returns the instance of DAO

    companion object {
        @Volatile //changes will immediate will be visible to all the threads.
        private var INSTACE: NoteDatabase? = null
        fun getDatabase(context: Context): NoteDatabase {
            return INSTACE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                ).build()
                INSTACE = instance
                instance
            }
        }
    }
}