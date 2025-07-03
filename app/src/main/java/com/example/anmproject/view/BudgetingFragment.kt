package com.example.anmproject.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anmproject.R
import com.example.anmproject.databinding.FragmentBudgetingBinding
import com.example.anmproject.databinding.FragmentNewBudgetingBinding
import com.example.anmproject.viewmodel.BudgetingListViewModel
import com.example.anmproject.viewmodel.DetailBudgetingViewModel


class BudgetingFragment : Fragment() {
    private lateinit var viewModel: BudgetingListViewModel
    private lateinit var binding: FragmentBudgetingBinding
    private val budgetingListAdapter  = BudgetingListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBudgetingBinding.inflate(
            inflater,
            container, false
        )
        viewModel =
            ViewModelProvider(this).get(BudgetingListViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences: SharedPreferences =
            requireContext().getSharedPreferences("SETTING", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("uuid","").toString()

        viewModel = ViewModelProvider(this).get(BudgetingListViewModel::class.java)
        viewModel.refresh(userId)
        binding.recViewBudgeting.layoutManager = LinearLayoutManager(context)
        binding.recViewBudgeting.adapter = budgetingListAdapter

        observeViewModel()

        binding.fabAdd.setOnClickListener {
            val action = BudgetingFragmentDirections.actionNewBudget()
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun observeViewModel() {
        viewModel.budgetingLD.observe(viewLifecycleOwner, Observer {
            budgetingListAdapter.updateTodoList(it)
            if(it.isEmpty()) {
                binding.recViewBudgeting?.visibility = View.GONE
                binding.txtError?.visibility = View.VISIBLE
                binding.txtError.setText("Your Budget still empty.")
            } else {
                binding.txtError?.visibility = View.GONE
                binding.recViewBudgeting?.visibility = View.VISIBLE
            }
        })

    }
}