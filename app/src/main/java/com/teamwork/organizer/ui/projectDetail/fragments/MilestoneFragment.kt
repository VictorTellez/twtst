package com.teamwork.organizer.ui.projectDetail.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup

import com.teamwork.organizer.R
import com.teamwork.organizer.data.model.Project


/**
 * Milestones.
 */
class MilestoneFragment : Fragment() {
    /**
     * Gets a new instance of the fragment
     */
    fun newInstance(project: Project): MilestoneFragment {
        val instance = MilestoneFragment()
        val bundle = Bundle(1)
        bundle.putSerializable(PROJECT, project)
        instance.arguments = bundle
        return instance
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_milestones, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.menu_milestone_fragment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    companion object {
        val PROJECT = "PROJECT"
    }

}
