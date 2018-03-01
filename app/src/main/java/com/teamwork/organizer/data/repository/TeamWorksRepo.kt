package com.teamwork.organizer.data.repository

import com.teamwork.organizer.data.api.TeamWorksService
import com.teamwork.organizer.data.model.Project
import com.teamwork.organizer.data.model.ProjectsList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Victor Tellez on 01/03/2018.
 */
class TeamWorksRepo {

    interface ProjectsCallback {
        fun successProjects(projects: ProjectsList)
        fun error()
    }

    private var disposable: Disposable? = null

    private val teamWorksApiServe by lazy {
        TeamWorksService.create()
    }

    fun loadProjects(callback: ProjectsCallback) {
        disposable = teamWorksApiServe.readProjects()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { projects -> callback.successProjects(projects) },
                        { onError -> callback.error() }
                )
    }

    fun dispose() {
        disposable?.dispose()
    }

    companion object {
        //private val TAG = TeamWorksRepo.javaClass.simpleName
    }

}