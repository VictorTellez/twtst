package com.teamwork.organizer.ui.main.presenter

/**
 * This interface will be use to communicate with the view and with the repository to manage the data.
 *
 * Created by Victor Tellez on 08/02/2018.
 */
interface IMainPresenter {
    /**
     * Loads all the projects
     */
    fun loadProjects()

    /**
     * Dispose the projects data to avoid memory leaks
     */
    fun disposeProjects()
}