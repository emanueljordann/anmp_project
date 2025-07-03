package com.example.anmproject.viewmodel

import android.app.Application
import android.service.autofill.UserData
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import buildDb
import com.example.anmproject.model.User
import com.example.anmproject.model.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserListViewModel(application: Application)
    :AndroidViewModel(application), CoroutineScope {

    val userLD = MutableLiveData<List<User>>()
    val userLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
    fun refresh() {
        loadingLD.value = true
        userLoadErrorLD.value = false
        launch {
            val db = buildDb(getApplication())

            userLD.postValue(db.userDao().selectAllUser())
            loadingLD.postValue(false)
        }
    }
    fun clearTask(user: User) {
        launch {
            val db = UserDatabase.buildDatabase(
                getApplication()
            )
            db.userDao().deleteTodo(user)

            userLD.postValue(db.userDao().selectAllUser())
        }
    }


}