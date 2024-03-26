package com.mobile.mahaveertransport.presentation.view.userinterface

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.mobile.mahaveertransport.R
import com.mobile.mahaveertransport.databinding.FragmentLoginScreenBinding
import com.mobile.mahaveertransport.presentation.ViewState
import com.mobile.mahaveertransport.presentation.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment: BaseFragment() {

    private lateinit var binding: FragmentLoginScreenBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginScreenBinding.inflate(inflater, container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            btnLogin.setOnClickListener {
                viewModel.doAuthentication(
                    editUsername.text.toString(),
                    edtPassword.text.toString())
            }
            btnRegister.setOnClickListener {
                findNavController().navigate(
                    R.id.action_loginFragment_to_registrationFragment
                )
            }

            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED){
                    viewModel.getViewStateFlow().collect { viewState ->
                        when(viewState){
                            is ViewState.Loading -> {

                            }
                            is ViewState.Failure -> {
                               Toast.makeText(
                                   context,viewState.throwable.message,Toast.LENGTH_LONG
                               ).show()
                            }
                            is ViewState.Success -> {
                                viewState.result
                            }

                        }
                    }
                }
            }

        }
    }

}