package com.example.myweather.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
fun Fragment.changeFragment(fragment: Fragment, id: Int) {
    val fragmentManager = requireActivity().supportFragmentManager
    val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
    fragmentTransaction.addToBackStack(null)
        .replace(id, fragment)
        .commit()
}