package com.example.securityrtcs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageView

class LaunchActivity : AppCompatActivity() {
    lateinit var image: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        image = findViewById(R.id.ic_image)

        val timer = object : CountDownTimer(3000, 1000)
        {
            override fun onTick(p0: Long)
            {
                image.animate().apply {
                    duration = 1000
                    rotationYBy(-360f)
                }.start()
            }
            override fun onFinish() {
                val intent = Intent(this@LaunchActivity, LoginActivity :: class.java)
                startActivity(intent)
            }
        }
        timer.start()
    }
}