package com.example.newactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.net.toUri
import com.bumptech.glide.Glide

class PicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pic_layout)
        this.title = "Картинка"

        val imView: ImageView = findViewById(R.id.picView)
        Glide.with(this).load(intent.getStringExtra("picLink")).into(imView)
    }
}