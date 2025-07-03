package com.example.anmproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import buildDb
import com.example.anmproject.model.User
import com.example.anmproject.model.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailUserViewModel (application:  Application)
    :AndroidViewModel(application), CoroutineScope {
    val userLD = MutableLiveData<User>()

    private val job = Job()

    fun addTodo(list:List<User>) {
        launch {
            val db = buildDb(getApplication())
            db.userDao().insertAll(*list.toTypedArray())
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun login(username:String,pass:String) {
        launch {
            val db = buildDb(getApplication())
            userLD.postValue(db.userDao().Login(username,pass))
        }
    }

    fun cekUsername(username:String) {
        launch {
            val db = buildDb(getApplication())
            userLD.postValue(db.userDao().cekUsername(username))
        }
    }

    fun selectUser(userId:Int) {
        launch {
            val db = buildDb(getApplication())
            userLD.postValue(db.userDao().selectUser(userId))
        }
    }

    fun updatePassword(userId:Int,password:String){
        launch {
            val db = buildDb(getApplication())
            db.userDao().updatePass(userId,password)
        }
    }

}