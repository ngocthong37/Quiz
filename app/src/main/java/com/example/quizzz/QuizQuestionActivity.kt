
package com.example.quizzz

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_question.*

class QuizQuestionActivity : AppCompatActivity(),  View.OnClickListener {
    private var mCurrentPosition: Int = 1
    private var mQuestionList:ArrayList<Question>? = null
    private var mSelectedOpinionPosition = 0
    private var mCorrectAnswers: Int = 0
    private var mUserName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)
        mQuestionList = Constants.getQuestions()
        mUserName = intent.getStringExtra(Constants.USER_NAME)
        setQuestion()
        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btn_submit?.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    private fun setQuestion() {
        defaultOptionView()
        val question: Question = mQuestionList!![mCurrentPosition - 1]
        progressBar?.progress = mCurrentPosition
        tvProgress?.text = "$mCurrentPosition / ${progressBar?.max}"
        tvQuestion.text = question.questions
        tvImage.setImageResource(question.image)
        tvOptionOne.text = question.optionOne
        tvOptionTwo.text = question.optionTwo
        tvOptionThree.text = question.optionThree
        tvOptionFour.text = question.optionFour
        if (mCurrentPosition > mQuestionList!!.size) {
            btn_submit.text = "FINISH"
        }
        else {
            btn_submit.text = "SUBMIT"
        }
    }
    private fun defaultOptionView() {
        val options = ArrayList<TextView>()
        tvOptionOne?.let {
            options.add(0, it)
        }
        tvOptionTwo?.let {
            options.add(1, it)
        }
        tvOptionThree?.let {
            options.add(2, it)
        }
        tvOptionFour?.let {
            options.add(3, it)
        }
        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.defaul_option_border
            )
        }
    }
    private fun selectedDefaultOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionView()
        mSelectedOpinionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_default_option_border
        )
    }
    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.tvOptionOne -> {
                tvOptionOne?.let {
                    selectedDefaultOptionView(it, 1)
                }
            }
            R.id.tvOptionTwo -> {
                tvOptionTwo?.let {
                    selectedDefaultOptionView(it, 2)
                }
            }
            R.id.tvOptionThree -> {
                tvOptionThree?.let {
                    selectedDefaultOptionView(it, 3)
                }
            }
            R.id.tvOptionFour -> {
                tvOptionFour?.let {
                    selectedDefaultOptionView(it, 4)
                }
            }
            R.id.btn_submit -> {
                if (mSelectedOpinionPosition == 0) {
                    mCurrentPosition++
                    when {
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this, Result::class.java)
                            intent.putExtra(Constants.CORRECT_ANSWER, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTION, mQuestionList?.size)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
                else {
                    val question = mQuestionList?.get(mCurrentPosition - 1)
                    if (question!!.correctAnswer != mSelectedOpinionPosition) {
                        answerView(mSelectedOpinionPosition, R.drawable.wrong_answer)
                    }
                    else {
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_answer)
                    if (mCurrentPosition == mQuestionList!!.size) {
                        btn_submit.text = "GET THE RESULT"
                    }
                    else {
                        btn_submit.text = "GO TO THE NEXT QUESTION"
                    }
                    mSelectedOpinionPosition = 0
                }
                }
            }
        }
        private fun answerView(answer: Int, drawableView: Int) {
            when(answer) {
                1->{
                    tvOptionOne?.background = ContextCompat.getDrawable(
                        this,drawableView
                    )
                }
                2->{
                    tvOptionTwo?.background = ContextCompat.getDrawable(
                        this@QuizQuestionActivity,drawableView
                    )
                }
                3->{
                    tvOptionThree?.background = ContextCompat.getDrawable(
                        this@QuizQuestionActivity,drawableView
                    )
                }
                4->{
                    tvOptionFour?.background = ContextCompat.getDrawable(
                        this@QuizQuestionActivity,drawableView
                    )
                }
            }
        }
    }