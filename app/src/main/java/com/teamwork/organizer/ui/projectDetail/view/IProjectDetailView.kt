package com.teamwork.organizer.ui.projectDetail.presenter

import com.teamwork.organizer.data.model.TaskLists
import com.teamwork.organizer.data.model.TodoList

/**
 * Interface to access to the view from the presenter.
 *
 * Created by Victor Tellez on 09/02/2018.
 */
interface IProjectDetailView {
    fun showTaskLists(taskLists: List<TodoList>)
    fun showError()
}