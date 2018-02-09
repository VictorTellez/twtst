package com.teamwork.organizer.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.util.Date

/**
 * Project model.
 * Created by Victor Tellez on 07/02/2018.
 */
data class Project(val company: Company,
                   val starred: Boolean,
                   val name: String,
                   val showAnnouncement: Boolean,
                   val announcement: String,
                   val description: String,
                   val status: String,
                   val isProjectAdmin: Boolean,
                   val createdOn: String,
                   val startPage: String? = "",
                   val startDate: String? = "",
                   val logo: String? = "",
                   val notifyeveryone: Boolean = false,
                   val id: String,
                   val lastChangedOn: String? = "",
                   val endDate: String? = "",
                   val harvestTimersEnabled: Boolean = true) {}