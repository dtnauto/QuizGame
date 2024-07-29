package com.example.quizgame.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData

class QuestionViewModel {

    var repository = QuestionRepository(application)

    fun observerCurrentListQuestions(): LiveData<ArrayList<Question?>> {

        repository.getListQuestions() // bat dau lay data
        Log.e("mycodeisblocking", "333333333333333333")
        return repository.currentListQuestions
    }
}