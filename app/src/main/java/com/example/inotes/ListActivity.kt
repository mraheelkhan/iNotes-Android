package com.example.inotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inotes.adaptors.UserAdaptor
import com.example.inotes.db.NoteDatabase
import com.example.inotes.db.UserEntity
import com.example.inotes.model.UserModel
import com.example.inotes.model.Users
import com.example.inotes.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val appDb = NoteDatabase(this)
        val viewManger = LinearLayoutManager(this)
        userlist_recycleview.setHasFixedSize(true)
        userlist_recycleview.layoutManager = viewManger

        val userStore = ArrayList<UserEntity>()
        var dbUsers = ArrayList<UserModel>()

            RetrofitClient.instance.getAllUsers()
                .enqueue(object : Callback<List<UserModel>>{
                    override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
                        Log.e("Notworking", "sajflsajfasjdf WORKING")
                    }

                    override fun onResponse(
                        call: Call<List<UserModel>>,
                        response: Response<List<UserModel>>
                    ) {
                        val users = response?.body();
                        users?.forEach {
                            userStore.add(UserEntity(it.name, it.store.toString()))
                        }
                        Thread{
                            appDb.getUserDao().insertAllUsers(userStore)
                        }.start()
                    }

                })

    }
}
