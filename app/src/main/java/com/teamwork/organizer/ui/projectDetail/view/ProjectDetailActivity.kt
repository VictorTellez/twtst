package com.teamwork.organizer.ui.projectDetail.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.teamwork.organizer.R
import com.teamwork.organizer.data.model.Project
import kotlinx.android.synthetic.main.activity_detail.*
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import com.teamwork.organizer.ui.projectDetail.adapter.ViewPagerAdapter
import com.teamwork.organizer.ui.projectDetail.fragments.OverviewFragment
import com.teamwork.organizer.ui.projectDetail.fragments.TaskListFragment
import com.teamwork.organizer.ui.projectDetail.fragments.MilestoneFragment


/**
 * Created by Victor Tellez on 09/02/2018.
 */
class ProjectDetailActivity : AppCompatActivity() {

    private lateinit var tabTitle: Array<String>

    private lateinit var project: Project

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        getBundleData()
        configureViews()
    }

    private fun getBundleData() {
        project = intent.getSerializableExtra(PROJECT) as Project
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
        val overviewFragment = OverviewFragment().newInstance(project)
        val taskFragment = TaskListFragment().newInstance(project)
        val milestoneFragment = MilestoneFragment().newInstance(project)
        adapter.addFragment(overviewFragment, getString(R.string.detail_tab_one).toUpperCase())
        adapter.addFragment(taskFragment, getString(R.string.detail_tab_two).toUpperCase())
        adapter.addFragment(milestoneFragment, getString(R.string.detail_tab_three).toUpperCase())

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