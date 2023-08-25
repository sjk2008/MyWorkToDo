package com.imsjkumar.myworktodo.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.imsjkumar.myworktodo.model.Note

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM notesTable ORDER BY id ASC ")
    fun getAllNotes() :LiveData<List<Note>>
}