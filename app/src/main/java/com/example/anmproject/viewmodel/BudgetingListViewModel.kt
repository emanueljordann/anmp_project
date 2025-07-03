package com.example.anmproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import buildDb
import com.example.anmproject.model.Budgeting
import com.example.anmproject.model.User
import com.example.anmproject.model.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class BudgetingListViewModel(application: Application)
    :AndroidViewModel(application), CoroutineScope {

    val budgetingLD = MutableLiveData<List<Budgeting>>()
    val budgetingLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh(userId:String) {
        loadingLD.value = true
        budgetingLoadErrorLD.value = false
        launch {
            val db = buildDb(getApplication())
            budgetingLD.postValue(db.budgetingDao().selectBudgeting(userId))
            loadingLD.postValue(false)
        }
    }
    fun clearTask(budget: Budgeting) {
        launch {
            val db = UserDatabase.buildDatabase(
                getApplication()
            )
            db.budgetingDao().deleteBudgeting(budget)

            budgetingLD.postValue(db.budgetingDao().selectAllBudgeting())
        }
    }


}