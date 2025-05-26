package com.example.quizgame.repository

import android.util.Log
import com.example.quizgame.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthenFirebaseRepo: AuthenRepo<UserModel> {

    private var auth = FirebaseAuth.getInstance()

    init {
        auth.currentUser?.reload()
            Log.d("DEBUG", "AuthenticationRepository")
    }

    override suspend fun signUp(email: String, password: String, user: UserModel) {
        try {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    if(task.isSuccessful) {

                        getCurrentUser(user)

                    } else {

                        Log.e("DEBUG", "SignUp Failed")

                        user.userID = null
                        user.userEmail = null

                    }

                }.await()
        } catch (e: Exception) {

        }
    }

    override suspend fun signIn(email: String, password: String, user: UserModel) {

        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        getCurrentUser(user)
                    } else {
                        user.userID = null
                        user.userEmail = null
                        Log.e("DEBUG", "SignIn Failed")
                    }
                }.await()

            Log.e("DEBUG", "await")
        }
        catch (e: Exception) {
            Log.e("DEBUG", "signIn exception: $e")
        }
    }

    override fun signOut() {
        auth.signOut()
    }

    override fun getCurrentUser(user: UserModel) {
        if(auth.currentUser != null) {
            user.userID = auth.currentUser?.uid
            user.userEmail = auth.currentUser?.email
        }
    }
}