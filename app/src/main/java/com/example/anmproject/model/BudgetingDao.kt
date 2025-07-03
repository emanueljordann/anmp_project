package com.example.anmproject.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface BudgetingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg budgeting: Budgeting)

    @Query("SELECT * FROM budgeting")
    fun selectAllBudgeting(): List<Budgeting>

    @Query("SELECT * FROM budgeting WHERE idUser= :id")
    fun selectBudgeting(id:String): List<Budgeting>

    @Query("UPDATE budgeting SET name=:nama, budget=:budget WHERE uuid= :id and idUser=:userid")
    fun updateBudgeting(id:String,userid:String,nama:String,budget:Int)

    @Delete
    fun deleteBudgeting(budgeting:Budgeting)
}
