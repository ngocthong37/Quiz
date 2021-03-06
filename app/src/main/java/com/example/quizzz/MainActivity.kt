package com.example.quizzz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_Start.setOnClickListener {
            if(edText.text!!.isEmpty()) {
                Toast.makeText(this, "Please enter your name",Toast.LENGTH_SHORT).show()
            }
            else {
                val intent = Intent(this, QuizQuestionActivity::class.java)
                intent.putExtra(Constants.USER_NAME, edText.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}