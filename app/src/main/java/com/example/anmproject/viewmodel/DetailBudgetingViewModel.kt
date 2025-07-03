package com.example.anmproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import buildDb
import com.example.anmproject.model.Budgeting
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailBudgetingViewModel (application:  Application)
    :AndroidViewModel(application), CoroutineScope {
    val budgetingLD = MutableLiveData<Budgeting>()
    var loadingLD = MutableLiveData<Boolean>()
    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun addBudget (list:List<Budgeting>){
        launch {
            val db = buildDb(getApplication())
            db.budgetingDao().insertAll(*list.toTypedArray())
        }
    }
    fun editBudget (id:String,userid:String,nama:String,budget:Int){
        launch {
            val db = buildDb(getApplication())
            db.budgetingDao().updateBudgeting(id,userid,nama,budget)
        }
    }

}