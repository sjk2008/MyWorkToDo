package com.imsjkumar.myworktodo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.imsjkumar.myworktodo.databinding.ActivityAddEdtNoteBinding
import com.imsjkumar.myworktodo.model.Note
import com.imsjkumar.myworktodo.viewModel.NoteViewModel
import java.text.SimpleDateFormat
import java.util.*


class AddEdtNoteActivity : AppCompatActivity() {
    lateinit var binding : ActivityAddEdtNoteBinding
    lateinit var noteTitleEDT: EditText
    lateinit var noteDescriptionEDT: EditText
    lateinit var addUpdateBTN: Button
    lateinit var viewModel: NoteViewModel
    var noteID = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEdtNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        noteTitleEDT = findViewById(R.id.idEDTTitle)
        noteDescriptionEDT = findViewById(R.id.idEDTDescription)
        addUpdateBTN = findViewById(R.id.idBTNAddUpdate)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[NoteViewModel::class.java]

        val noteType = intent.getStringExtra("noteType")
        if (noteType.equals("Edit")) {
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDescription = intent.getStringExtra("noteDescription")
            noteID = intent.getIntExtra("noteID", -1)
            addUpdateBTN.setText("Update Note")
            noteTitleEDT.setText(noteTitle)
            noteDescriptionEDT.setText(noteDescription)
        }else{
            addUpdateBTN.setText("Save Note")
        }
        addUpdateBTN.setOnClickListener {
            val noteTitle = noteTitleEDT.text.toString()
            val noteDescription = noteDescriptionEDT.text.toString()
            if (noteType.equals("Edit")){
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()){
                    val sdf = SimpleDateFormat("dd MM, yyyy - HH:mm")
                    val currentDate:String = sdf.format(Date())
                    val updateNote = Note(noteTitle,noteDescription,currentDate)
                    updateNote.id   = noteID
                    viewModel.updateNote(updateNote)
                    Toast.makeText(this,"Note Updated Successfully",Toast.LENGTH_SHORT).show()
                }
            }else{
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()){
                    val sdf = SimpleDateFormat("dd MM, yyyy - HH:mm")
                    val currentDate:String = sdf.format(Date())
                    viewModel.addNote(Note(noteTitle,noteDescription,currentDate))
                    Toast.makeText(this,"Successfully Note Added",Toast.LENGTH_SHORT).show()
                }
            }
            startActivity(Intent(applicationContext,MainActivity::class.java))
            this.finish()
        }

    }
}