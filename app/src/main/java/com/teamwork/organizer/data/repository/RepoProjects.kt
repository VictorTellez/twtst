package com.teamwork.organizer.data.repository

import android.util.Log
import com.teamwork.organizer.data.api.APIService
import com.teamwork.organizer.data.api.ApiClient
import com.teamwork.organizer.data.model.ProjectsList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Repository to load the projects
 * Created by Victor Tellez on 10/02/2018.
 */
class RepoProjects {

    /**
     * Callback to get the project list.
     */
    interface ProjectsCallback {
        fun successProjects(list: ProjectsList)
        fun error()
    }

    /**
     * Loads projects
     */
    fun loadProjects(callback: ProjectsCallback) {
        val mAPIService: APIService = ApiClient.apiService
        // RxJava
        mAPIService.readProjects()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : io.reactivex.Observer<ProjectsList> {

                    override fun onError(e: Throwable) {
                        Log.e(TAG, "Rx error: " + e.message)
                    }

                    override fun onComplete() {
                        Log.d(TAG, "Rx onCompleted")
                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.d(TAG, "Rx onSubscribe")
                    }

                    override fun onNext(projects: ProjectsList) {
                        Log.d(TAG, "Rx onNext")
                        callback.successProjects(projects)
                    }
                })
    }

    companion object {
        private val TAG = RepoProjects::class.java.simpleName
    }

}