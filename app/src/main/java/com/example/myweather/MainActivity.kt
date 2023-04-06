package com.example.myweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myweather.common.mvp.BaseActivity
import com.example.myweather.ui.WeatherFragment
import com.example.myweather.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
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