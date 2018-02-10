package com.teamwork.organizer.ui.projectDetail.fragments

import com.teamwork.organizer.data.model.TodoList

/**
 * Created by Victor Tellez on 10/02/2018.
 */
interface ITaskListView {
    fun showTaskLists(taskLists: List<TodoList>)
    fun showError()
}