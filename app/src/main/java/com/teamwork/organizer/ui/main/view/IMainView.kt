package com.teamwork.organizer.ui.main.view

import com.teamwork.organizer.data.model.Project

/**
 * This interface provides the methods to communicate to the view by the presenter.
 *
 * Created by Victor Tellez on 08/02/2018.
 */
interface IMainView {
    /**
     * Shows all the projects.
     * @param projects project list
     */
    fun  showProjects(projects: List<Project>)

    /**
     * Shows an error when loading the projects.
     */
    fun showError()
}