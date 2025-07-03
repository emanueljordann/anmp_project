package com.example.anmproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import buildDb
import com.example.anmproject.model.Budgeting
import com.example.anmproject.model.Expenses
import com.example.anmproject.model.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ExpensesListViewModel(application: Application)
    :AndroidViewModel(application), CoroutineScope {

    val expensesLD = MutableLiveData<List<Expenses>>()
    val expensesLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh(userId:String) {
        loadingLD.value = true
        expensesLoadErrorLD.value = false
        launch {
            val db = buildDb(getApplication())
            expensesLD.postValue(db.expensesDao().selectExpenses(userId))
            loadingLD.postValue(false)
        }
    }

    fun selectExpenses(userId:String) {
        loadingLD.value = true
        expensesLoadErrorLD.value = false
        launch {
            val db = buildDb(getApplication())
            expensesLD.postValue(db.expensesDao().selectExpenses(userId))
            loadingLD.postValue(false)
        }
    }

    fun clearTask(expenses: Expenses) {
        launch {
            val db = UserDatabase.buildDatabase(
                getApplication()
            )
            db.expensesDao().deleteExpenses(expenses)

            expensesLD.postValue(db.expensesDao().selectAllExpenses())
        }
    }


}