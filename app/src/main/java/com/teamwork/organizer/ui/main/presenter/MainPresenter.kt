package com.teamwork.organizer.ui.main.presenter


import com.teamwork.organizer.data.model.ProjectsList
import com.teamwork.organizer.data.repository.IProjectsRepository
import com.teamwork.organizer.ui.main.view.IMainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/**
 * This class will manage the view and the data.
 *
 * Created by Victor Tellez on 08/02/2018.
 */
class MainPresenter(val view: IMainView, val repository: IProjectsRepository) : IMainPresenter {

    private var disposable: Disposable? = null
    /**
     * Loads the projects and send them to the view.
     */
    /*override fun loadProjects() {
        repository.loadProjects(this)
    }*/

    override fun disposeProjects() {
        //ProjectsRepository().dispose()
        if (disposable != null && !disposable!!.isDisposed) {
            disposable?.dispose()
        }
    }

    override fun loadProjects() {
        disposable = repository.loadProjects()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ProjectsList>() {
                    override fun onError(e: Throwable) {
                        view.showError()
                    }

                    override fun onSuccess(projects: ProjectsList) {
                        view.showProjects(projects.projects)
                    }
                }
                )
    }


    companion object {
        private val TAG = MainPresenter::class.java.simpleName
    }
}