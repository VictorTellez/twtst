package com.teamwork.organizer.ui.projectdetail.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.teamwork.organizer.R
import com.teamwork.organizer.data.model.TodoList
import com.teamwork.organizer.ui.utils.inflate
import kotlinx.android.synthetic.main.recycler_item_task_list.view.*

/**
 * The adapter of the recycler.
 *
 * Created by Victor Tellez on 08/02/2018.
 */
class TaskListRecyclerAdapter(private val taskList: List<TodoList>, var clickListener : View.OnClickListener?) : RecyclerView.Adapter<TaskListRecyclerAdapter.TaskListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListHolder {
        val inflatedView = parent.inflate(R.layout.recycler_item_task_list, false)
        return TaskListHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: TaskListHolder, position: Int) {
        holder.bindProject(taskList[position])
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun getProject(adapterPosition: Int) : TodoList {
        return taskList[adapterPosition]
    }

    inner class TaskListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            if (clickListener != null) {
                itemView.setOnClickListener(clickListener)
            }
            itemView.tag = this
        }

        fun bindProject(taskList: TodoList) {
            itemView.name.text = taskList.name.capitalize()
            itemView.uncompleted.text = if (taskList.uncompletedCount == null) "0" else taskList.uncompletedCount
        }
    }
}