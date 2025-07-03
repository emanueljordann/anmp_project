package com.example.anmproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import buildDb
import com.example.anmproject.model.Budgeting
import com.example.anmproject.model.Expenses
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailExpensesViewModel (application:  Application)
    :AndroidViewModel(application), CoroutineScope {
    val expensesLD = MutableLiveData<Expenses>()
    val expensesHitungLD = MutableLiveData<Int>()
    var loadingLD = MutableLiveData<Boolean>()
    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun addExpenses (list:List<Expenses>){
        loadingLD.value=true
        launch {
            val db = buildDb(getApplication())
            db.expensesDao().insertAll(*list.toTypedArray())
            loadingLD.value=false
        }
    }

    fun hitung(userId:String,budgetId:String){
        launch {
            val db = buildDb(getApplication())

            expensesHitungLD.postValue(db.expensesDao().selectTotalExpensesBudget(userId,budgetId))
        }
    }
}