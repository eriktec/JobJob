package com.res.jobjob.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.res.jobjob.R
import com.res.jobjob.common.base.BaseFragment
import com.res.jobjob.common.handler.Handlers
import com.res.jobjob.common.vo.Resource
import com.res.jobjob.databinding.FragmentLoginBinding
import com.res.jobjob.repository.RepoFactory
import com.res.jobjob.vm.login.LoginFactory
import com.res.jobjob.vm.login.LoginViewModel

class LoginFragment : BaseFragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by lazy {
        ViewModelProvider(this, LoginFactory(RepoFactory()))[LoginViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        Handlers().also { binding.handler = it }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            if (email.checkString() && password.checkString()) {
                viewModel.loginUser(email, password)
                viewModel.status.observe(viewLifecycleOwner, { resource ->
                    when (resource) {
                        is Resource.Loading -> alertDialog.show()
                        is Resource.Success -> successLogin(resource.data)
                        is Resource.Error -> error(resource.errorMsg)
                    }
                })
            } else error("Cheque que todos los campos esten llenos")
        }
    }

    private fun successLogin(id: String) {
        viewModel.getUser(id).observe(viewLifecycleOwner, { resource ->
            when (resource) {
                is Resource.Loading -> Log.d("Hi", "Buscando el usuario")
                is Resource.Success -> success(resource.data)
                is Resource.Error -> error(resource.errorMsg)
            }
        })
    }
}