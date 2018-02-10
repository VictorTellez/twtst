package com.teamwork.organizer.ui.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.teamwork.organizer.R
import com.teamwork.organizer.data.model.Project
import com.teamwork.organizer.ui.utils.inflate
import kotlinx.android.synthetic.main.recycler_item.view.*

/**
 * The adapter of the recycler.
 *
 * Created by Victor Tellez on 08/02/2018.
 */
class RecyclerAdapter(private val projects: List<Project>, var clickListener : View.OnClickListener?) : RecyclerView.Adapter<RecyclerAdapter.ProjectHolder>() {

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

    fun getProject(adapterPosition: Int) : Project {
        return projects[adapterPosition]
    }

    inner class ProjectHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            if (clickListener != null) {
                itemView.setOnClickListener(clickListener)
            }
            itemView.tag = this
        }

        fun bindProject(project: Project) {
            Picasso.with(itemView.context).load(project.logo).into(itemView.image)
            itemView.name.text = project.name
            itemView.uncompleted.text = project.description
        }
    }
}