package com.example.apicallsproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.apicallsproject.R
import com.example.apicallsproject.adapter.NewsAdapter
import com.example.apicallsproject.data.model.Article
import com.example.apicallsproject.databinding.FragmentFavoritesBinding


class FavoritesFragment : Fragment() {

    private val viewModel: NewsViewModel by activityViewModels()

    private lateinit var binding: FragmentFavoritesBinding
            override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemClickedCallback: (Article) -> Unit  = {
            viewModel.selectedArticleItem(it)

            findNavController().navigate(R.id.newsDetailFragment)
        }

        val adapter = NewsAdapter(viewModel.favorites.value!!, itemClickedCallback)
        binding.recyclerFavourites.adapter = adapter
        if (viewModel.favorites.value.isNullOrEmpty()){
                Toast.makeText(requireContext(), "There are no favorites yet!", Toast.LENGTH_SHORT).show()

        }

    }
}