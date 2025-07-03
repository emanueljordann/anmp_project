package com.example.anmproject.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anmproject.R
import com.example.anmproject.databinding.FragmentExpensesBinding
import com.example.anmproject.databinding.FragmentLoginBinding
import com.example.anmproject.viewmodel.BudgetingListViewModel
import com.example.anmproject.viewmodel.ExpensesListViewModel


class ExpensesFragment : Fragment() {
    private lateinit var binding: FragmentExpensesBinding
    private lateinit var viewModel: ExpensesListViewModel
    private lateinit var viewModelBudgeting: BudgetingListViewModel
    private val expensesListAdapter  = ExpensesListAdapter(arrayListOf(),arrayListOf())
    var listOfBudgetName :ArrayList<String> =ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentExpensesBinding.inflate(
            inflater,
            container, false
        )
        viewModel =
            ViewModelProvider(this).get(ExpensesListViewModel::class.java)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences: SharedPreferences =
        requireContext().getSharedPreferences("SETTING", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("uuid","").toString()

        viewModelBudgeting = ViewModelProvider(this).get(BudgetingListViewModel::class.java)
        viewModelBudgeting.refresh(userId)
        observeViewModelBudgeting()

        viewModel = ViewModelProvider(this).get(ExpensesListViewModel::class.java)
        viewModel.refresh(userId)
        binding.recViewExpenses.layoutManager = LinearLayoutManager(context)
        binding.recViewExpenses.adapter = expensesListAdapter


        binding.fabExpenses.setOnClickListener {

            val action = ExpensesFragmentDirections.actionNewExpenses()
            Navigation.findNavController(it).navigate(action)
        }


    }
    fun observeViewModel() {
        viewModel.expensesLD.observe(viewLifecycleOwner, Observer {
            if(it.isEmpty()) {
                binding.recViewExpenses?.visibility = View.GONE
                binding.textEror?.visibility = View.VISIBLE
                binding.textEror.setText("Your Budget still empty.")
            } else {
                Log.d("cek expenses",it.toString())
                Log.d("cek budget",listOfBudgetName[0].toString())

                expensesListAdapter.updateStudentList(it,listOfBudgetName)
                binding.textEror?.visibility = View.GONE
                binding.recViewExpenses?.visibility = View.VISIBLE
            }
        })

    }
    fun observeViewModelBudgeting() {
        viewModelBudgeting.budgetingLD.observe(viewLifecycleOwner, Observer {
            for (budgetname in it){
                listOfBudgetName.add(budgetname.name.toString())
            }
            observeViewModel()
        })

    }
}
