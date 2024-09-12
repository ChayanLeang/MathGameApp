package com.apptech.mathgameapp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import java.util.Locale
import java.util.Objects
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    private lateinit var gameLayout : LinearLayout
    private lateinit var textScore : TextView
    private lateinit var textLife : TextView
    private lateinit var textTime : TextView
    private lateinit var textQuestion : TextView
    private lateinit var editTextInput : EditText
    private lateinit var btnOk : Button
    private lateinit var btnNext : Button
    private lateinit var calculationType : String
    private lateinit var timer : CountDownTimer
    private var startTimeInMill : Long = 60000
    private var timeLeftInMill : Long = startTimeInMill
    private var correctAnswer = 0
    private var score = 0
    private var life = 3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        calculationType = intent.getStringExtra("calculationType").toString()
        supportActionBar!!.title=calculationType
        gameLayout=findViewById(R.id.gameLayout)
        textScore=findViewById(R.id.textScore)
        textLife=findViewById(R.id.textLife)
        textTime=findViewById(R.id.textTime)
        textQuestion=findViewById(R.id.textQuestion)
        editTextInput=findViewById(R.id.editTextInput)
        btnOk=findViewById(R.id.btnOk)
        btnNext=findViewById(R.id.btnNext)
        gameContinue()
        btnOk.setOnClickListener{
            val input=editTextInput.text.toString()
            if(input.isEmpty()){
                Snackbar.make(gameLayout,"Please write your answer",Snackbar.LENGTH_SHORT)
                                    .setAction("Close",View.OnClickListener {  }).show()
            }
            else{
                if(Objects.equals(input.toInt(),correctAnswer)){
                    textQuestion.text = "Congratulation..."
                    score += 10
                }
                else{
                    textQuestion.text = "Sorry :("
                    life--
                    score-=10
                    if(score<0) score=0
                }
                pauseTimer()
                updateTextTime()
                textScore.text = score.toString()
                textLife.text=life.toString()
                editTextInput.setText("")
                btnOk.visibility=View.GONE
            }
        }

        btnNext.setOnClickListener{
            pauseTimer()
            resetTimer()
            editTextInput.setText("")
            if(Objects.equals(life,0)){
                Toast.makeText(this@GameActivity,"Game Over",Toast.LENGTH_SHORT).show()
                val intent=Intent(this@GameActivity,ResultActivity::class.java)
                intent.putExtra("score",score)
                startActivity(intent)
                finish()
            }
            else{
                btnOk.visibility=View.VISIBLE
                gameContinue()
            }
        }
    }

    fun gameContinue(){
        startTimer()
        val number1 = Random.nextInt(0,100)
        val number2 = Random.nextInt(0,100)
        if(calculationType.equals("Addition",true)){
            textQuestion.text = "$number1 + $number2"
            correctAnswer=number1 + number2
        }
        if(calculationType.equals("Subtraction",true)){
            textQuestion.text = "$number1 - $number2"
            correctAnswer=number1 - number2
        }
        if(calculationType.equals("Multiplication",true)){
            textQuestion.text = "$number1 x $number2"
            correctAnswer=number1 * number2
        }
    }

    fun startTimer(){
        timer = object : CountDownTimer(startTimeInMill,1000){
            override fun onTick(milliUntilFinish: Long) {
                timeLeftInMill=milliUntilFinish
                updateTextTime()
            }

            override fun onFinish() {
                pauseTimer()
                resetTimer()
                updateTextTime()
                life--
                textLife.text=life.toString()
                textQuestion.text="Sorry, Time is up"
                btnOk.visibility=View.GONE
            }
        }.start()
    }

    fun updateTextTime(){
        val remainingTime : Int = (timeLeftInMill/1000).toInt()
        textTime.text=String.format(Locale.getDefault(),"%02d",remainingTime)
    }

    fun pauseTimer(){
        timer.cancel()
    }

    fun resetTimer(){
        timeLeftInMill=startTimeInMill
        updateTextTime()
    }
}