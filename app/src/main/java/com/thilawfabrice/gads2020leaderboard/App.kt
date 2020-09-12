package com.thilawfabrice.gads2020leaderboard

import android.app.Application
import com.thilawfabrice.gads2020leaderboard.repository.GOOGLE_FORM_FULL_URL
import com.thilawfabrice.gads2020leaderboard.repository.GadsApi
import com.thilawfabrice.gads2020leaderboard.repository.GoogleFromApi
import com.thilawfabrice.gads2020leaderboard.repository.LEADERS_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {
    companion object {
        private val retrofitLearningClient by lazy {
            Retrofit.Builder()
                .baseUrl(LEADERS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        private val retrofitGoogleFormClient by lazy {
            Retrofit.Builder()
                .baseUrl(GOOGLE_FORM_FULL_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val gadsLearningApi: GadsApi by lazy {
            retrofitLearningClient.create(GadsApi::class.java)
        }

        val repositorySubmissionApi: GoogleFromApi by lazy {
            retrofitGoogleFormClient.create(GoogleFromApi::class.java)
        }
    }
}

