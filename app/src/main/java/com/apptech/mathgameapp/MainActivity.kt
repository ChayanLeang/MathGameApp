package com.apptech.mathgameapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var addition:Button
    private lateinit var subtraction:Button
    private lateinit var multiplication:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addition=findViewById(R.id.addition)
        subtraction=findViewById(R.id.subtraction)
        multiplication=findViewById(R.id.multiplication)

        addition.setOnClickListener{
            val intent = Intent(this@MainActivity,GameActivity::class.java)
            intent.putExtra("calculationType","Addition")
            startActivity(intent)
        }

        subtraction.setOnClickListener{
            val intent = Intent(this@MainActivity,GameActivity::class.java)
            intent.putExtra("calculationType","Subtraction")
            startActivity(intent)
        }

        multiplication.setOnClickListener{
            val intent = Intent(this@MainActivity,GameActivity::class.java)
            intent.putExtra("calculationType","Multiplication")
            startActivity(intent)
        }
    }
}