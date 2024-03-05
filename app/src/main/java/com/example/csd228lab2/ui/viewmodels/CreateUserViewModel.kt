package com.example.csd228lab2.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.csd228lab2.models.User

class CreateUserViewModel : ViewModel() {
    fun createUser( userName: String, email: String) {
        var u1 = User(id = 1, userName = userName, email = email, avatar = 'A')
        Log.d("CreateUserViewModel",u1.toString())

    }
}