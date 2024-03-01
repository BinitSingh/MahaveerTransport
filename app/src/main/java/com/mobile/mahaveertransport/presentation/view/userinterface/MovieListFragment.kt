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
import com.mobile.mahaveertransport.databinding.FragmentMovieListBinding
import com.mobile.mahaveertransport.presentation.view.adapter.MovieClickListener
import com.mobile.mahaveertransport.presentation.view.adapter.MovieListAdaptor
import com.mobile.mahaveertransport.presentation.viewmodel.MovieListViewModel
import com.mobile.mahaveertransport.presentation.ViewState
import com.mobile.mahaveertransport.utility.Constants.MOVIE
import com.mobile.mahaveertransport.utility.ItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MovieListFragment : BaseFragment() {
    private lateinit var binding: FragmentMovieListBinding
    private val viewModel: MovieListViewModel by viewModels()
    @Inject
    lateinit var movieAdaptor: MovieListAdaptor

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchMovieList()
        with(binding){
            rvMovie.addItemDecoration(
                ItemDecoration(resources.getDimension(R.dimen.dimen_16dp).toInt())
            )
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.getViewStateFlow().collect{ viewState ->
                        when (viewState) {
                            is ViewState.Loading ->
                                showHideProgressBar(progressBar, viewState.isLoading)

                            is ViewState.Failure -> {
                                viewState.throwable.message?.let {
                                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                                }
                            }
                            is ViewState.Success -> {
                                if(this@MovieListFragment::movieAdaptor.isInitialized) {
                                    with(movieAdaptor) {
                                        rvMovie.adapter = this
                                        listner = itemClickListener
                                        dataSet = viewState.result
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private val itemClickListener: MovieClickListener = { movie ->
        findNavController().navigate(
            R.id.action_show_moviedetail,
            Bundle().apply {
                putParcelable(MOVIE, movie)
            }
        )
    }

}