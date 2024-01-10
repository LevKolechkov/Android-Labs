package com.example.newactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnShow: Button = findViewById(R.id.btn_show_pic)

        btnShow.setOnClickListener {
            val intent = Intent(this, PicActivity::class.java)
            intent.putExtra("picLink", "https://webpulse.imgsmail.ru/imgpreview?mb=webpulse&key=pulse_cabinet-image-7991edc9-2a54-4521-b463-ef7133e5c7fa")
            startActivity(intent)
        }
    }
}