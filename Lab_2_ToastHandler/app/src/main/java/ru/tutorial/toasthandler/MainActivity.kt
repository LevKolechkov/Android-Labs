package ru.tutorial.toasthandler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnOk = findViewById<Button>(R.id.button_ok)

        btnOk.setOnClickListener{
            Toast.makeText(this, "Кнопка ОК", Toast.LENGTH_SHORT).show()
        }
    }
}