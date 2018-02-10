package com.teamwork.organizer.ui.main.presenter

import com.teamwork.organizer.data.model.ProjectsList
import com.teamwork.organizer.data.repository.RepoProjects
import com.teamwork.organizer.ui.main.view.IMainView
/**
 * This class will manage the view and the data.
 *
 * Created by Victor Tellez on 08/02/2018.
 */
class MainPresenter(val view: IMainView) : IMainPresenter, RepoProjects.ProjectsCallback {

    /**
     * Loads the projects and send them to the view.
     */
    override fun loadProjects() {
        RepoProjects().loadProjects(this)
    }

    override fun successProjects(list: ProjectsList) {
        view.showProjects(list.projects)
    }

    override fun error() {
        view.showError()
    }

    companion object {
        private val TAG = MainPresenter::class.java.simpleName
    }
}