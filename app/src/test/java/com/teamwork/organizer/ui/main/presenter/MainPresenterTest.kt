package com.teamwork.organizer.ui.main.presenter

import com.teamwork.organizer.data.model.Project
import com.teamwork.organizer.data.repository.IProjectsRepository
import com.teamwork.organizer.data.repository.ProjectsRepository
import com.teamwork.organizer.ui.main.view.IMainView
import org.junit.Assert
import org.junit.Test

/**
 * Created by victor on 05/03/2018.
 */
class MainPresenterTest {

    @Test
    fun shouldPassBooksToView() {

        // given
        val view: IMainView = MockView()

        // when
        val presenter: MainPresenter = MainPresenter(view, ProjectsRepository())
        presenter.loadProjects()

        // then
        view.showProjects(listOf())
    }

    private class MockView : IMainView {
        var passed = false
        override fun showProjects(projects: List<Project>) {
            passed = true
        }

        override fun showError() {
        }
    }

    private class MockProjectsRepository : IProjectsRepository {
        override fun loadProjects(callback: ProjectsRepository.ProjectsCallback) {
        }

        override fun dispose() {
        }
    }
}