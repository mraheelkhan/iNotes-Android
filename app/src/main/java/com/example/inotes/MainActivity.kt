package com.example.inotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inotes.adaptors.NoteAdaptor
import com.example.inotes.db.Note
import com.example.inotes.db.NoteDatabase
import com.example.inotes.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), NoteAdaptor.onNoteItemClickListener {

//    private var mDb : NoteDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        floatingActionButtonTop.setOnClickListener {

            Log.i("CLICKED", "CLICKED BTN")
            /*RetrofitClient.instance.getAllUsersReq()
                .enqueue(object : Callback<List<ReqresTestModel>>{
                    override fun onFailure(call: Call<List<ReqresTestModel>>, t: Throwable) {
                        Log.e("CLICKED", t.message.toString())
                    }

                    override fun onResponse(
                        call: Call<List<ReqresTestModel>>,
                        response: Response<List<ReqresTestModel>>
                    ) {
                        Log.i("CLICKED", response.body().toString())
                        Toast.makeText(applicationContext, response.body().toString(), Toast.LENGTH_SHORT).show()
                    }
                })*/
//            val intent = Intent(this, ListActivity::class.java)
//            startActivity(intent)
        }

        floatingActionButton.setOnClickListener {
            val intent = Intent(this, addActivity::class.java)
            intent.putExtra("noteId", "")
            startActivity(intent)
        }

        val viewManager = LinearLayoutManager(this)
        notes_recycleview.setHasFixedSize(true)
        notes_recycleview.layoutManager = viewManager



        val appDb = NoteDatabase(this)
        Thread{
            val notes = appDb.getNoteDao().getAllNotes()
            notes_recycleview.adapter = NoteAdaptor(notes, this)
        }.start()

    }

    override fun onItemClick(note: Note, position: Int) {

        val intent = Intent(this@MainActivity, addActivity::class.java)
        intent.putExtra("noteId", note.id.toString())
        Log.i("noteIDtoAddActivity", note.id.toString())
        startActivity(intent)

    }

    override fun editImageClick(note: Note, position: Int) {
        val intent = Intent(this@MainActivity, addActivity::class.java)
        intent.putExtra("noteId", note.id.toString())
        Log.i("noteIDtoAddActivity", note.id.toString())
        startActivity(intent)
    }

    override fun deleteImageClick(note: Note, position: Int) {
        val appDb = NoteDatabase(this)
        Log.i("pressingDeleteImage", "presseddddddddddddd")
        Thread{
            appDb.getNoteDao().deleteNote(note)
            Log.i("pressingDeleteImage", "presseddd22222222222")

        }.start()
        val calling = CallToAction()
        calling.callToast(this@MainActivity, "Note has been deleted")
    }

}
