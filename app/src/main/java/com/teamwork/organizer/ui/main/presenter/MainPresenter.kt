package com.teamwork.organizer.ui.main.presenter

import android.util.Log
import com.teamwork.organizer.data.api.APIService
import com.teamwork.organizer.data.api.ApiClient
import com.teamwork.organizer.data.model.ProjectsList
import com.teamwork.organizer.ui.main.view.IMainView
import com.teamwork.organizer.ui.main.view.MainActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * This class will manage the view and the data.
 *
 * Created by Victor Tellez on 08/02/2018.
 */
class MainPresenter(val view: IMainView) : IMainPresenter {
    /**
     * Loads the projects and send them to the view.
     */
    override fun loadProjects() {
        val mAPIService: APIService = ApiClient.getApiService()
        // RxJava
        mAPIService.readProjects()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : io.reactivex.Observer<ProjectsList> {

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

                    override fun onNext(projects: ProjectsList) {
                        Log.d(TAG, "Rx onNext")
                        view.showProjects(projects.projects)
                    }
                })
    }

    companion object {
        private val TAG = MainPresenter::class.java.simpleName
    }
}