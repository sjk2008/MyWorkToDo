package com.imsjkumar.myworktodo.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.imsjkumar.myworktodo.model.Note
import com.imsjkumar.myworktodo.repository.NoteRepository
import com.imsjkumar.myworktodo.room.NoteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    val allNotes: LiveData<List<Note>>
    val repository: NoteRepository

    // initialize
    init {
        val dao = NoteDatabase.getDatabase(application).getNotesDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }

    fun addNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

}