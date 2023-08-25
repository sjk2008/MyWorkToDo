package com.imsjkumar.myworktodo.repository

import androidx.lifecycle.LiveData
import com.imsjkumar.myworktodo.model.Note
import com.imsjkumar.myworktodo.room.NotesDao

class NoteRepository(private val notesDao: NotesDao) {
    val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()

    suspend fun insert(note: Note) {
        notesDao.insert(note)
    }

    suspend fun delete(note: Note) {
        notesDao.delete(note)
    }

    suspend fun update(note: Note) {
        notesDao.update(note)
    }


}