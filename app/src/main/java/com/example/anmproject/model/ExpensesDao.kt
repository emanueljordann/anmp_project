package com.example.anmproject.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ExpensesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg expenses: Expenses)

    @Query("SELECT * FROM expenses")
    fun selectAllExpenses(): List<Expenses>

    @Query("SELECT * FROM expenses WHERE idUser= :id")
    fun selectExpenses(id:String): List<Expenses>

    @Query("SELECT SUM(nominal) FROM expenses WHERE idUser= :id AND idBudgeting=:budgetId")
    fun selectTotalExpensesBudget(id:String,budgetId:String): Int

    @Delete
    fun deleteExpenses(expenses:Expenses)
}