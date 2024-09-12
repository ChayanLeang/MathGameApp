package com.apptech.mathgameapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    private lateinit var textScore : TextView
    private lateinit var btnPlayAgain : Button
    private lateinit var btnExit : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        textScore=findViewById(R.id.textScore)
        btnPlayAgain=findViewById(R.id.btnPlayAgain)
        btnExit=findViewById(R.id.btnExit)
        val score = intent.getIntExtra("score",0)
        textScore.text = "Your Score : " + score.toString()

        btnPlayAgain.setOnClickListener{
            val intent = Intent(this@ResultActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnExit.setOnClickListener{
            val intent=Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}