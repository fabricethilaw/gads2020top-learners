package com.thilawfabrice.gads2020leaderboard

import android.app.Application
import com.thilawfabrice.gads2020leaderboard.repository.GadsApi
import com.thilawfabrice.gads2020leaderboard.repository.LEADERS_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {
    companion object {
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(LEADERS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val gadsApi by lazy {
            retrofit.create(GadsApi::class.java)
        }
    }
}

