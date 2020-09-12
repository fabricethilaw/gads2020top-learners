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
import com.thilawfabrice.gads2020leaderboard.viewmodels.toLearnersItemData
import com.thilawfabrice.gads2020leaderboard.views.adapters.LearningLeaderAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A  fragment containing top learning leaders .
 */
class LearningLeadersFragment : Fragment() {

    // Use the 'by activityViewModels()' Kotlin property delegate
    // from the fragment-ktx artifact
    private val pageViewModel: PageViewModel by activityViewModels()
    private val mAdapter by lazy { LearningLeaderAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_learning_leaders, container, false)
        val listView = root.findViewById<RecyclerView>(R.id.listView)

        initializeUI(listView)
        updateUIWhenDataIsAvailable()
        return root
    }

    private fun updateUIWhenDataIsAvailable() {
        lifecycleScope.launch(
            Dispatchers.Main
        ) {
            pageViewModel.getTopLearners(App.gadsLearningApi).observe(viewLifecycleOwner) { data ->

                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(requireContext(), "Learners ${data.size}", Toast.LENGTH_SHORT)
                        .show()
                    mAdapter.update(data.toLearnersItemData())
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
        fun newInstance(): LearningLeadersFragment {
            return LearningLeadersFragment()
        }
    }
}
