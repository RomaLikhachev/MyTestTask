package com.test.mytesttask.repository.service

import com.test.mytesttask.model.EmployeeResponse
import retrofit2.Call
import retrofit2.http.GET

interface EmployeeWebservice {

    @GET("65gb/static/raw/master/testTask.json")
    fun getEmployeesResponse(): Call<EmployeeResponse>
}