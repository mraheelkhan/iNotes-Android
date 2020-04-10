package com.example.inotes

import android.content.Context
import android.widget.Toast

class CallToAction {

    fun callToast(context : Context, message : String ){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}