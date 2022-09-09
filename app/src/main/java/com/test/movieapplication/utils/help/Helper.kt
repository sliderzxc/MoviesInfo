package com.test.movieapplication.utils.help

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.test.movieapplication.R

fun FragmentManager.changeFragment(fragment: Fragment) {
    this.beginTransaction().replace(R.id.main_activity, fragment).commit()
}