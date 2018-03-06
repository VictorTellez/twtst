package com.teamwork.organizer.ui.projectdetail.presenter

import com.teamwork.organizer.data.repository.RepoTaskList
import com.teamwork.organizer.data.model.TaskLists
import com.teamwork.organizer.data.repository.ProjectsRepository
import com.teamwork.organizer.data.repository.TaskListRepository
import com.teamwork.organizer.ui.projectdetail.fragments.ITaskListView

/**
 * This class loads the task list and send to the view. Business logic.
 *
 * Created by Victor Tellez on 09/02/2018.
 */
class TaskListPresenter(val view: ITaskListView) : ITaskListPresenter,  RepoTaskList.TaskListCallback, TaskListRepository.TaskListCallback {

    /**
     * Loads list tasks
     */
    override fun loadTasks(projectId: String) {
        //RepoTaskList().loadTaskLists(projectId, this)
        TaskListRepository().loadTaskList(this, projectId.toInt())
    }

    override fun successTaskLists(list: TaskLists) {
        view.showTaskLists(list.tasklists)
    }

    override fun error() {
        view.showError()
    }

    override fun disposeTasks() {
        ProjectsRepository().dispose()
    }
}