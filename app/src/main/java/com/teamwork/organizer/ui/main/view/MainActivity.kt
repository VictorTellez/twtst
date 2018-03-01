package com.teamwork.organizer.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.teamwork.organizer.R
import com.teamwork.organizer.data.api.TeamWorksService
import com.teamwork.organizer.data.model.Project
import com.teamwork.organizer.data.model.ProjectsList
import com.teamwork.organizer.data.repository.RepoProjects
import com.teamwork.organizer.ui.main.adapter.RecyclerAdapter
import com.teamwork.organizer.ui.main.presenter.IMainPresenter
import com.teamwork.organizer.ui.main.presenter.MainPresenter
import com.teamwork.organizer.ui.projectDetail.view.ProjectDetailActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.longToast

/**
 * This is the main activity. This activity will show the list of the user projects.
 */
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, IMainView, View.OnClickListener {

    private lateinit var adapter: RecyclerAdapter
    private lateinit var presenter: IMainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureViews()
        presenter = MainPresenter(this)
        loadProjects()
    }

    private fun loadProjects() {
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
        presenter.loadProjects()
    }

    /**
     * Setup the views.
     */
    private fun configureViews() {
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
        val headerLayout = navigationView.getHeaderView(0)
        val avatar = headerLayout.findViewById<View>(R.id.avatar) as ImageView
        Picasso.with(this).load("https://s3.amazonaws.com/TWFiles/349705/userAvatar/tf_C21F3016-CD19-C60F-E82B23120C506FEE.Tac_the_Psychotic_Cat.jpg")
                .into(avatar)

        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onBackPressed() {
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.disposeProjects()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.nav_projects) {
            loadProjects()
        }

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun showProjects(projects: List<Project>) {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        adapter = RecyclerAdapter(projects, this)
        recyclerView.adapter = adapter
    }

    override fun showError() {
        progressBar.visibility = View.GONE
        longToast(R.string.error_loading_projects)
    }

    override fun onClick(view: View?) {
        val intent = Intent(this, ProjectDetailActivity::class.java)
        val holder = view?.tag as RecyclerAdapter.ProjectHolder
        intent.putExtra(ProjectDetailActivity.PROJECT, adapter.getProject(holder.adapterPosition))
        startActivity(intent)
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}
