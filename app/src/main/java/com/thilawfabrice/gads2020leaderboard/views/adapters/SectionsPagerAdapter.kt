package com.thilawfabrice.gads2020leaderboard.views.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.thilawfabrice.gads2020leaderboard.R
import com.thilawfabrice.gads2020leaderboard.views.LearningLeadersFragment
import com.thilawfabrice.gads2020leaderboard.views.SkillIQLeadersFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_learning_leaders,
    R.string.tab_skill_iq_leaders
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        return if (position == 0) {
            LearningLeadersFragment.newInstance()
        } else {
            SkillIQLeadersFragment.newInstance()
        }

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}