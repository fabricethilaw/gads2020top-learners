package com.thilawfabrice.gads2020leaderboard.repository

import com.thilawfabrice.gads2020leaderboard.models.LearnerModel
import com.thilawfabrice.gads2020leaderboard.models.SkillIQModel
import retrofit2.Response
import retrofit2.http.GET


interface GadsApi {

    @GET("$LEADERS_BASE_URL/api/hours")
    suspend fun getLearningLearners(): Response<List<LearnerModel>>

    @GET("$LEADERS_BASE_URL/api/skilliq")
    suspend fun getTopSkillIQs(): Response<List<SkillIQModel>>

}

const val LEADERS_BASE_URL = "https://gadsapi.herokuapp.com"
