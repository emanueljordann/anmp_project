package com.example.anmproject.view

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.anmproject.R

public class NewBudgetingFragmentDirections private constructor() {
  public companion object {
    public fun actionBudgeting(): NavDirections = ActionOnlyNavDirections(R.id.actionBudgeting)
  }
}
