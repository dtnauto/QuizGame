package com.example.quizgame.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizgame.model.UserModel
//import com.example.quizgame.models.UserModel
import com.example.quizgame.repository.AuthenFirebaseRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AuthViewModel2 : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.IO + Job()
    private val repository = AuthenFirebaseRepo()
    private val userLiveData = MutableLiveData<UserModel>()


    fun observerUserLiveData(): LiveData<UserModel> {

        return userLiveData

    }


    fun signUp(email: String, passWord: String) {
        val user = UserModel(null, null)
        launch {
            repository.signUp(email, passWord, user)
            userLiveData.postValue(user)
        }

    }

    fun signIn(email: String, passWord: String) {
        val user = UserModel(null, null)
        launch {
            repository.signIn(email, passWord, user)
            Log.e("DEBUG", "user ${user.userID} ${user.userEmail}")
            if(user.userID != null && user.userEmail != null)
                userLiveData.postValue(user)
        }

    }

    fun signOut() {
        repository.signOut()
    }

}