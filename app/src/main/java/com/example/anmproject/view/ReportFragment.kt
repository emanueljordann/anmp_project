package com.example.anmproject.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anmproject.R
import com.example.anmproject.databinding.FragmentReportBinding
import com.example.anmproject.model.Budgeting
import com.example.anmproject.model.Expenses
import com.example.anmproject.viewmodel.BudgetingListViewModel
import com.example.anmproject.viewmodel.DetailExpensesViewModel
import com.example.anmproject.viewmodel.ExpensesListViewModel
import com.example.anmproject.viewmodel.ListViewModel
import java.text.DecimalFormat
import kotlin.math.exp


class ReportFragment : Fragment() {
    private lateinit var viewModel: ExpensesListViewModel
    private lateinit var viewModelBudgeting: BudgetingListViewModel
    private val reportListAdapter  = ReportListAdapter(arrayListOf(),arrayListOf())
    private lateinit var binding: FragmentReportBinding
//    val binding: FragmentReportBinding = DataBindingUtil.setContentView(
//        requireContext(), R.layout.fragment_report)

    var listOfExpenses :ArrayList<Expenses> =ArrayList()
    var listOfBudgeting :ArrayList<Budgeting> =ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReportBinding.inflate(inflater,container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences: SharedPreferences =
            requireContext().getSharedPreferences("SETTING", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("uuid","").toString()

        viewModel = ViewModelProvider(this).get(ExpensesListViewModel::class.java)
        viewModelBudgeting = ViewModelProvider(this).get(BudgetingListViewModel::class.java)
        viewModelBudgeting.refresh(userId)
        viewModel.selectExpenses(userId)

        binding.recViewBudgeting.layoutManager = LinearLayoutManager(context)
        binding.recViewBudgeting.adapter = reportListAdapter
        Log.d("cek user", userId.toString())
        observeViewModel()
    }
    fun observeViewModel() {
        viewModelBudgeting.budgetingLD.observe (viewLifecycleOwner, Observer{
            Log.d("cek budget", it.toString())
            listOfBudgeting.clear()
            listOfBudgeting.addAll(it)

        })
        viewModel.expensesLD.observe (viewLifecycleOwner, Observer{
            Log.d("cekhitung", it.toString())
            listOfExpenses.clear()
            listOfExpenses.addAll(it)
            if(it.isEmpty()) {
                binding.recViewBudgeting?.visibility = View.GONE
                binding.textErorrr?.visibility = View.VISIBLE
                binding.textErorrr.setText("Your Report still empty.")
            } else {
                binding.textErorrr?.visibility = View.GONE
                binding.recViewBudgeting?.visibility = View.VISIBLE
            }
            reportListAdapter.updateReport(listOfBudgeting,listOfExpenses)
            var totalExpens = 0
            var totalBudget = 0
            for (budget in listOfBudgeting){
                totalBudget+=budget.budget.toString().toInt()
            }
            for (expens in listOfExpenses){
                totalExpens+=expens.nominal.toString().toInt()
            }
            binding.textBanding.text="Rp. "+ formatter(totalExpens)+" / "+formatter(totalBudget)
        })
    }
    fun formatter(n: Int) =
        DecimalFormat("#,###")
            .format(n)
            .replace(",", ".")
}