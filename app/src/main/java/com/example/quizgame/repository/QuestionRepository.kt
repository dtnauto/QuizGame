package com.example.quizgame.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase

class QuestionRepository {
    var database = Firebase.database
    var questionsDatabase = database.getReference("questions")
//    var scoresDatabase = database.getReference("scores")

    var currentListQuestions = MutableLiveData<ArrayList<Question?>>()

    lateinit var listQuestions: ArrayList<Question?>

    fun getListQuestions(){

        Log.e("mycodeisblocking", "7777777777777777777")
        questionsDatabase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.e("mycodeisblocking", "6666666666666")
                for (question in snapshot.children) {
                    listQuestions.add(question.getValue(Question::class.java))
                }
                currentListQuestions.postValue(listQuestions)
                Log.e("mycodeisblocking", "111111111111111")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("mycodeisblocking", "loadPost:onCancelled", error.toException())
            }
        })
    }
}