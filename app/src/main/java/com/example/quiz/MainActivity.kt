package com.example.quiz

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import com.example.quiz.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val quizViewModel: QuizViewModel by viewModels()


        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            Log.d(TAG, "onCreate(Bundle?) called")
         binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            Log.d(TAG, "Got a QuizViewModel: $quizViewModel")

        binding.trueButton.setOnClickListener { view: View ->
            checkAnswer(true, view)
        }


        binding.falseButton.setOnClickListener { view: View ->
            checkAnswer(false, view)
        }
            binding.nextButton.setOnClickListener {
                quizViewModel.moveToNext()
                actualizarPregunta()
            }

            actualizarPregunta()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun actualizarPregunta() {
        val questionTextResId = quizViewModel.currentQuestionText
        binding.text.setText(questionTextResId)
    }
    private fun checkAnswer(userAnswer: Boolean, view:View) {
        val respuestaCorrecta = quizViewModel.currentQuestionAnswer

        val messageResId = if (userAnswer == respuestaCorrecta) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        val colorBackground = if(userAnswer == respuestaCorrecta){
            R.color.verde
        }else{
            R.color.rojo
        }
        val mySnack = Snackbar.make(view,messageResId,Snackbar.LENGTH_LONG)
        mySnack.setBackgroundTint(resources.getColor(R.color.naranja))
        mySnack.show()

        //Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
         //   .show()
    }
}