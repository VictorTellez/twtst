package com.teamwork.organizer.data.repository

import com.teamwork.organizer.data.api.TeamWorksService
import com.teamwork.organizer.data.model.TaskLists
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by Victor Tellez on 01/03/2018.
 */
class TaskListRepository {

    interface TaskListCallback {
        fun successTaskLists(taskLists: TaskLists)
        fun error()
    }

    private var disposable: Disposable? = null

    private val teamWorksApiServe by lazy {
        TeamWorksService.create()
    }

    fun loadTaskList(callback: TaskListCallback, project_id: Int) {
        disposable = teamWorksApiServe.readTaskLists(project_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<TaskLists>() {
                    override fun onError(e: Throwable) {
                        callback.error()
                    }

                    override fun onSuccess(taskList: TaskLists) {
                        callback.successTaskLists(taskList)
                    }
                }
                )
    }

    fun dispose() {
        if (disposable != null && !disposable!!.isDisposed) {
            disposable?.dispose()
        }
    }

    companion object {
        private val TAG = ProjectsRepository.javaClass.simpleName
    }

}