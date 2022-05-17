package com.hussien.topnews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.hussien.topnews.data.model.Article
import com.hussien.topnews.databinding.FragmentSavedNewsBinding
import com.hussien.topnews.presentation.adapter.NewsAdapter
import com.hussien.topnews.presentation.viewModel.NewsViewModel
import com.hussien.topnews.presentation.viewModel.NewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SavedNewsFragment : Fragment() {

    lateinit var binding:FragmentSavedNewsBinding

    @Inject lateinit var factory:NewsViewModelFactory

    private val viewModel:NewsViewModel by activityViewModels { factory }
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedNewsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.savedNews.observe(viewLifecycleOwner) { news ->
            news?.let { handleNews(news) }
        }
    }

    private fun initRecycler() {
        newsAdapter = NewsAdapter {
            findNavController().navigate(
                SavedNewsFragmentDirections.actionSavedNewsFragmentToNewsInfoFragment(
                    it
                )
            )
        }
        binding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        val callback = object: ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                val article = newsAdapter.differ.currentList[pos]
                viewModel.deleteNews(article)
                Snackbar.make(requireView(),"Delete successfully",Snackbar.LENGTH_LONG)
                    .apply {
                        setAction("Undo"){
                            viewModel.saveNews(article)
                        }
                    }
                    .show()
            }
        }

        ItemTouchHelper(callback).apply {
            attachToRecyclerView(binding.rvNews)
        }
    }

    private fun handleNews(news: List<Article>) {
        newsAdapter.differ.submitList(news)
    }

}