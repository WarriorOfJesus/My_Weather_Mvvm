package com.example.myweather

import android.os.Bundle
import com.example.common.mvp.BaseActivity
import com.example.myweather.databinding.ActivityMainBinding
import com.example.myweather.ui.WeatherFragment

class RootActivity : BaseActivity() {
    private val  binding:ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val fragment = WeatherFragment()
        changeFragment(fragment, R.id.fragmentContainer)
    }
}