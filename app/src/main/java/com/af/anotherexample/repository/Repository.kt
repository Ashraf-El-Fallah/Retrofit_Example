package com.af.anotherexample.repository

import com.af.anotherexample.api.RetrofitInstance2
import com.af.anotherexample.model.Post
import retrofit2.Response

class Repository {
    suspend fun getPost(): Response<Post> {
        return RetrofitInstance2.api.getPost()
    }
}