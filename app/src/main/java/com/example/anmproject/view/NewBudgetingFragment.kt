package com.example.anmproject.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.anmproject.R
import com.example.anmproject.databinding.FragmentBudgetingBinding
import com.example.anmproject.databinding.FragmentNewBudgetingBinding
import com.example.anmproject.model.Budgeting
import com.example.anmproject.model.Expenses
import com.example.anmproject.model.User
import com.example.anmproject.viewmodel.DetailBudgetingViewModel
import com.example.anmproject.viewmodel.ExpensesListViewModel
import kotlin.times


class NewBudgetingFragment : Fragment() {
    private lateinit var viewModel: DetailBudgetingViewModel
    private lateinit var viewModelExpenses: ExpensesListViewModel
    private lateinit var binding: FragmentNewBudgetingBinding
    var totalExpens:Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewBudgetingBinding.inflate(
            inflater,
            container, false
        )
        viewModel =
            ViewModelProvider(this).get(DetailBudgetingViewModel::class.java)
        viewModelExpenses=
            ViewModelProvider(this).get(ExpensesListViewModel::class.java)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var idBudget = 0
        if(NewBudgetingFragmentArgs.fromBundle(requireArguments()).newBudget==false) {
            val nominal = NewBudgetingFragmentArgs.fromBundle(requireArguments()).nominal
            val nama = NewBudgetingFragmentArgs.fromBundle(requireArguments()).budgetName
            idBudget = NewBudgetingFragmentArgs.fromBundle(requireArguments()).id.toString().toInt()
            observeViewModel(idBudget)
            binding.buttonEdit?.visibility = View.VISIBLE
            binding.buttonEdit?.isEnabled = true
            binding.buttonAddNewBudget?.visibility = View.GONE
            binding.buttonAddNewBudget?.isEnabled = false
            binding.textNewBudgeting.hint=nama
            binding.textNominalNewBudget.hint=nominal.toString()
        } else {
            binding.buttonEdit?.visibility = View.GONE
            binding.buttonEdit?.isEnabled = false
            binding.buttonAddNewBudget?.visibility = View.VISIBLE
            binding.buttonAddNewBudget?.isEnabled = true
        }

        val sharedPreferences: SharedPreferences =
            requireContext().getSharedPreferences("SETTING", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val userId = sharedPreferences.getString("uuid","")

        viewModelExpenses.selectExpenses(userId.toString())


        binding.buttonAddNewBudget.setOnClickListener {
            val newBudget=binding.textNominalNewBudget.text.toString().toInt()
            val newName = binding.textNewBudgeting.text.toString()
            if(newBudget<0){
                Toast.makeText(view.context, "Error Nominal Tidak Boleh Negatif", Toast.LENGTH_LONG).show()
            }else{
                val budgetBaru = Budgeting(
                    userId,
                    newName,
                    newBudget
                )
                val list = listOf(budgetBaru)
                viewModel.addBudget(list)
                Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()
                Navigation.findNavController(it).popBackStack()
            }
        }

        binding.buttonEdit.setOnClickListener {
            val newBudget=binding.textNominalNewBudget.text.toString().toInt()
            val newName = binding.textNewBudgeting.text.toString()
            if(totalExpens>newBudget){
                Toast.makeText(view.context, "Error ! Expenses lebih besar dari pada budget", Toast.LENGTH_LONG).show()
            }else{
                viewModel.editBudget(
                    idBudget.toString(),
                    userId.toString(),
                    newName,
                    newBudget
                )
                Toast.makeText(view.context, "Data Edited", Toast.LENGTH_LONG).show()
                Navigation.findNavController(it).popBackStack()
            }
        }
    }
    fun observeViewModel(idBudget:Int){
        viewModelExpenses.expensesLD.observe(viewLifecycleOwner, Observer {
            for(expens in it){
                if(expens.idBudgeting==idBudget.toString()){
                    totalExpens+=expens.nominal.toString().toInt()
                }
            }
        })
    }
}
