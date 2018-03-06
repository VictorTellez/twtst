package com.teamwork.organizer.data.repository

import com.teamwork.organizer.data.api.TeamWorksService
import com.teamwork.organizer.data.model.ProjectsList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by Victor Tellez on 01/03/2018.
 */
class ProjectsRepository : IProjectsRepository {

    interface ProjectsCallback {
        fun successProjects(projects: ProjectsList)
        fun error()
    }

    private var disposable: Disposable? = null

    private val teamWorksApiServe by lazy {
        TeamWorksService.create()
    }

    override fun loadProjects(callback: ProjectsCallback) {
        disposable = teamWorksApiServe.readProjects()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ProjectsList>() {
                    override fun onError(e: Throwable) {
                        callback.error()
                    }

                    override fun onSuccess(projects: ProjectsList) {
                        callback.successProjects(projects)
                    }
                }
                )
    }

    override fun dispose() {
        if (disposable != null && !disposable!!.isDisposed) {
            disposable?.dispose()
        }
    }

    companion object {
        private val TAG = ProjectsRepository.javaClass.simpleName
    }

}