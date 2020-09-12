package com.thilawfabrice.gads2020leaderboard.views

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.thilawfabrice.gads2020leaderboard.R

class LaunchScreen : AppCompatActivity() {

    private val SPLASH_DISPLAY_LENGHT = 1200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        displayLeaderboardScreen(SPLASH_DISPLAY_LENGHT.toLong())
    }

    /**
     * Display Leaderboard screen automatically
     * after the specified amount of time elapses
     */
    private fun displayLeaderboardScreen(delayMillisecondsTime: Long) {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LeaderBoardScreen::class.java)
            startActivity(intent)
            finish()

        }, delayMillisecondsTime)
    }

}