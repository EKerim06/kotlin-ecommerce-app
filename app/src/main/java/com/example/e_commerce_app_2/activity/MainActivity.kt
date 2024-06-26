package com.example.e_commerce_app_2.activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.e_commerce_app_2.Adapter.SliderAdapter
import com.example.e_commerce_app_2.Model.SliderModel
import com.example.e_commerce_app_2.R
import com.example.e_commerce_app_2.Viewmodel.MainViewModel
import com.example.e_commerce_app_2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {


    private val viewModel = MainViewModel()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBanner()


    }

    private fun initBanner() {

        binding.progressBarBanner.visibility = View.VISIBLE

        viewModel.banners.observe(this, Observer { items ->
            banners(items)
            binding.progressBarBanner.visibility = View.GONE
        })
        viewModel.loadBanners()

    }

    private fun banners(images: List<SliderModel>) {

        binding.viewPagerSlider.adapter = SliderAdapter(images, binding.viewPagerSlider)

        binding.viewPagerSlider.clipToPadding = false
        binding.viewPagerSlider.clipChildren = false
        binding.viewPagerSlider.offscreenPageLimit = 3

        binding.viewPagerSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }

        binding.viewPagerSlider.setPageTransformer(compositePageTransformer)

        if(images.size >1){
            binding.dotsIndicator.visibility = View.VISIBLE

            binding.dotsIndicator.attachTo(binding.viewPagerSlider)

        }


    }

}