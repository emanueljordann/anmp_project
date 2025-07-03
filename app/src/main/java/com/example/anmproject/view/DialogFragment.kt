package com.example.anmproject.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anmproject.R
import com.example.anmproject.databinding.FragmentBudgetingBinding
import com.example.anmproject.databinding.FragmentDialogBinding
import com.example.anmproject.viewmodel.BudgetingListViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.DecimalFormat


open class DialogFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDialogBinding.inflate(
            inflater,
            container, false
        )
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val deskripsi = DialogFragmentArgs.fromBundle(requireArguments()).deskripsi
        val tanggal = DialogFragmentArgs.fromBundle(requireArguments()).tanggal
        val kategori = DialogFragmentArgs.fromBundle(requireArguments()).kategori
        val expens = DialogFragmentArgs.fromBundle(requireArguments()).total

        binding.textTotalDialog.text = "Rp. "+formatter(expens)
        binding.textTanggalDialog.text = tanggal
        binding.textDeskripsiDialog.text = deskripsi
        binding.textKategoriDialog.text = kategori
    }
    fun formatter(n: Int) =
        DecimalFormat("#,###")
            .format(n)
            .replace(",", ".")

}