package com.example.anmproject.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.anmproject.databinding.FragmentNewExpensesBinding
import com.example.anmproject.model.Budgeting
import com.example.anmproject.model.Expenses
import com.example.anmproject.viewmodel.BudgetingListViewModel
import com.example.anmproject.viewmodel.DetailExpensesViewModel
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class NewExpensesFragment : Fragment() {
    private lateinit var binding: FragmentNewExpensesBinding
    private lateinit var viewModelExpenses: DetailExpensesViewModel
    private lateinit var viewModelBudgeting: BudgetingListViewModel
    var listOfBudget :ArrayList<Budgeting> =ArrayList()
    var listOfBudgetName :ArrayList<String> =ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewExpensesBinding.inflate(
            inflater,
            container, false
        )
        viewModelExpenses =
            ViewModelProvider(this).get(DetailExpensesViewModel::class.java)
        viewModelBudgeting =
            ViewModelProvider(this).get(BudgetingListViewModel::class.java)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sharedPreferences: SharedPreferences =
            requireContext().getSharedPreferences("SETTING", Context.MODE_PRIVATE)

        val userId = sharedPreferences.getString("uuid","").toString()
        var budgetingId=0
        val currentExpenses=0
        var budgetMax=0

        viewModelExpenses =
            ViewModelProvider(this).get(DetailExpensesViewModel::class.java)
        viewModelBudgeting = ViewModelProvider(this).get(BudgetingListViewModel::class.java)
        viewModelBudgeting.refresh(userId)
        observeViewModelBudgeting()
        val currentDate: String? =
            SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
        binding.textTanggal.text=currentDate
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAdd.setOnClickListener {
            val nominal = binding.textNominal.text.toString().toInt()

            if(nominal<0){
                Toast.makeText(view.context, "Error Nominal Tidak Boleh Negatif", Toast.LENGTH_LONG).show()
            }else if(currentExpenses+nominal>budgetMax){
                Toast.makeText(view.context, "Error Expens Anda Lebih Dari Budget", Toast.LENGTH_LONG).show()
            } else{
                val expensesBaru = Expenses(
                    userId,
                    budgetingId.toString(),
                    tanggal = currentDate,
                    nominal = nominal,
                    deskripsi = binding.textDeskripsi.text.toString(),
                )
                val list = listOf(expensesBaru)
                Log.d("cek expens",list.toString())
                viewModelExpenses.addExpenses(list)
                Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()
                Navigation.findNavController(it).popBackStack()
            }
        }

        binding.spinnerKategori.onItemSelectedListener= object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                budgetingId=position+1
                viewModelExpenses.hitung(userId,budgetingId.toString())
                Log.d("cek posisi",position.toString())

                budgetMax=listOfBudget[position].budget.toString().toInt()

                observeViewModelExpenses(listOfBudget[position].budget.toString().toInt())
                binding.textMaxBudgetNew.text = "Rp. "+formatter(budgetMax)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }
    }

    fun observeViewModelExpenses(budget:Int){
        viewModelExpenses.expensesHitungLD.observe(viewLifecycleOwner, Observer {

            if(it==null){
                binding.textCurrentExpensesNew.text="0"
            }else{
                binding.progressBar2.setProgress((it*100/budget))
                Log.d("Cek Hitung",(it*100/budget).toString())
                binding.textCurrentExpensesNew.text= "Rp. "+formatter(it)
            }
        })
    }


    fun observeViewModelBudgeting() {
        viewModelBudgeting.budgetingLD.observe(viewLifecycleOwner, Observer {
            for (budget in it){
                listOfBudget.add(budget)
            }
            for (budgetname in listOfBudget){
                listOfBudgetName.add(budgetname.name.toString())
            }
            val budgetAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item,listOfBudgetName)
            budgetAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerKategori.adapter = budgetAdapter
        })

    }
    fun formatter(n: Int) =
        DecimalFormat("#,###")
            .format(n)
            .replace(",", ".")

}