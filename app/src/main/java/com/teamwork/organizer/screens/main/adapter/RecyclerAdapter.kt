package com.teamwork.organizer.screens.main.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.teamwork.organizer.R
import com.teamwork.organizer.data.model.Project
import com.teamwork.organizer.screens.utils.inflate
import kotlinx.android.synthetic.main.recycler_item.view.*

/**
 * The adapter of the recycler.
 *
 * Created by Victor Tellez on 08/02/2018.
 */
class RecyclerAdapter(private val projects: List<Project>) : RecyclerView.Adapter<RecyclerAdapter.ProjectHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectHolder {
        val inflatedView = parent.inflate(R.layout.recycler_item, false)
        return ProjectHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ProjectHolder, position: Int) {
        holder.bindProject(projects[position])
    }

    override fun getItemCount(): Int {
        return projects.size
    }

    class ProjectHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            Log.d("RecyclerView", "CLICK!")
        }

        fun bindProject(project: Project) {
            Picasso.with(view.context).load(project.logo).into(view.image)
            view.name.text = project.name
            view.description.text = project.description
        }

    }
}