package com.test.mytesttask.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.test.mytesttask.model.Employee
import com.test.mytesttask.model.Specialty
import com.test.mytesttask.repository.EmployeeRepository
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    val repository: EmployeeRepository
) : ViewModel() {

    private val _employeeId = MutableLiveData<Int>()
    val employeeId : LiveData<Int>
        get() = _employeeId

    val employee : LiveData<Employee> = Transformations
        .switchMap(_employeeId) { _employeeId ->
            repository.getEmployee(_employeeId)
        }

    private val specialtiesList : LiveData<List<Specialty>> = Transformations
        .switchMap(employee) { employee ->
            repository.getSpecialtiesForEmployee(employee.id)
        }

    val specialtiesTitle: LiveData<String> = Transformations
        .map(specialtiesList) { specialtiesList ->
            specialtiesList.joinToString(separator = ", ") {
                    specialty -> specialty.name
            }
        }

    internal fun setEmployeeId(employeeId: Int) {
        if (_employeeId.value != employeeId) {
            _employeeId.value = employeeId
        }
    }
}