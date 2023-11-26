package com.bignerdranch.android.geoquiz

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.geoquiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true))

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.questionTextView.setOnClickListener {
            changeQuestion(Direction.PREV)
        }

        binding.trueButton.setOnClickListener { view: View ->
            checkQuestion(true)
        }

        binding.falseButton.setOnClickListener { view: View ->
            checkQuestion(false)
        }

        binding.prevButton.setOnClickListener {
            changeQuestion(Direction.PREV)
        }

        binding.nextButton.setOnClickListener {
            changeQuestion(Direction.NEXT)
        }

        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
    }

    private fun checkQuestion(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }

    private enum class Direction {
        PREV,
        NEXT
    }

    private fun changeQuestion(direction: Direction) {
        currentIndex = when(direction) {
            Direction.PREV -> if (currentIndex == 0) questionBank.size - 1 else (currentIndex - 1) % questionBank.size
            Direction.NEXT -> (currentIndex + 1) % questionBank.size
        }
        updateQuestion()
    }
}