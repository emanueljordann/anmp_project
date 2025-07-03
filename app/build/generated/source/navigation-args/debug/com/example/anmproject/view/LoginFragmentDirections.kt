package com.example.anmproject.view

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.anmproject.R

public class LoginFragmentDirections private constructor() {
  public companion object {
    public fun actionRegister(): NavDirections = ActionOnlyNavDirections(R.id.actionRegister)
  }
}
