package com.example.inotes.adaptors

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inotes.R
import com.example.inotes.db.Note
import kotlinx.android.synthetic.main.note_list_layout.view.*

class NoteAdaptor(val notes: List<Note>, var clickListner : onNoteItemClickListener) : RecyclerView.Adapter<NoteAdaptor.NoteViewHolder>() {


    class NoteViewHolder(val view : View) : RecyclerView.ViewHolder(view){


        fun initialize(note : Note, action : onNoteItemClickListener){
            view.setOnClickListener {
                action.onItemClick(note, adapterPosition)
            }

            view.editImage.setOnClickListener {
                action.editImageClick(note, adapterPosition)
            }

            view.deleteImage.setOnClickListener {
                action.deleteImageClick(note, adapterPosition)
            }

        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.note_list_layout, parent, false)
        )
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        // color codes
        val color1 =  "#FFC3E4"
         val color2 =  "#C3EFFF"

        if(position.toInt() % 2 == 0 ){
            holder.view.relativeLayoutCardView.setBackgroundColor(Color.parseColor(color1))
        } else if( position.toInt() % 2 != 0){
            holder.view.relativeLayoutCardView.setBackgroundColor(Color.parseColor(color2))
        } else {
            holder.view.relativeLayoutCardView.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }
        val myNote = notes[position]
        holder.view.text_view_title.text = myNote.title
        holder.view.text_view_note.text = myNote.description


        holder.initialize(notes[position],  clickListner)

    }

    interface onNoteItemClickListener{
        fun onItemClick(note : Note, position: Int)
        fun editImageClick(note: Note, position: Int)
        fun deleteImageClick(note: Note, position: Int)
    }


}