package com.example.anmproject.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.anmproject.databinding.ExpensesListItemBinding
import com.example.anmproject.model.Budgeting
import com.example.anmproject.model.Expenses
import java.text.DecimalFormat
import kotlin.math.exp

class ExpensesListAdapter (val expensesList:ArrayList<Expenses>,val listName: ArrayList<String>)
    :RecyclerView.Adapter<ExpensesListAdapter.ExpensesViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExpensesViewHolder {
        val binding = ExpensesListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ExpensesViewHolder(binding)

    }
    fun formatter(n: Int) =
        DecimalFormat("#,###")
            .format(n)
            .replace(",", ".")

    override fun onBindViewHolder(
        holder: ExpensesViewHolder,
        position: Int
    ) {
        val tanggal = expensesList[position].tanggal
        val kategori = listName[expensesList[position].idBudgeting.toString().toInt()-1]
        val expens = expensesList[position].nominal.toString().toInt()

        holder.binding.textNominalExpenses.text = "Rp. "+formatter(expens)
        holder.binding.textKategori.text = kategori
        holder.binding.textTanggalExpenses.text = tanggal

        holder.binding.expensesCard.setOnClickListener {
            val action = ExpensesFragmentDirections.actionDialog(tanggal,expensesList[position].deskripsi,kategori,expens)
            Navigation.findNavController(it).navigate(action)
        }

    }
    fun updateStudentList(newExpensesList: List<Expenses>,newlistName:List<String>) {
        expensesList.clear()
        listName.clear()
        listName.addAll(newlistName)
        expensesList.addAll(newExpensesList)
        notifyDataSetChanged()
    }



    override fun getItemCount(): Int {
        return expensesList.size
    }

    class ExpensesViewHolder (var binding: ExpensesListItemBinding)
        :RecyclerView.ViewHolder(binding.root)
    }