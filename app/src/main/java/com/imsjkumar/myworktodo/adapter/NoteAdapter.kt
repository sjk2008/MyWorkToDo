package com.imsjkumar.myworktodo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.imsjkumar.myworktodo.R
import com.imsjkumar.myworktodo.model.Note

class NoteAdapter(
    val context: Context,
    val noteClickDeleteInterface: noteClickDeleteInterface,
    val noteClickInterface: noteClickInterface
) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private val allNote = ArrayList<Note>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_rv, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteTV.setText(allNote.get(position).noteTitle)
        holder.timeTV.setText("Last Added:- " + allNote.get(position).timeStamp)

        holder.deleteIMG.setOnClickListener {
            noteClickDeleteInterface.onDeleteIconClick(allNote.get(position))
        }
        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(allNote.get(position))
        }
    }

    override fun getItemCount(): Int {
        return allNote.size
    }

    fun updateList(newList: List<Note>) {
        allNote.clear()
        allNote.addAll(newList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteTV: TextView = itemView.findViewById(R.id.notesTitle)
        val timeTV: TextView = itemView.findViewById(R.id.isTVTimeStamp)
        val deleteIMG: ImageView = itemView.findViewById(R.id.idIMGDelete)
    }
}

interface noteClickDeleteInterface {
    fun onDeleteIconClick(note: Note)
}

interface noteClickInterface {
    fun onNoteClick(note: Note)
}