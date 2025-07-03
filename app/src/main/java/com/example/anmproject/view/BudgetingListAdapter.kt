package com.example.anmproject.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.anmproject.databinding.BudgetingListItemBinding
import com.example.anmproject.model.Budgeting
import java.text.DecimalFormat

class BudgetingListAdapter (val budgetingList:ArrayList<Budgeting>)
:RecyclerView.Adapter<BudgetingListAdapter.BudgetingViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BudgetingViewHolder {
        var binding = BudgetingListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent,false)
        return BudgetingViewHolder(binding)

    }
    fun formatter(n: Int) =
        DecimalFormat("#,###")
            .format(n)
            .replace(",", ".")

    override fun onBindViewHolder(
        holder: BudgetingViewHolder,
        position: Int
    ) {
        val nama = budgetingList[position].name.toString()
        val nominal = budgetingList[position].budget.toString().toInt()
        holder.binding.textKategoriBudgeting.text = nama
        holder.binding.textNominalBudgeting.text = "Rp. "+formatter(nominal)

        holder.binding.cardBudget.setOnClickListener {
            val action = BudgetingFragmentDirections.actionNewBudget(nominal,nama,newBudget = false,(position+1).toString())
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return budgetingList.size
    }

    class BudgetingViewHolder(var binding: BudgetingListItemBinding):
        RecyclerView.ViewHolder(binding.root)

    fun updateTodoList(newBudgetingList: List<Budgeting>) {
        budgetingList.clear()
        budgetingList.addAll(newBudgetingList)
        notifyDataSetChanged()
    }

}
