package com.af.anotherexample.repository

import com.af.anotherexample.api.RetrofitInstance2
import com.af.anotherexample.model.Post
import retrofit2.Response

class Repository {
    suspend fun getPost(): Response<Post> {
        return RetrofitInstance2.api.getPost()
    }

    suspend fun getPost2(number: Int):Response<Post>{
        return RetrofitInstance2.api.getPost2(number)
    }

    suspend fun getCustomPosts(userId: Int):Response<List<Post>>{
        return RetrofitInstance2.api.getCustomPost(userId)
    }
}