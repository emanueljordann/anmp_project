package com.example.anmproject.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.anmproject.databinding.ExpensesListItemBinding
import com.example.anmproject.databinding.ReportListItemBinding
import com.example.anmproject.model.Budgeting
import com.example.anmproject.model.Expenses
import com.example.anmproject.view.ExpensesListAdapter.ExpensesViewHolder
import java.text.DecimalFormat
import kotlin.math.exp

class ReportListAdapter(val budgetingList:ArrayList<Budgeting>,val expensesList: ArrayList<Expenses>)
    :RecyclerView.Adapter<ReportListAdapter.ReportViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReportListAdapter.ReportViewHolder {
        val binding = ReportListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ReportViewHolder(binding)
    }
    fun formatter(n: Int) =
        DecimalFormat("#,###")
            .format(n)
            .replace(",", ".")

    override fun onBindViewHolder(
        holder: ReportListAdapter.ReportViewHolder,
        position: Int
    ) {
        var total = 0
        for (i in expensesList){
            if(i.idBudgeting==budgetingList[position].uuid.toString()){
                total+=i.nominal.toString().toInt()
            }
        }
        Log.d("cek total",total.toString())
        val maxBudget = budgetingList[position].budget.toString().toInt()
        var selisih = maxBudget-total
        holder.binding.textNama.text = budgetingList[position].name.toString()
        holder.binding.textSelisihBudget.text ="Budget Left : Rp. "+formatter(selisih)
        holder.binding.textCurrentBudgetNew.text = "Rp. "+formatter(total)
        holder.binding.textMaxBudget.text = "Rp. "+formatter(maxBudget)
        holder.binding.progressBarReport.setProgress(total*100/maxBudget)


        holder.binding.cardReport.setOnClickListener(){

        }
    }

    fun updateReport(newBudgeting: List<Budgeting>,newExpenses:List<Expenses>){
        expensesList.clear()
        budgetingList.clear()
        budgetingList.addAll(newBudgeting)
        expensesList.addAll(newExpenses)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return budgetingList.size
    }
    class ReportViewHolder (var binding: ReportListItemBinding)
        :RecyclerView.ViewHolder(binding.root)

}

