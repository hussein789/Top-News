package com.hussien.topnews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hussien.topnews.data.model.APIResponse
import com.hussien.topnews.data.model.Article
import com.hussien.topnews.databinding.FragmentTopNewsBinding
import com.hussien.topnews.presentation.adapter.NewsAdapter
import com.hussien.topnews.presentation.viewModel.NewsViewModel
import com.hussien.topnews.presentation.viewModel.NewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TopNewsFragment : Fragment() {

    var isScrolling = false

    @Inject
    lateinit var factory: NewsViewModelFactory
    val viewModel: NewsViewModel by activityViewModels { factory }

    private lateinit var newsAdapter: NewsAdapter
    private lateinit var binding: FragmentTopNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentTopNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initSearchView()
        observeViewModel()

        viewModel.resetData()
        viewModel.getTopHeadlines()
    }

    private fun initSearchView() {
        binding.svNews.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchTopHeadlines(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchTopHeadlines(newText.toString())
                return true
            }
        })

        binding.svNews.setOnCloseListener {
            viewModel.getTopHeadlines()
            true
        }
    }

    private fun observeViewModel() {
        viewModel.loadingUiState.observe(viewLifecycleOwner) { show ->
            show?.let { handleLoading(show) }
        }

        viewModel.topHeadlinesUiState.observe(viewLifecycleOwner) { news ->
            news?.let { updateNewsList(it) }
        }
    }

    private fun updateNewsList(it: List<Article>) {
        newsAdapter.differ.submitList(it)
    }

    private fun handleLoading(show: Boolean) {
        binding.progress.isVisible = show
    }

    private fun initRecycler() {
        newsAdapter = NewsAdapter {
            findNavController().navigate(
                TopNewsFragmentDirections.actionTopNewsFragmentToNewsInfoFragment(
                    it
                )
            )
        }
        binding.newsRv.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

//        binding.newsRv.addOnScrollListener(object:RecyclerView.OnScrollListener(){
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                if(newState == RecyclerView.SCROLL_STATE_DRAGGING)
//                    isScrolling = true
//            }
//
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                val layoutManager = (recyclerView.layoutManager as LinearLayoutManager)
//                val lastVisibleItemPos = layoutManager.findLastVisibleItemPosition()
//                val isLastItemVisible = lastVisibleItemPos == newsAdapter.differ.currentList.size - 1
//                if(isScrolling && !viewModel.isLastPage() && isLastItemVisible) {
//                    viewModel.loadMore()
//                    isScrolling = false
//                }
//            }
//        })
    }

}