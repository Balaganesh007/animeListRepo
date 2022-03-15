package com.example.animelistrepo.ui


import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.animelistrepo.R
import com.example.animelistrepo.adapter.AnimeAdapter
import com.example.animelistrepo.databinding.DisplayAllFragmentBinding
import com.example.animelistrepo.domain.DataModel

class DisplayAllFragment : Fragment() {

    private lateinit var binding: DisplayAllFragmentBinding
    private lateinit var viewModel: DisplayAllViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.display_all_fragment,
            container,
            false
        )

        val application = requireNotNull(this.activity).application
        val viewModelFactory = DisplayAllViewModelFactory(application)
        viewModel = ViewModelProvider(this,viewModelFactory).get(DisplayAllViewModel::class.java)
        binding.displayAllViewModel = viewModel
        binding.lifecycleOwner = this
        val adapter = AnimeAdapter()

        binding.animeList.adapter = adapter
        viewModel.relist.observe(viewLifecycleOwner, Observer{
            it?.let {
                Log.v("bala", it.toString())
                adapter.submitList(it)
            }
        })


        return binding.root
    }

}