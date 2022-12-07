package com.example.quiz

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"

class QuizViewModel ( private val savedStateHandle: SavedStateHandle): ViewModel() {

    private var bancoPreguntas = listOf(
        pregunta(R.string.pregunta_chocolate, false),
        pregunta(R.string.pregunta_clima, false),
        pregunta(R.string.pregunta_carrera, false),
    )

    private var indice : Int
        get() = savedStateHandle.get(CURRENT_INDEX_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)


    val currentQuestionAnswer: Boolean
        get() = bancoPreguntas[indice].respuesta
    val currentQuestionText: Int
        get() = bancoPreguntas[indice].textoPregunta
    fun moveToNext() {
        indice = (indice + 1) % bancoPreguntas.size

    }
}