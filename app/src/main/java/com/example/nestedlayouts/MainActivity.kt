package com.example.nestedlayouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var myButton: Button = findViewById(R.id.button);
        var text1: TextView = findViewById(R.id.textView);
        var text2: TextView = findViewById(R.id.textView2);
        var text3: TextView = findViewById(R.id.textView3);
        var text4: TextView = findViewById(R.id.textView4);
        var text5: TextView = findViewById(R.id.textView5);
        var text6: TextView = findViewById(R.id.textView6);
        var text10: TextView = findViewById(R.id.textView10);
        var text11: TextView = findViewById(R.id.textView11);
        var text12: TextView = findViewById(R.id.textView12);
        var digit = 1;
        myButton.setOnClickListener {
            digit += 1;
            text1.text = "";
            text2.text = "";
            text3.text = "";
            text4.text = "";
            text5.text = "";
            text6.text = "";
            text10.text = "";
            text11.text = "";
            text12.text = "";
            if (digit % 3 == 1) {
                text1.text = digit.toString();
                text4.text = digit.toString();
                text10.text = digit.toString();
            }
            if (digit % 3 == 2) {
                text2.text = digit.toString();
                text5.text = digit.toString();
                text11.text = digit.toString();
            }
            if (digit % 3 == 0) {
                text3.text = digit.toString();
                text6.text = digit.toString();
                text12.text = digit.toString();
            }
        }
    }
}