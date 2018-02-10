package com.teamwork.organizer.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Task list model.
 *
 * Created by Victor Tellez on 10/02/2018.
 */

public class TodoList {

    @SerializedName("projectid")
    @Expose
    private String projectid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("milestone-id")
    @Expose
    private String milestoneId;
    @SerializedName("uncompleted-count")
    @Expose
    private String uncompletedCount;
    @SerializedName("complete")
    @Expose
    private Boolean complete;
    @SerializedName("private")
    @Expose
    private String _private;
    @SerializedName("overdue-count")
    @Expose
    private String overdueCount;
    @SerializedName("project-name")
    @Expose
    private String projectName;
    @SerializedName("pinned")
    @Expose
    private Boolean pinned;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("completed-count")
    @Expose
    private String completedCount;

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(String milestoneId) {
        this.milestoneId = milestoneId;
    }

    public String getUncompletedCount() {
        return uncompletedCount;
    }

    public void setUncompletedCount(String uncompletedCount) {
        this.uncompletedCount = uncompletedCount;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public String getPrivate() {
        return _private;
    }

    public void setPrivate(String _private) {
        this._private = _private;
    }

    public String getOverdueCount() {
        return overdueCount;
    }

    public void setOverdueCount(String overdueCount) {
        this.overdueCount = overdueCount;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Boolean getPinned() {
        return pinned;
    }

    public void setPinned(Boolean pinned) {
        this.pinned = pinned;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompletedCount() {
        return completedCount;
    }

    public void setCompletedCount(String completedCount) {
        this.completedCount = completedCount;
    }

}