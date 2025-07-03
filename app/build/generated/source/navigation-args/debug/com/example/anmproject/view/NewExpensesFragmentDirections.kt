package com.example.anmproject.view

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.anmproject.R

public class NewExpensesFragmentDirections private constructor() {
  public companion object {
    public fun actionExpenses(): NavDirections = ActionOnlyNavDirections(R.id.actionExpenses)
  }
}
