package com.example.csd228lab2.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.csd228lab2.models.User

/* This is a ViewModel class that is used to create a new user
* For now, it simply creates a new user in memory and logs the user's information
 */
class CreateUserViewModel : ViewModel() {
    fun createUser( userName: String, email: String) {
        var u1 = User(id = 1, userName = userName, email = email, avatar = 'A')
        Log.d("CreateUserViewModel",u1.toString())

    }
}