package com.teamwork.organizer.ui.projectDetail.presenter

import android.util.Log
import com.teamwork.organizer.data.api.APIService
import com.teamwork.organizer.data.api.ApiClient
import com.teamwork.organizer.data.model.Project
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * This class loads a project and send it to the view.
 *
 * Created by Victor Tellez on 09/02/2018.
 */
class ProjectDetailPresenter(val view: IProjectDetailView) : IProjectDetailPresenter {
    /**
     * Loads a project by id.
     */
    override fun loadProject(projectId: String) {
        val mAPIService: APIService = ApiClient.getApiServiceForProject()
        // RxJava
        mAPIService.readProject(projectId.toInt())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : io.reactivex.Observer<Project> {

                    override fun onError(e: Throwable) {
                        Log.e(TAG, "Rx error: " + e.message)
                        view.showError()
                    }

                    override fun onComplete() {
                        Log.d(TAG, "Rx onCompleted")
                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.d(TAG, "Rx onSubscribe")
                    }

                    override fun onNext(project: Project) {
                        Log.d(TAG, "Rx onNext projectId=$projectId project=$project")
                        view.showProject(project)
                    }
                })
    }

    companion object {
        private val TAG = ProjectDetailPresenter::class.java.simpleName
    }
}