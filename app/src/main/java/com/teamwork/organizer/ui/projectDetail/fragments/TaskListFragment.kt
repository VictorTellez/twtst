package com.teamwork.organizer.ui.projectDetail.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.teamwork.organizer.R
import com.teamwork.organizer.data.model.Project
import com.teamwork.organizer.data.model.TodoList
import com.teamwork.organizer.ui.projectDetail.adapter.TaskListRecyclerAdapter
import com.teamwork.organizer.ui.projectDetail.presenter.ITaskListPresenter
import com.teamwork.organizer.ui.projectDetail.presenter.TaskListPresenter
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.longToast

/**
 * Task list fragment.
 */
class TaskListFragment : Fragment(), ITaskListView, View.OnClickListener {

    private lateinit var adapter: TaskListRecyclerAdapter
    private lateinit var presenter: ITaskListPresenter
    private lateinit var progressBar: LinearLayout
    private lateinit var recyclerView: RecyclerView

    /**
     * Gets a new instance of the fragment
     */
    fun newInstance(project: Project): TaskListFragment {
        val instance = TaskListFragment()
        val bundle = Bundle(1)
        bundle.putSerializable(PROJECT, project)
        instance.arguments = bundle
        return instance
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_task_list, container, false)
        configureViews(view)
        loadTaskLists()
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.menu_task_fragment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun showTaskLists(taskLists: List<TodoList>) {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        adapter = TaskListRecyclerAdapter(taskLists, this)
        recyclerView.adapter = adapter
    }

    override fun showError() {
        progressBar.visibility = View.GONE
        context.longToast(R.string.error_loading_taskList)
    }

    override fun onClick(v: View?) {}

    private fun configureViews(view: View) {
        progressBar =  view.findViewById<LinearLayout>(R.id.progressBarTaskList)
        recyclerView =  view.findViewById<RecyclerView>(R.id.recyclerViewTaskList)
        recyclerView.layoutManager = LinearLayoutManager(context)
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    /**
     * Loads the task lists.
     */
    private fun loadTaskLists() {
        presenter = TaskListPresenter(this)
        val project = arguments.getSerializable(PROJECT) as Project
        presenter.loadTasks(project.id)
    }

    companion object {
        val PROJECT = "PROJECT"
    }
}
