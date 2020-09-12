package com.thilawfabrice.gads2020leaderboard.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.thilawfabrice.gads2020leaderboard.R
import com.thilawfabrice.gads2020leaderboard.views.adapters.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_leaderboard_screen.*

class LeaderBoardScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard_screen)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        // todo disable allcaps on tabs
        tabs.setupWithViewPager(viewPager)
        btnSubmitProject.setOnClickListener {
            startActivity(Intent(this, SubmitRepository::class.java))
        }


    }
}