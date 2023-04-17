package com.example.root

import android.os.Bundle
import com.example.common.mvp.BaseActivity
import com.example.myweather.R
import com.example.myweather.databinding.ActivityMainBinding
import com.example.myweather.ui.WeatherFragment
import com.example.utils.replace

class RootActivity : BaseActivity() {
    private val  binding:ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        replace(WeatherFragment())
    }
}