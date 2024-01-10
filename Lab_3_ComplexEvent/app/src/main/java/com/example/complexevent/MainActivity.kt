package com.example.complexevent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var MyButton: Button = findViewById(R.id.button);
        var MyEditText: EditText = findViewById(R.id.editText);
        var MyCheckBox: CheckBox = findViewById(R.id.checkBox);
        var MyTextView: TextView = findViewById(R.id.textView);
        var MyProgressBar: ProgressBar = findViewById(R.id.progressBar);
        MyButton.setOnClickListener {
            if (MyCheckBox.isChecked) {
                MyProgressBar.setProgress(MyProgressBar.getProgress() + 10, true);
                MyTextView.text = MyEditText.text;
            }
        }
    }
}