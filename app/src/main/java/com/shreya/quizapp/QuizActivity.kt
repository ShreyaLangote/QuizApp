package com.shreya.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    // List of questions
    private val questions: List<Question> = listOf(
        Question("What is the capital of France?", listOf("Paris", "Berlin", "Madrid", "Rome"), 0),
        Question("Who wrote 'Hamlet'?", listOf("Shakespeare", "Homer", "Dante", "Tolstoy"), 0),
        Question("What is 2 + 2?", listOf("3", "4", "5", "6"), 1)
    )

    private var currentQuestionIndex: Int = 0
    private var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val questionText = findViewById<TextView>(R.id.questionText)
        val optionsGroup = findViewById<RadioGroup>(R.id.optionsGroup)
        val nextQuestionButton = findViewById<Button>(R.id.nextQuestionButton)

        loadQuestion(questionText, optionsGroup)

        nextQuestionButton.setOnClickListener {
            val selectedOption = optionsGroup.checkedRadioButtonId
            if (selectedOption != -1) {
                val selectedAnswerIndex = optionsGroup.indexOfChild(findViewById(selectedOption))
                if (selectedAnswerIndex == questions[currentQuestionIndex].correctAnswerIndex) {
                    score++
                }

                currentQuestionIndex++
                if (currentQuestionIndex < questions.size) {
                    loadQuestion(questionText, optionsGroup)
                } else {
                    // Navigate to ResultActivity and pass the score
                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra("SCORE", score)
                    startActivity(intent)
                    finish()
                }
            } else {
                Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadQuestion(questionText: TextView, optionsGroup: RadioGroup) {
        val question = questions[currentQuestionIndex]
        questionText.text = question.text
        optionsGroup.removeAllViews()
        question.options.forEachIndexed { index, option ->
            val radioButton = RadioButton(this)
            radioButton.text = option
            radioButton.id = index
            optionsGroup.addView(radioButton)
        }
    }
}
