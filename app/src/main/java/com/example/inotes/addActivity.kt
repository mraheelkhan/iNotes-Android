package com.example.inotes

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.inotes.db.Note
import com.example.inotes.db.NoteDatabase
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_main.*

class addActivity : AppCompatActivity() {

    private var noteID : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        var db = NoteDatabase(this)
        noteID = intent.getStringExtra("noteId")
        Log.i("noteIDtoAddActivity2", noteID.toString())

        Thread{
            if(noteID.isNotEmpty()) {
                val singleNote = db.getNoteDao().getNoteById(noteID.toInt())
                this.edTitle.setText(singleNote.title)
                this.edBody.setText(singleNote.description)
            }
        }.start()
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
        btnSave.setOnClickListener{
            val title = edTitle.text
            val body = edBody.text

            if(title.isEmpty()){
                edTitle.error = "Enter it to go"
                edTitle.requestFocus()
                return@setOnClickListener
            }
            tvName.text = title.toString()

            Thread{
                if(noteID.isEmpty()){
                    val note = Note(title.toString(), body.toString())
                    saveNote(note)
                }else {
                    val note = Note(title.toString(), body.toString())
                    note.id = noteID.toInt()
                    db.getNoteDao().updateNote(note)
                    Log.i("updatedNoteLog", note.toString())

                }

                Log.i("getAllNotes", db.getNoteDao()?.getAllNotes().toString())
            }.start()
        }
    }

    fun saveNote(note: Note){
        class SaveNote : AsyncTask<Void, Void, Void>(){
            override fun doInBackground(vararg params: Void?): Void? {

                NoteDatabase(this@addActivity).getNoteDao().addNote(note)
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                Log.i("noteResultTextData", result.toString())
                Toast.makeText(this@addActivity, "Save Insertion Completed: " + note.toString(), Toast.LENGTH_LONG).show()

            }
        }
        SaveNote().execute()
    }
}
