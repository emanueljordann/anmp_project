package com.example.anmproject.view

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.anmproject.R
import kotlin.Int
import kotlin.String

public class ExpensesFragmentDirections private constructor() {
  private data class ActionDialog(
    public val tanggal: String?,
    public val deskripsi: String?,
    public val kategori: String?,
    public val total: Int = 0,
  ) : NavDirections {
    public override val actionId: Int = R.id.actionDialog

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("tanggal", this.tanggal)
        result.putString("deskripsi", this.deskripsi)
        result.putString("kategori", this.kategori)
        result.putInt("total", this.total)
        return result
      }
  }

  public companion object {
    public fun actionNewExpenses(): NavDirections = ActionOnlyNavDirections(R.id.actionNewExpenses)

    public fun actionDialog(
      tanggal: String?,
      deskripsi: String?,
      kategori: String?,
      total: Int = 0,
    ): NavDirections = ActionDialog(tanggal, deskripsi, kategori, total)
  }
}
