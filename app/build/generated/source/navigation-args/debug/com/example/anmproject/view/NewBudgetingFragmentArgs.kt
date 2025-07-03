package com.example.anmproject.view

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.jvm.JvmStatic

public data class NewBudgetingFragmentArgs(
  public val nominal: Int = 0,
  public val budgetName: String = "\"\"",
  public val newBudget: Boolean = true,
  public val id: String = "\"\"",
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putInt("nominal", this.nominal)
    result.putString("budgetName", this.budgetName)
    result.putBoolean("newBudget", this.newBudget)
    result.putString("id", this.id)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("nominal", this.nominal)
    result.set("budgetName", this.budgetName)
    result.set("newBudget", this.newBudget)
    result.set("id", this.id)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): NewBudgetingFragmentArgs {
      bundle.setClassLoader(NewBudgetingFragmentArgs::class.java.classLoader)
      val __nominal : Int
      if (bundle.containsKey("nominal")) {
        __nominal = bundle.getInt("nominal")
      } else {
        __nominal = 0
      }
      val __budgetName : String?
      if (bundle.containsKey("budgetName")) {
        __budgetName = bundle.getString("budgetName")
        if (__budgetName == null) {
          throw IllegalArgumentException("Argument \"budgetName\" is marked as non-null but was passed a null value.")
        }
      } else {
        __budgetName = "\"\""
      }
      val __newBudget : Boolean
      if (bundle.containsKey("newBudget")) {
        __newBudget = bundle.getBoolean("newBudget")
      } else {
        __newBudget = true
      }
      val __id : String?
      if (bundle.containsKey("id")) {
        __id = bundle.getString("id")
        if (__id == null) {
          throw IllegalArgumentException("Argument \"id\" is marked as non-null but was passed a null value.")
        }
      } else {
        __id = "\"\""
      }
      return NewBudgetingFragmentArgs(__nominal, __budgetName, __newBudget, __id)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): NewBudgetingFragmentArgs {
      val __nominal : Int?
      if (savedStateHandle.contains("nominal")) {
        __nominal = savedStateHandle["nominal"]
        if (__nominal == null) {
          throw IllegalArgumentException("Argument \"nominal\" of type integer does not support null values")
        }
      } else {
        __nominal = 0
      }
      val __budgetName : String?
      if (savedStateHandle.contains("budgetName")) {
        __budgetName = savedStateHandle["budgetName"]
        if (__budgetName == null) {
          throw IllegalArgumentException("Argument \"budgetName\" is marked as non-null but was passed a null value")
        }
      } else {
        __budgetName = "\"\""
      }
      val __newBudget : Boolean?
      if (savedStateHandle.contains("newBudget")) {
        __newBudget = savedStateHandle["newBudget"]
        if (__newBudget == null) {
          throw IllegalArgumentException("Argument \"newBudget\" of type boolean does not support null values")
        }
      } else {
        __newBudget = true
      }
      val __id : String?
      if (savedStateHandle.contains("id")) {
        __id = savedStateHandle["id"]
        if (__id == null) {
          throw IllegalArgumentException("Argument \"id\" is marked as non-null but was passed a null value")
        }
      } else {
        __id = "\"\""
      }
      return NewBudgetingFragmentArgs(__nominal, __budgetName, __newBudget, __id)
    }
  }
}
