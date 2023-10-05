package ru.tutorial.logging

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<TextInputEditText>(R.id.textInputEditText)
        val button_log = findViewById<Button>(R.id.button_log)
        val button_timber = findViewById<Button>(R.id.button_timber)


        button_log.setOnClickListener {
            val inputText = editText.text.toString()

            Log.v("From EditText", inputText)
        }

        button_timber.setOnClickListener{
            Timber.plant(Timber.DebugTree())
            Timber.v(editText.text.toString())
        }

    }
}