package com.example.anmproject.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg user: User)

    @Query("SELECT * FROM user")
    fun selectAllUser(): List<User>

    @Query("SELECT * FROM user WHERE uuid= :id")
    fun selectUser(id:Int): User

    @Query("SELECT * FROM user WHERE username= :username AND password= :pass")
    fun Login(username:String,pass:String): User

    @Query("SELECT * FROM user WHERE username= :username")
    fun cekUsername(username: String):User

    @Query("UPDATE user SET password=:password WHERE uuid= :id")
    fun updatePass(id:Int,password: String)

    @Delete
    fun deleteTodo(user:User)
}
