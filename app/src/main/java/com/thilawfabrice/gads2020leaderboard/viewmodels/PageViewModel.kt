package com.thilawfabrice.gads2020leaderboard.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thilawfabrice.gads2020leaderboard.models.LearnerModel
import com.thilawfabrice.gads2020leaderboard.models.SkillIQModel
import com.thilawfabrice.gads2020leaderboard.repository.GadsApi

class PageViewModel : ViewModel() {

    private val liveLearners = MutableLiveData<List<LearnerModel>>()
    private val liveSkillIQs = MutableLiveData<List<SkillIQModel>>()


    suspend fun getTopLearners(api: GadsApi): LiveData<List<LearnerModel>> {
        val response = api.getLearningLearners()
        return when (response.isSuccessful) {
            true -> {
                liveLearners.postValue(response.body()!!)
                liveLearners
            }
            false -> {
                liveLearners.postValue(listOf())
                liveLearners
            }
        }
    }

    suspend fun getTopSkillIQs(api: GadsApi): LiveData<List<SkillIQModel>> {
        val response = api.getTopSkillIQs()
        return when (response.isSuccessful) {
            true -> {
                liveSkillIQs.postValue(response.body()!!)
                liveSkillIQs
            }
            false -> {
                liveSkillIQs.postValue(listOf())
                liveSkillIQs
            }
        }
    }

}

class ListItemData(val name: String, val details: String, val badge: String)

fun List<LearnerModel>.toLearnersItemData() = map {
    ListItemData(
        name = it.name,
        badge = it.badgeUrl,
        details = "${it.hours} learning hours, ${it.country}"
    )
}

fun List<SkillIQModel>.toSkillsItemData() = map {
    ListItemData(
        name = it.name,
        badge = it.badgeUrl,
        details = "${it.score} skill IQ score, ${it.country}"
    )
}