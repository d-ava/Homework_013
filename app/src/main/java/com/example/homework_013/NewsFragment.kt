package com.example.homework_013

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework_013.databinding.NewsFragmentBinding
import com.example.homework_013.network.Resource

class NewsFragment : BaseFragment<NewsFragmentBinding, NewsModel>(NewsFragmentBinding::inflate) {

    override fun getViewModel(): Class<NewsModel> = NewsModel::class.java

    override var useSharedViewModel = false

    lateinit var adapter: NewsAdapter


    override fun start() {
        setRecycler()
        setListeners()

    }


    private fun setRecycler() {
        adapter = NewsAdapter()
        val layoutManager = LinearLayoutManager(context)
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = layoutManager
    }

    private fun setListeners() {

        binding.swipe.setOnRefreshListener {
            viewModel.loadNews()
        }

        binding.btn.setOnClickListener {
            progressBarON()
            binding.swipe.isRefreshing = true
            viewModel.loadNews()

            viewModel.newsData.observe(viewLifecycleOwner, {
                when (it) {
                    is Resource.Success -> {
                        adapter.setData(it.data!!)
                        progressBarOFF()
                        binding.swipe.isRefreshing = false
                    }
                    is Resource.Error -> {
                        Toast.makeText(context, "${R.string.loadingError}", Toast.LENGTH_SHORT)
                            .show()
                        progressBarOFF()
                        binding.swipe.isRefreshing = false
                    }
                    is Resource.Loading -> {
                        binding.swipe.isRefreshing = it.loading
                    }

                }
            }
            )
        }

    }

    private fun progressBarON() {
        binding.progressOut.visibility = View.VISIBLE
        binding.progressIn.visibility = View.VISIBLE
    }

    private fun progressBarOFF() {
        binding.progressOut.visibility = View.INVISIBLE
        binding.progressIn.visibility = View.INVISIBLE

    }


}