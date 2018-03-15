package com.teamwork.organizer.ui.main.presenter

import com.teamwork.organizer.data.model.Project
import com.teamwork.organizer.data.model.ProjectsList
import com.teamwork.organizer.data.repository.IProjectsRepository
import com.teamwork.organizer.ui.main.view.IMainView
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.plugins.RxAndroidPlugins
import org.junit.runner.RunWith
import org.mockito.Mockito.doThrow
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by victor on 05/03/2018.
 */
//@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {

    @Rule @JvmField
    var mockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var view: IMainView

    @Mock
    private lateinit var repository: IProjectsRepository

    private lateinit var presenter: MainPresenter

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }
        presenter = MainPresenter(view, repository)
    }

    @Test
    fun shouldPassProjectsListToView() {
        val PROJECT_LIST: ProjectsList = ProjectsList(listOf(Project(), Project(), Project()))
        Mockito.`when`(repository.loadProjects()).thenReturn(Single.just(PROJECT_LIST))
        presenter.loadProjects()
        //view.showProjects(PROJECT_LIST.projects)
        verify(view).showProjects(PROJECT_LIST.projects)
    }

    @Test
    fun shouldPassEmptyListToView() {
        Mockito.`when`(repository.loadProjects()).thenReturn(Single.just(EMPTY))
        presenter.loadProjects()
        verify(view).showProjects(EMPTY.projects)
    }

    /*@Test
    fun shouldHandleError() {
        Mockito.`when`(repository.loadProjects()).thenReturn(Single.error(Throwable("boom")))
        presenter.loadProjects()
        verify(view).showError()
    }*/

    companion object {
        private val EMPTY: ProjectsList = ProjectsList(emptyList())
    }

}