package com.teamwork.organizer.ui.projectDetail.presenter

/**
 * Interface to communicate with the presenter from the view. Loads the task list.
 *
 * Created by Victor Tellez on 09/02/2018.
 */
interface ITaskListPresenter {
    /**
     * Loads the task list.
     */
    fun loadTasks(projectId: String)
}