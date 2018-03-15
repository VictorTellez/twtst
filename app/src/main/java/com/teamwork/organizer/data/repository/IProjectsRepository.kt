package com.teamwork.organizer.data.repository

import com.teamwork.organizer.data.model.ProjectsList
import io.reactivex.Single

/**
 * Created by victor on 05/03/2018.
 */
interface IProjectsRepository {
   //fun loadProjects(callback: ProjectsRepository.ProjectsCallback)
   fun loadProjects() : Single<ProjectsList>
   fun dispose()
}