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
import com.example.anmproject.databinding.FragmentLoginBinding
import com.example.anmproject.viewmodel.DetailUserViewModel
import com.example.anmproject.viewmodel.UserListViewModel

class LoginFragment : Fragment() {
    private lateinit var viewModel: DetailUserViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(
            inflater,
            container, false
        )
        viewModel =
            ViewModelProvider(this).get(DetailUserViewModel::class.java)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences: SharedPreferences =
            requireContext().getSharedPreferences("SETTING", Context.MODE_PRIVATE)
        val ceklogin = sharedPreferences.getBoolean("isLoggedIn",false)
        if(ceklogin==true){
            requireContext().startActivity(Intent(requireContext(),
                BudgetActivity::class.java))
        }


        binding.buttonLogin.setOnClickListener {
            var user = binding.textName.text.toString()
            var pass = binding.textPassword.text.toString()
            viewModel.login(user,pass)

            observeViewModel()
        }
        binding.buttonRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionRegister()
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun observeViewModel() {
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            if (it == null){
                Toast.makeText(context,"Error ! Username atau Password salah",Toast.LENGTH_SHORT).show()
            }else{
                val sharedPreferences: SharedPreferences =
                    requireContext().getSharedPreferences("SETTING", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                Log.d("cek it", it.toString())
                editor.putString("uuid", it.uuid.toString())
                editor.putBoolean("isLoggedIn", true)
                editor.apply()
                binding.buttonLogin.context.startActivity(Intent(binding.buttonLogin.context,
                    BudgetActivity::class.java))
            }

        })
    }

}
