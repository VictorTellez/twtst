package com.teamwork.organizer.data.api;

import com.teamwork.organizer.data.model.Project;
import com.teamwork.organizer.data.model.ProjectsList;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * This are the methods to get the Api data.
 *
 * Created by Victor Tellez on 01/12/2017.
 */
public interface APIService {
    /**
     * Gets all the projects.
     * @return projects
     */
    @GET("projects.json")
    Observable<ProjectsList> readProjects();

    /**
     * Get a project by id.
     * @param project_id id
     * @return project
     */
    @GET("projects/{project_id}.json")
    Observable<Project> readProject(@Path(value = "project_id", encoded = true) int project_id);
}
