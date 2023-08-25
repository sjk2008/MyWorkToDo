package com.imsjkumar.myworktodo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.imsjkumar.myworktodo.adapter.NoteAdapter
import com.imsjkumar.myworktodo.adapter.noteClickDeleteInterface
import com.imsjkumar.myworktodo.adapter.noteClickInterface
import com.imsjkumar.myworktodo.databinding.ActivityMainBinding
import com.imsjkumar.myworktodo.model.Note
import com.imsjkumar.myworktodo.viewModel.NoteViewModel

class MainActivity : AppCompatActivity(), noteClickDeleteInterface, noteClickInterface {

    lateinit var binding : ActivityMainBinding
    lateinit var notesRV: RecyclerView
    lateinit var addFraBtn: FloatingActionButton
    lateinit var viewModel: NoteViewModel
     val allNote = ArrayList<Note>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        notesRV = findViewById(R.id.idRVNOTE)
        addFraBtn = findViewById(R.id.floatingBTN)

        notesRV.layoutManager = LinearLayoutManager(this)

        val noteRVAdapter = NoteAdapter(this, this, this)
        notesRV.adapter = noteRVAdapter


        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[NoteViewModel::class.java]
        viewModel.allNotes.observe(this) { list ->
            list?.let {
                noteRVAdapter.updateList(it)
            }
        }
        addFraBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, AddEdtNoteActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onDeleteIconClick(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.noteTitle} Deleted", Toast.LENGTH_SHORT).show()
    }

    override fun onNoteClick(note: Note) {
        val intent = Intent(this@MainActivity, AddEdtNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDescription", note.noteDescription)
        intent.putExtra("noteID", note.id)
        startActivity(intent)
    }
}