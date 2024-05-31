package com.example.apicallsproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.apicallsproject.R
import com.example.apicallsproject.adapter.NewsAdapter
import com.example.apicallsproject.databinding.FragmentNewsBinding
import com.example.apicallsproject.databinding.NewsItemBinding


class NewsFragment : Fragment() {
private val viewModel: NewsViewModel by activityViewModels()

    private lateinit var binding: FragmentNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    binding = FragmentNewsBinding.inflate(layoutInflater)
    return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val helper: SnapHelper = PagerSnapHelper()
        helper.attachToRecyclerView(binding.rvNews)

        viewModel.articles.observe(viewLifecycleOwner){
            val adapter = NewsAdapter(it)
            binding.rvNews.adapter = adapter
        }

        viewModel.loadImages()
    }


}