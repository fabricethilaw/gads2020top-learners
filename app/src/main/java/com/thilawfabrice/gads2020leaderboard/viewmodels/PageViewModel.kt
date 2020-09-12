package com.thilawfabrice.gads2020leaderboard.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thilawfabrice.gads2020leaderboard.models.LearnerModel
import com.thilawfabrice.gads2020leaderboard.models.SkillIQModel

class PageViewModel : ViewModel() {

    private val liveLearners = MutableLiveData<List<LearnerModel>>()
    private val liveSkillIQs = MutableLiveData<List<SkillIQModel>>()


    fun getTopLearners(): LiveData<List<LearnerModel>> {
        return MutableLiveData<List<LearnerModel>>()
    }

    fun getTopSkillIQs(): LiveData<List<SkillIQModel>> {
        return MutableLiveData<List<SkillIQModel>>()
    }

}

class ListItemData(val name: String, val details: String, val badge: String)

fun List<LearnerModel>.toLearnersItemData() = map {
    ListItemData(
        name = it.name,
        badge = it.badgeUrl,
        details = "${it.hour} learning hours, ${it.country}"
    )
}

fun List<SkillIQModel>.toSkillsItemData() = map {
    ListItemData(
        name = it.name,
        badge = it.badgeUrl,
        details = "${it.score} skill IQ score, ${it.country}"
    )
}