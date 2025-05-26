package com.example.quizgame.repository

interface AuthenRepo<T> {

    suspend fun signUp(email: String, password: String, user: T)
    suspend fun signIn(email: String, password: String, user: T)
    fun signOut()
    fun getCurrentUser(user: T)

}