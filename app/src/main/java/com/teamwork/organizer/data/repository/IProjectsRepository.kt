package com.teamwork.organizer.data.repository

/**
 * Created by victor on 05/03/2018.
 */
interface IProjectsRepository {
   fun loadProjects(callback: ProjectsRepository.ProjectsCallback)
   fun dispose()
}