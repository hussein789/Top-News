package com.hussien.topnews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.hussien.topnews.databinding.FragmentNewsInfoBinding
import com.hussien.topnews.presentation.adapter.NewsAdapter
import com.hussien.topnews.presentation.viewModel.NewsViewModel
import com.hussien.topnews.presentation.viewModel.NewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsInfoFragment : Fragment() {

    private lateinit var binding:FragmentNewsInfoBinding
    private val args:NewsInfoFragmentArgs by navArgs()

    @Inject lateinit var factory: NewsViewModelFactory
    private val viewModel:NewsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewsInfoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.wvNews.apply {
            val webViewClient = WebViewClient()
            if(args.selectedArticle?.url?.isNotEmpty() == true){
                loadUrl(args?.selectedArticle?.url ?: "")
            }
        }

        observeViewModel()

        binding.saveArticle.setOnClickListener {
            viewModel.saveNews(args.selectedArticle)
        }
    }

    private fun observeViewModel() {
        viewModel.snackbarUiState.observe(viewLifecycleOwner){stringRes ->
            stringRes?.let { showSnack(it) }
        }
    }

    private fun showSnack(stringRes: Int) {
        Snackbar.make(requireView(),getString(stringRes),Snackbar.LENGTH_SHORT).show()
    }


}