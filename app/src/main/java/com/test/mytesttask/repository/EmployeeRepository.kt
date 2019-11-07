package com.test.mytesttask.repository

import androidx.lifecycle.LiveData
import com.test.mytesttask.model.Employee
import com.test.mytesttask.model.EmployeeResponse
import com.test.mytesttask.model.Specialty
import com.test.mytesttask.repository.db.EmployeeDatabase
import com.test.mytesttask.repository.service.EmployeeWebservice
import com.test.mytesttask.util.EmployeeDataFormatHandler
import retrofit2.Call
import retrofit2.Response
import java.io.IOException
import java.util.concurrent.Executor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmployeeRepository @Inject constructor(
    private val service: EmployeeWebservice,
    private val database: EmployeeDatabase,
    /** Data handler from the server, converts the data to a single correct style */
    private val dataFormatHandler : EmployeeDataFormatHandler,
    /** Contractor to start async data retrieval from the server and database */
    private val executor: Executor
) : Repository {

    private val specialtyDao = database.specialtyDao()
    private val employeeDao = database.employeeDao()
    private val employeeSpecialtyJoinDao = database.employeeSpecialtyJoinDao()

    /**
     * A flag that determines the need for updating the data. Data is considered obsolete after
     * a new application launch.
     */
    private var isFreshData: Boolean = false

    override fun getSpecialties(): LiveData<List<Specialty>> {
        initiateData()
        return specialtyDao.getAll()
    }

    override fun getEmployeesForSpecialty(specialtyId: Int): LiveData<List<Employee>> {
        initiateData()
        return employeeSpecialtyJoinDao.getEmployeesForSpecialty(specialtyId)
    }

    override fun getEmployee(employeeId: Int): LiveData<Employee> {
        initiateData()
        return employeeDao.getEmployee(employeeId)
    }

    override fun getSpecialtiesForEmployee(employeeId: Int): LiveData<List<Specialty>> {
        initiateData()
        return employeeSpecialtyJoinDao.getSpecialtiesForEmployee(employeeId)
    }

    /**
     * Check for the availability of data in the database, as well as for their relevance.
     * After it starts loading data from the server, starts saving this data to the database
     * and sets the flag that the data is up to date.
     */
    private fun initiateData() {
        executor.execute {
            if (employeeDao.hasEmployee() != 0 && isFreshData) return@execute

            // Get data from service
            val employeeResponse = loadDataFromWebservice()

            // Save data to local db
            if (employeeResponse != null
                && employeeResponse.status == EmployeeResponse.Status.SUCCESS) {
                // Bring unformatted data from the server to a single correct style
                dataFormatHandler.handleData(employeeResponse.employeeList)
                // Save formatted data in the database
                saveDataToDatabase(employeeResponse.employeeList)
                // Indicates that the data is relevant
                isFreshData = true
            }
        }
    }

    private fun loadDataFromWebservice(): EmployeeResponse? {
        // Refreshes the data.
        val call: Call<EmployeeResponse> = service.getEmployeesResponse()
        val response: Response<EmployeeResponse> = try {
            call.execute()
        } catch (exc: IOException) {
            exc.printStackTrace()
            return EmployeeResponse(emptyList(), EmployeeResponse.Status.FAILURE)
        }

        val employeeResponse = response.body()
        if (response.isSuccessful) {
            employeeResponse?.status = EmployeeResponse.Status.SUCCESS
        } else {
            employeeResponse?.status = EmployeeResponse.Status.FAILURE
        }

        return employeeResponse
    }

    private fun saveDataToDatabase(employeeList: List<Employee>) {
        // Delete old data
        database.clearAllTables()
        // Write employees
        employeeDao.insertAll(employeeList)

        // Get and write specialties
        val specialtySet = employeeList.flatMapTo(hashSetOf()) {
            it.specialtyList
        }
        specialtyDao.insertAll(specialtySet)

        // Generate and write employee relationships with their specialties
        val employeeSpecialtySet = employeeList.flatMapTo(hashSetOf()) {
            it.generateEmployeeSpecialtyList()
        }
        employeeSpecialtyJoinDao.insertAll(employeeSpecialtySet)
    }
}