package com.shreya.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.shreya.quizapp.QuizActivity



class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val score = intent.getIntExtra("SCORE", 0)
        val scoreTextView = findViewById<TextView>(R.id.textViewScore)
        val restartButton = findViewById<Button>(R.id.buttonRestartQuiz)
        val exitButton = findViewById<Button>(R.id.buttonExit)

        scoreTextView.text = "Your Score: $score"

        restartButton.setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java))
            finish()
        }

        exitButton.setOnClickListener {
            finish()
        }
    }
}
