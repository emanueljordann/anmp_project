package com.example.anmproject.view

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.anmproject.R

public class RegisterFragmentDirections private constructor() {
  public companion object {
    public fun actionLogin(): NavDirections = ActionOnlyNavDirections(R.id.actionLogin)
  }
}
