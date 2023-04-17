package com.example.utils

import androidx.annotation.AnimRes
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.whenStateAtLeast
import com.example.myweather.R
import kotlinx.coroutines.launch

inline fun LifecycleOwner.whenStateAtLeast(state: Lifecycle.State, crossinline block: () -> Unit) {
    if (lifecycle.currentState.isAtLeast(state)) {
        block()
    } else {
        lifecycle.coroutineScope.launch {
            lifecycle.whenStateAtLeast(state) { block() }
        }
    }
}

fun Fragment.popBackStack() {
    requireActivity().popBackStack()
}

fun FragmentActivity.popBackStack() {

    if (supportFragmentManager.backStackEntryCount < 2) {
        finish()
    } else {
        supportFragmentManager.popBackStackImmediate()
    }
}

fun Fragment.add(
    target: Fragment,
    @IdRes containerId: Int = R.id.container,
    addToBackStack: Boolean = true,
    @AnimRes enter: Int = R.anim.nav_enter,
    @AnimRes exit: Int = R.anim.nav_exit,
    @AnimRes popEnter: Int = R.anim.nav_pop_enter,
    @AnimRes popExit: Int = R.anim.nav_pop_exit,
    fragmentManager: FragmentManager = requireActivity().supportFragmentManager
) = whenStateAtLeast(Lifecycle.State.STARTED) {
    fragmentManager.commit(allowStateLoss = true) {
        setCustomAnimations(enter, exit, popEnter, popExit)
        add(containerId, target, target.javaClass.name)
        if (addToBackStack) addToBackStack(target.javaClass.name)
    }
}

fun Fragment.replace(
    target: Fragment,
    @IdRes containerId: Int = R.id.container,
    addToBackStack: Boolean = true,
    @AnimRes enter: Int = R.anim.nav_enter,
    @AnimRes exit: Int = R.anim.nav_exit,
    @AnimRes popEnter: Int = R.anim.nav_pop_enter,
    @AnimRes popExit: Int = R.anim.nav_pop_exit,
    fragmentManager: FragmentManager = requireActivity().supportFragmentManager
) = whenStateAtLeast(Lifecycle.State.STARTED) {
    fragmentManager.commit(allowStateLoss = true) {
        setCustomAnimations(enter, exit, popEnter, popExit)
        replace(containerId, target, target.javaClass.name)
        if (addToBackStack) addToBackStack(target.javaClass.name)
    }
}

fun FragmentActivity.replace(
    target: Fragment,
    @IdRes containerId: Int = R.id.container,
    addToBackStack: Boolean = true
) = whenStateAtLeast(Lifecycle.State.STARTED) {
    supportFragmentManager.commit(allowStateLoss = true) {
        replace(containerId, target, target.javaClass.name)
        if (addToBackStack) addToBackStack(target.javaClass.name)
    }
}

fun FragmentActivity.add(
    target: Fragment,
    @IdRes containerId: Int = R.id.container,
    addToBackStack: Boolean = true,
    @AnimRes enter: Int = R.anim.nav_enter,
    @AnimRes exit: Int = R.anim.nav_exit,
    @AnimRes popEnter: Int = R.anim.nav_pop_enter,
    @AnimRes popExit: Int = R.anim.nav_pop_exit
) = whenStateAtLeast(Lifecycle.State.STARTED) {
    supportFragmentManager.commit(allowStateLoss = true) {
        setCustomAnimations(enter, exit, popEnter, popExit)
        add(containerId, target, target.javaClass.name)
        if (addToBackStack) addToBackStack(target.javaClass.name)
    }
}