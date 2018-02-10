package com.teamwork.organizer.ui.projectDetail.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.teamwork.organizer.R
import com.teamwork.organizer.data.model.Project
import com.teamwork.organizer.ui.projectDetail.presenter.IProjectDetailPresenter
import com.teamwork.organizer.ui.projectDetail.presenter.IProjectDetailView
import com.teamwork.organizer.ui.projectDetail.presenter.ProjectDetailPresenter
import kotlinx.android.synthetic.main.activity_detail.*
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import com.teamwork.organizer.data.model.TaskLists
import com.teamwork.organizer.data.model.TodoList
import com.teamwork.organizer.ui.projectDetail.adapter.ViewPagerAdapter
import com.teamwork.organizer.ui.projectDetail.fragments.OverviewFragment
import com.teamwork.organizer.ui.projectDetail.fragments.TaskFragment
import com.teamwork.organizer.ui.projectDetail.fragments.MilestoneFragment


/**
 * Created by Victor Tellez on 09/02/2018.
 */
class ProjectDetailActivity : AppCompatActivity(), IProjectDetailView {

    private lateinit var tabTitle: Array<String>

    //TODO set Task in tab - Inbox tasks
    private var unreadCount = intArrayOf(0, 5, 0)

    private lateinit var presenter: IProjectDetailPresenter
    private lateinit var project: Project

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        getBundleData()
        configureViews()
        presenter = ProjectDetailPresenter(this)
        loadProjectTasksAndMilestones()
    }

    override fun showTaskLists(taskLists: List<TodoList>) {

    }

    override fun showError() {
    }

    private fun getBundleData() {
        project = intent.getSerializableExtra(PROJECT) as Project
    }

    private fun loadProjectTasksAndMilestones() {
        presenter.loadTasks(project.id)
    }

    /**
     * Setup the views.
     */
    private fun configureViews() {
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.title = project.name
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        setupViewPager()
    }

    private fun setupViewPager() {
        tabTitle = arrayOf(
                getString(R.string.detail_tab_one).toUpperCase(),
                getString(R.string.detail_tab_two).toUpperCase(),
                getString(R.string.detail_tab_three).toUpperCase())

        detailViewPager.offscreenPageLimit = 3

        detailViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                detailTabLayout.getTabAt(position)?.select()
            }
            override fun onPageScrollStateChanged(state: Int) {}
        })

        val adapter = ViewPagerAdapter(supportFragmentManager)

        //TODO Pass data to fragments newInstance method
        val callsFragment = OverviewFragment().newInstance(project)
        val chatFragment = TaskFragment()
        val contactsFragment = MilestoneFragment()
        adapter.addFragment(callsFragment, getString(R.string.detail_tab_one).toUpperCase())
        adapter.addFragment(chatFragment, getString(R.string.detail_tab_two).toUpperCase())
        adapter.addFragment(contactsFragment, getString(R.string.detail_tab_three).toUpperCase())

        detailViewPager.adapter = adapter
        detailTabLayout.setupWithViewPager(detailViewPager)
        detailTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                detailViewPager.setCurrentItem(tab.position, false)
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

    }

    companion object {
        val PROJECT = "PROJECT"
    }
}