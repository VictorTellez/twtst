package com.teamwork.organizer.ui.projectDetail.presenter

import com.teamwork.organizer.data.repository.RepoTaskList
import com.teamwork.organizer.data.model.TaskLists
import com.teamwork.organizer.data.repository.TeamWorksProjectsRepo
import com.teamwork.organizer.ui.projectDetail.fragments.ITaskListView

/**
 * This class loads the task list and send to the view. Business logic.
 *
 * Created by Victor Tellez on 09/02/2018.
 */
class TaskListPresenter(val view: ITaskListView) : ITaskListPresenter,  RepoTaskList.TaskListCallback, TeamWorksProjectsRepo.TaskListCallback {

    /**
     * Loads list tasks
     */
    override fun loadTasks(projectId: String) {
        //RepoTaskList().loadTaskLists(projectId, this)
        TeamWorksProjectsRepo().loadTaskList(this, projectId.toInt())
    }

    override fun successTaskLists(list: TaskLists) {
        view.showTaskLists(list.tasklists)
    }

    override fun error() {
        view.showError()
    }

    override fun disposeTasks() {
        TeamWorksProjectsRepo().dispose()
    }
}