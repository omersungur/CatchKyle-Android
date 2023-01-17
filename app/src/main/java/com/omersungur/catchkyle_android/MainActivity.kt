package com.omersungur.catchkyle_android

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun start (view: View) {
        val intent : Intent = Intent(this,GameScreenActivity::class.java)
        startActivity(intent)
    }
}