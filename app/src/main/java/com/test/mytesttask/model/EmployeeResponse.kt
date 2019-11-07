package com.test.mytesttask.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Json response from server. Root value is an array that stores all the information
 * about associates.
 */
data class EmployeeResponse(
    @SerializedName("response")
    @Expose
    var employeeList: List<Employee>,

    var status: Status
) {

    enum class Status {
        SUCCESS,
        FAILURE
    }

}