package com.test.movieapplication.utils.help

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.test.movieapplication.R

fun FragmentManager.changeFragment(fragment: Fragment) {
    this.beginTransaction().replace(R.id.main_activity, fragment).commit()
}

fun Activity.recreateSmoothly() {
    startActivity(Intent(this, this::class.java))
    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    finish()
}