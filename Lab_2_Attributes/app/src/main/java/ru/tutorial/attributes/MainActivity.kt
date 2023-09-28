package ru.tutorial.attributes

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textInput = findViewById<TextInputEditText>(R.id.textInputEditText)

        val buttonBlackText = findViewById<Button>(R.id.button_black_text)
        val buttonRedText = findViewById<Button>(R.id.button_red_text)
        val buttonSize8SP = findViewById<Button>(R.id.button_size_8sp)
        val buttonSize24SP = findViewById<Button>(R.id.button_size_24sp)
        val buttonBackgroundWhite = findViewById<Button>(R.id.button_background_white)
        val buttonBackgroundYellow = findViewById<Button>(R.id.button_background_yellow)

        buttonBlackText.setOnClickListener{
            textInput.setTextColor(Color.BLACK)
        }

        buttonRedText.setOnClickListener{
            textInput.setTextColor(Color.RED)
        }

        buttonSize24SP.setOnClickListener{
            textInput.textSize = 24F
        }

        buttonSize8SP.setOnClickListener{
            textInput.textSize = 8F
        }

        buttonBackgroundWhite.setOnClickListener{
            textInput.setBackgroundColor(Color.WHITE)
        }

        buttonBackgroundYellow.setOnClickListener{
            textInput.setBackgroundColor(Color.YELLOW)
        }
    }
}