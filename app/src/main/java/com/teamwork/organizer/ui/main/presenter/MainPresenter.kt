package com.teamwork.organizer.ui.main.presenter

import com.teamwork.organizer.data.api.TeamWorksService
import com.teamwork.organizer.data.model.Project
import com.teamwork.organizer.data.model.ProjectsList
import com.teamwork.organizer.data.repository.RepoProjects
import com.teamwork.organizer.data.repository.TeamWorksRepo
import com.teamwork.organizer.ui.main.view.IMainView
/**
 * This class will manage the view and the data.
 *
 * Created by Victor Tellez on 08/02/2018.
 */
class MainPresenter(val view: IMainView) : IMainPresenter, TeamWorksRepo.ProjectsCallback, RepoProjects.ProjectsCallback {
    /**
     * Loads the projects and send them to the view.
     */
    override fun loadProjects() {
        //RepoProjects().loadProjects(this)
        TeamWorksRepo().loadProjects(this)
    }

    override fun successProjects(projects: ProjectsList) {
        view.showProjects(projects.projects)
    }

    override fun error() {
        view.showError()
    }

    override fun disposeProjects() {
        TeamWorksRepo().dispose()
    }

    companion object {
        private val TAG = MainPresenter::class.java.simpleName
    }
}