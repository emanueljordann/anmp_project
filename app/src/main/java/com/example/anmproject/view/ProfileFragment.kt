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
import com.example.anmproject.R
import com.example.anmproject.databinding.FragmentProfileBinding
import com.example.anmproject.databinding.FragmentRegisterBinding
import com.example.anmproject.model.User
import com.example.anmproject.viewmodel.DetailBudgetingViewModel
import com.example.anmproject.viewmodel.DetailUserViewModel
import com.example.anmproject.viewmodel.UserListViewModel


class ProfileFragment : Fragment(),UserEditListener {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: DetailUserViewModel
    private lateinit var user:User
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        viewModel=
            ViewModelProvider(this).get(DetailUserViewModel::class.java)
        binding.editListener=this
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences: SharedPreferences =
            requireContext().getSharedPreferences("SETTING", Context.MODE_PRIVATE)

        val userId = sharedPreferences.getString("uuid","").toString()

        viewModel.selectUser(userId.toString().toInt())
        observeViewModel()
    }
    fun observeViewModel(){
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            if(it != null)
            {
                user=User("","","","")
                user = it
                Log.d("cekUser",it.toString())
            }
        })
    }

    override fun onUserEditPassListener(v:View,password: String) {
        if(binding.textNewPassword.text.toString()!=binding.password){
            Toast.makeText(v.context, "Error Confirm Password is not same", Toast.LENGTH_LONG).show()
        }else if(user.password!=binding.textOldPassword.text.toString()){
            Toast.makeText(v.context, "Error Wrong Password", Toast.LENGTH_LONG).show()
        }else{
            viewModel.updatePassword(user.uuid,password)
            Toast.makeText(v.context, "Todo Updated", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(v).popBackStack()
        }
    }

    override fun signOutListener(v: View) {
        val sharedPreferences: SharedPreferences =
            requireContext().getSharedPreferences("SETTING", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putBoolean("isLoggedIn", false)
        editor.apply()
        requireContext().startActivity(Intent(requireContext(),
            MainActivity::class.java))
    }
}