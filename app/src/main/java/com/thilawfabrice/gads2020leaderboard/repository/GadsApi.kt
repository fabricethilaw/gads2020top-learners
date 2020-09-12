package com.thilawfabrice.gads2020leaderboard.repository

import com.thilawfabrice.gads2020leaderboard.models.LearnerModel
import com.thilawfabrice.gads2020leaderboard.models.SkillIQModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST


interface GadsApi {

    @GET("$LEADERS_BASE_URL/api/hours")
    suspend fun getLearningLearners(): Response<List<LearnerModel>>

    @GET("$LEADERS_BASE_URL/api/skilliq")
    suspend fun getTopSkillIQs(): Response<List<SkillIQModel>>


}

interface GoogleFromApi {
    @POST(GOOGLE_FORM_FULL_URL)
    suspend fun gadsSubmissionREST(
        @Field("entry.1824927963") email: String,
        @Field("entry.1877115667") firstName: String,
        @Field("entry.2006916086") lastName: String,
        @Field("entry.284483984") repositoryLink: String
    ): Response<Any>
}

const val LEADERS_BASE_URL = "https://gadsapi.herokuapp.com"
const val GOOGLE_FORM_FULL_URL =
    "https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse"
