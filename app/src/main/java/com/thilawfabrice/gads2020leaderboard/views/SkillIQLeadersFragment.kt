package com.thilawfabrice.gads2020leaderboard.views

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.thilawfabrice.gads2020leaderboard.App
import com.thilawfabrice.gads2020leaderboard.R
import com.thilawfabrice.gads2020leaderboard.viewmodels.PageViewModel
import com.thilawfabrice.gads2020leaderboard.viewmodels.toSkillsItemData
import com.thilawfabrice.gads2020leaderboard.views.adapters.SkillIQLeaderAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 *  A  fragment containing top Skill IQ leaders .
 */
class SkillIQLeadersFragment : Fragment() {

    // Use the 'by activityViewModels()' Kotlin property delegate
    // from the fragment-ktx artifact
    private val pageViewModel: PageViewModel by activityViewModels()
    private val mAdapter by lazy { SkillIQLeaderAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_skill_iq_leaders, container, false)
        val listView = root.findViewById<RecyclerView>(R.id.listView)

        initializeUI(listView)
        updateUIWhenDataIsAvailable()
        return root
    }


    private fun updateUIWhenDataIsAvailable() {
        lifecycleScope.launch(Dispatchers.Main) {

            pageViewModel.getTopSkillIQs(App.gadsLearningApi).observe(viewLifecycleOwner) { data ->

                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(
                        requireContext(),
                        "Skills data size ${data.size}",
                        Toast.LENGTH_SHORT
                    ).show()
                    mAdapter.update(data.toSkillsItemData())
                }
            }
        }


    }

    private fun initializeUI(listView: RecyclerView) {
        listView.adapter = mAdapter
    }


    companion object {

        /**
         * Returns a new instance of this fragment for the given section
         */
        @JvmStatic
        fun newInstance(): SkillIQLeadersFragment {
            return SkillIQLeadersFragment()
        }
    }
}