package com.example.anmproject.view

import android.view.View
import android.widget.CompoundButton
import com.example.anmproject.model.User

interface UserEditListener{
    fun onUserEditPassListener(v:View,password:String)
    fun signOutListener(v:View)
}
