package com.example.anmproject.view

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Int
import kotlin.String
import kotlin.jvm.JvmStatic

public data class DialogFragmentArgs(
  public val tanggal: String?,
  public val deskripsi: String?,
  public val kategori: String?,
  public val total: Int = 0,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("tanggal", this.tanggal)
    result.putString("deskripsi", this.deskripsi)
    result.putString("kategori", this.kategori)
    result.putInt("total", this.total)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("tanggal", this.tanggal)
    result.set("deskripsi", this.deskripsi)
    result.set("kategori", this.kategori)
    result.set("total", this.total)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): DialogFragmentArgs {
      bundle.setClassLoader(DialogFragmentArgs::class.java.classLoader)
      val __tanggal : String?
      if (bundle.containsKey("tanggal")) {
        __tanggal = bundle.getString("tanggal")
      } else {
        throw IllegalArgumentException("Required argument \"tanggal\" is missing and does not have an android:defaultValue")
      }
      val __deskripsi : String?
      if (bundle.containsKey("deskripsi")) {
        __deskripsi = bundle.getString("deskripsi")
      } else {
        throw IllegalArgumentException("Required argument \"deskripsi\" is missing and does not have an android:defaultValue")
      }
      val __kategori : String?
      if (bundle.containsKey("kategori")) {
        __kategori = bundle.getString("kategori")
      } else {
        throw IllegalArgumentException("Required argument \"kategori\" is missing and does not have an android:defaultValue")
      }
      val __total : Int
      if (bundle.containsKey("total")) {
        __total = bundle.getInt("total")
      } else {
        __total = 0
      }
      return DialogFragmentArgs(__tanggal, __deskripsi, __kategori, __total)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): DialogFragmentArgs {
      val __tanggal : String?
      if (savedStateHandle.contains("tanggal")) {
        __tanggal = savedStateHandle["tanggal"]
      } else {
        throw IllegalArgumentException("Required argument \"tanggal\" is missing and does not have an android:defaultValue")
      }
      val __deskripsi : String?
      if (savedStateHandle.contains("deskripsi")) {
        __deskripsi = savedStateHandle["deskripsi"]
      } else {
        throw IllegalArgumentException("Required argument \"deskripsi\" is missing and does not have an android:defaultValue")
      }
      val __kategori : String?
      if (savedStateHandle.contains("kategori")) {
        __kategori = savedStateHandle["kategori"]
      } else {
        throw IllegalArgumentException("Required argument \"kategori\" is missing and does not have an android:defaultValue")
      }
      val __total : Int?
      if (savedStateHandle.contains("total")) {
        __total = savedStateHandle["total"]
        if (__total == null) {
          throw IllegalArgumentException("Argument \"total\" of type integer does not support null values")
        }
      } else {
        __total = 0
      }
      return DialogFragmentArgs(__tanggal, __deskripsi, __kategori, __total)
    }
  }
}
