package com.example.anmproject.view

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.anmproject.R
import kotlin.Boolean
import kotlin.Int
import kotlin.String

public class BudgetingFragmentDirections private constructor() {
  private data class ActionNewBudget(
    public val nominal: Int = 0,
    public val budgetName: String = "\"\"",
    public val newBudget: Boolean = true,
    public val id: String = "\"\"",
  ) : NavDirections {
    public override val actionId: Int = R.id.actionNewBudget

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putInt("nominal", this.nominal)
        result.putString("budgetName", this.budgetName)
        result.putBoolean("newBudget", this.newBudget)
        result.putString("id", this.id)
        return result
      }
  }

  public companion object {
    public fun actionNewBudget(
      nominal: Int = 0,
      budgetName: String = "\"\"",
      newBudget: Boolean = true,
      id: String = "\"\"",
    ): NavDirections = ActionNewBudget(nominal, budgetName, newBudget, id)
  }
}
