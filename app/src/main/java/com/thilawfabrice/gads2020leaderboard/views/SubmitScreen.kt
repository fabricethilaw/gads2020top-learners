package com.thilawfabrice.gads2020leaderboard.views

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.thilawfabrice.gads2020leaderboard.App.Companion.repositorySubmissionApi
import com.thilawfabrice.gads2020leaderboard.R
import kotlinx.android.synthetic.main.content_scrolling.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class SubmitScreen : AppCompatActivity() {

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
            executeSubmitRequest(email, firstName, lastName, link)
        }
    }

    private fun executeSubmitRequest(
        email: String,
        firstName: String,
        lastName: String,
        link: String
    ) {
        repositorySubmissionApi.gadsSubmissionREST(
            email = email, firstName = firstName,
            lastName = lastName, repositoryLink = link
        ).enqueue(object : retrofit2.Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    showSuccessMessage()
                } else {
                    showFailureMessage()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                showFailureMessage()
            }
        })
    }

    private fun showSuccessMessage() {
        lifecycleScope.launch(Dispatchers.Main) {
            Toast.makeText(this@SubmitScreen, "Successful submission", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showFailureMessage() {
        lifecycleScope.launch(Dispatchers.Main) {
            lifecycleScope.launch(Dispatchers.Main) {
                Toast.makeText(this@SubmitScreen, "Submission Failure", Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }
}