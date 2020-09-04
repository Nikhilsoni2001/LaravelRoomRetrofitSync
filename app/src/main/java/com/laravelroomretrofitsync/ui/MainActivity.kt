package com.laravelroomretrofitsync.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.laravelroomretrofitsync.R
import com.laravelroomretrofitsync.ui.fragments.Main

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, Main()).commit()

    }
}