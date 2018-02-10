package com.teamwork.organizer.ui.projectDetail.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.res.ResourcesCompat.getColor
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.squareup.picasso.Picasso

import com.teamwork.organizer.R
import com.teamwork.organizer.data.model.Project
import com.teamwork.organizer.ui.utils.formatMonthYear
import android.graphics.PorterDuff

/**
 * Project overview fragment
 */
class OverviewFragment : Fragment() {
    /**
     * Gets a new instance of the fragment
     */
    fun newInstance(project: Project): OverviewFragment {
        val instance = OverviewFragment()
        val bundle = Bundle(1)
        bundle.putSerializable(PROJECT, project)
        instance.arguments = bundle
        return instance
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_overview, container, false)
        bindProject(view)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_overview_fragment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    /**
     * Binds the detail of the project.
     */
    private fun bindProject(view: View) {
        val project = arguments.getSerializable(PROJECT) as Project
        val image = view.findViewById<ImageView>(R.id.detailImage)
        val name = view.findViewById<TextView>(R.id.detailName)
        val company = view.findViewById<TextView>(R.id.detailCompany)
        val status = view.findViewById<TextView>(R.id.detailStatus)
        val description = view.findViewById<TextView>(R.id.detailDescription)
        val starred = view.findViewById<RatingBar>(R.id.starred)
        val dates = view.findViewById<TextView>(R.id.datesDetail)
        val lastChange = view.findViewById<TextView>(R.id.lastChangeDateDetail)

        Picasso.with(context).load(project.logo).into(image)
        name?.text = project.name
        company.text = project.company.name
        status.text = project.status.capitalize()
        description?.text = project.description
        setRatingBar(starred, project)
        dates.text = project.startDate.formatMonthYear() + " - " + project.endDate.formatMonthYear()
        lastChange.text = project.lastChangedOn.formatMonthYear()

        status.setTextColor(if (project.status == "active") getColor(resources, R.color.activeStatus, null)
                            else getColor(resources, R.color.otherStatus, null))
    }

    /**
     * Sets the style of the rating bar.
     */
    private fun setRatingBar(starred: RatingBar, project: Project) {
        starred.rating = if (project.starred) 1.0f else 0.0f
        val drawable = starred.progressDrawable
        if (project.starred) {
            drawable.setColorFilter(getColor(resources, R.color.rating_activated, null), PorterDuff.Mode.SRC_ATOP)
        } else {
            drawable.setColorFilter(getColor(resources, R.color.rating_disable, null), PorterDuff.Mode.SRC_ATOP)
        }
    }

    companion object {
        val PROJECT = "PROJECT"
    }
}
