package com.thilawfabrice.gads2020leaderboard.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.thilawfabrice.gads2020leaderboard.R
import com.thilawfabrice.gads2020leaderboard.viewmodels.PageViewModel
import com.thilawfabrice.gads2020leaderboard.viewmodels.toLearnersItemData
import com.thilawfabrice.gads2020leaderboard.views.adapters.LearningLeaderAdapter

/**
 * A  fragment containing top learning leaders .
 */
class LearningLeadersFragment : Fragment() {

    // Use the 'by activityViewModels()' Kotlin property delegate
    // from the fragment-ktx artifact
    private val pageViewModel: PageViewModel by activityViewModels()
    private val mAdapter by lazy { LearningLeaderAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_learning_leaders, container, false)
        val listView = root.findViewById<RecyclerView>(R.id.listView)

        initializeUI(listView)
        updateUIWhenDataIsAvailable()
        pageViewModel.getTopLearners()
        return root
    }

    private fun updateUIWhenDataIsAvailable() {
        pageViewModel.getTopLearners().observe(viewLifecycleOwner, { data ->
            mAdapter.update(data.toLearnersItemData())
        })
    }

    private fun initializeUI(listView: RecyclerView) {
        listView.adapter = mAdapter
    }

    companion object {
        /**
         * Returns a new instance of this fragment for the given section
         */
        @JvmStatic
        fun newInstance(): LearningLeadersFragment {
            return LearningLeadersFragment()
        }
    }
}
