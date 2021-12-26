package com.example.quizzz

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class Result : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val correctAnswer = intent.getIntExtra(Constants.CORRECT_ANSWER, 0)
        val totalAnswer = intent.getIntExtra(Constants.TOTAL_QUESTION, 0)
        tvName.text = intent.getStringExtra(Constants.USER_NAME)
        tv_Result.text = "Your score is $correctAnswer out of $totalAnswer"
        btn_Finish.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}