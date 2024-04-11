package com.example.csd228lab2.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.csd228lab2.database.ChatAppDatabase
import com.example.csd228lab2.models.User
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/* This is a ViewModel class that is used to create a new user
* For now, it simply creates a new user in memory and logs the user's information
 */
class CreateUserViewModel(private val userDao: ChatAppDatabase.UserDao) : ViewModel() {
    fun createUser(userName: String, email: String) {
        val u1 = User(userName = userName, email = email)
        userDao.insertUser(u1)
        Log.d("CreateUserViewModel",u1.toString())
    }
}