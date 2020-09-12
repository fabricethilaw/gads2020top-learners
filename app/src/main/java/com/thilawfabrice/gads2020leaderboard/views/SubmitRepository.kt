package com.thilawfabrice.gads2020leaderboard.views

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.thilawfabrice.gads2020leaderboard.App.Companion.repositorySubmissionApi
import com.thilawfabrice.gads2020leaderboard.R
import kotlinx.android.synthetic.main.content_scrolling.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubmitRepository : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit_repository)
        setSupportActionBar(findViewById(R.id.toolbar))

        submit.setOnClickListener {

            sendRepositoryDetails()

        }
    }

    private fun sendRepositoryDetails() {
        val email = inputEmail.text.toString().trim()
        val firstName = inputFirstName.text.toString().trim()
        val lastName = inputLastName.text.toString().trim()
        val link = inputGithubLink.text.toString().trim()
        if (email.isNotBlank() && firstName.isNotBlank() && lastName.isNotBlank() && link.isNotBlank()) {
            CoroutineScope(Dispatchers.Main).launch {
                repositorySubmissionApi.gadsSubmissionREST(
                    email = email, firstName = firstName,
                    lastName = lastName, repositoryLink = link
                ).run {
                    if (this.isSuccessful) {
                        Handler(Looper.getMainLooper()).post {
                            Toast.makeText(
                                this@SubmitRepository,
                                this.code().toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }
}