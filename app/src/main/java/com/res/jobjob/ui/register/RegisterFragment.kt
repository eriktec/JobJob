package com.res.jobjob.ui.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.res.jobjob.R
import com.res.jobjob.common.base.BaseFragment
import com.res.jobjob.common.handlers.Handlers
import com.res.jobjob.common.vo.Resource
import com.res.jobjob.databinding.FragmentRegisterBinding
import com.res.jobjob.model.data.User
import com.res.jobjob.repository.RepoFactory
import com.res.jobjob.vm.register.RegisterFactory
import com.res.jobjob.vm.register.RegisterViewModel

class RegisterFragment : BaseFragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var adapter: ArrayAdapter<String>
    private var job: String = ""
    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this, RegisterFactory(RepoFactory()))[RegisterViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        Handlers().also { binding.handler = it }
        spinnerConfig()
        return binding.root
    }

    private fun spinnerConfig() {
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.jobsSpinner.adapter = adapter
        binding.jobsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                job = if (position == 0) ""
                else parent!!.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchJobs()
        viewModel.jobs.observe(viewLifecycleOwner, { adapter.addAll(it) })
        binding.registerButton.setOnClickListener { registrarUsuario() }
    }

    private fun registrarUsuario() {
        binding.apply {
            val name: String = nameEditText.text.toString()
            val email: String = mailEditText.text.toString()
            val password: String = passwordEditText.text.toString()
            val phone: String = phoneEditText.text.toString()
            if (email.checkString() && password.checkString()) {
                viewModel.registrarUsuario(email, password).observe(viewLifecycleOwner, { resources ->
                    when (resources) {
                        is Resource.Loading -> alertDialog.show()
                        is Resource.Success -> addUserDb(User(resources.data, name, email, job, phone))
                        is Resource.Error -> error(resources.errorMsg)
                    }
                })
            }
        }
    }

    private fun addUserDb(user: User) {
        viewModel.user = user
        viewModel.addUserDb().observe(viewLifecycleOwner, { resources ->
            when (resources) {
                is Resource.Loading -> Log.d("Hi", "Agregando usuario a la Db")
                is Resource.Success -> success(user)
                is Resource.Error -> error(resources.errorMsg)
            }
        })
    }
}