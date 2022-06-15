package com.example.wildberriesweek71

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wildberriesweek71.databinding.FragmentDotaHeroesBinding

class DotaHeroesFragment : Fragment() {
    lateinit var binding: FragmentDotaHeroesBinding
    lateinit var dotaHeroesViewModel: DotaHeroesViewModel
    lateinit var dotaHeroItems: List<DotaHeroesJSON>
    private val adapter = DotaHeroesAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dotaHeroesViewModel = ViewModelProvider(requireActivity()).get(DotaHeroesViewModel::class.java)
        binding = FragmentDotaHeroesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dotaHeroesViewModel.request()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onStart() {
        super.onStart()
        dotaHeroesViewModel.itemsDotaHeroes.observe(activity as LifecycleOwner, Observer {
            dotaHeroItems = it
            adapter.getDotaHeroesData(dotaHeroItems)
            adapter.notifyDataSetChanged()
        })

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.rcSuperHeroes.layoutManager = LinearLayoutManager(context)
        binding.rcSuperHeroes.adapter = adapter
    }

    companion object {

        @JvmStatic
        fun newInstance() = DotaHeroesFragment()
    }
}