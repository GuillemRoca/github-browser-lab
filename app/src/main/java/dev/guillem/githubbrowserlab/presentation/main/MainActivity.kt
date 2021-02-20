package dev.guillem.githubbrowserlab.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.guillem.githubbrowserlab.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}