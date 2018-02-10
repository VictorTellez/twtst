package com.teamwork.organizer.ui.projectDetail.presenter

import com.teamwork.organizer.data.model.Project

/**
 * Interface to access to the view from the presenter.
 *
 * Created by Victor Tellez on 09/02/2018.
 */
interface IProjectDetailView {
    fun showProject(project: Project)
    fun showError()
}