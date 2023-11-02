package com.example.hw_1_4.ui.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.hw_1_4.databinding.FragmentOnBoardingBinding
import com.example.hw_1_4.ui.data.local.Pref

class OnBoardingFragment : Fragment() {
    private lateinit var binding:FragmentOnBoardingBinding
    private val adapter = OnBoardingAdapter(this::onClick)
    private val pref by lazy {
        Pref(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentOnBoardingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager.adapter = adapter
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.indicator.setViewPager(binding.viewPager)
    }

    private fun onClick(){
        pref.onShowed()
        findNavController().navigateUp()
    }
}