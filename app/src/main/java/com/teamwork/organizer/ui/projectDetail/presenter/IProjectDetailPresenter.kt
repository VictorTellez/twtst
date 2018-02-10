package com.teamwork.organizer.ui.projectDetail.presenter

/**
 * Interface to communicate with the presenter from the view. Loads a specific project.
 *
 * Created by Victor Tellez on 09/02/2018.
 */
interface IProjectDetailPresenter {
    /**
     * Loads the project by id.
     */
    fun loadProject(projectId: String)
}