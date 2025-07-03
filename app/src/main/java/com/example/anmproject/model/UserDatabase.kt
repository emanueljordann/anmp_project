package com.example.anmproject.model

import DB_NAME
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(User::class,Expenses::class,Budgeting::class), version =  2)
abstract class UserDatabase:RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun budgetingDao(): BudgetingDao
    abstract fun expensesDao(): ExpensesDao

    companion object {
        @Volatile private var instance: UserDatabase ?= null
        private val LOCK = Any()

        fun buildDatabase(context:Context) =
            Room.databaseBuilder(
                context.applicationContext,
                UserDatabase::class.java,
                DB_NAME).fallbackToDestructiveMigration().build()
        
        operator fun invoke(context:Context) {
            if(instance == null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }

    }
}
