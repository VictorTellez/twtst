package com.teamwork.organizer.data.repository

import android.util.Log
import com.teamwork.organizer.data.api.APIService
import com.teamwork.organizer.data.api.ApiClient
import com.teamwork.organizer.data.model.TaskLists
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Repository to get the task lists.
 *
 * Created by Victor Tellez on 10/02/2018.
 */
class RepoTaskList {

    /**
     * Callback to get the task list.
     */
    interface TaskListCallback {
        fun successTaskList(list: TaskLists)
        fun error()
    }

    /**
     * Loads the task lists.
     */
    fun loadTaskLists(projectId: String, taskListCallback: TaskListCallback) {

        val mAPIService: APIService = ApiClient.apiServiceForTaskLists
        // RxJava
        mAPIService.readTaskLists(projectId.toInt())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : io.reactivex.Observer<TaskLists> {

                    override fun onError(e: Throwable) {
                        Log.e(TAG, "Rx error: " + e.message)
                        taskListCallback.error()
                    }

                    override fun onComplete() {
                        Log.d(TAG, "Rx onCompleted")
                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.d(TAG, "Rx onSubscribe")
                    }

                    override fun onNext(list: TaskLists) {
                        Log.d(TAG, "Rx onNext taskLists.size=${list.tasklists.size}")
                        taskListCallback.successTaskList(list)
                    }
                })
    }

    companion object {
        private val TAG = RepoTaskList::class.java.simpleName
    }
}