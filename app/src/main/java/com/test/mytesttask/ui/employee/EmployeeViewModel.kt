package com.test.mytesttask.ui.employee

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.test.mytesttask.model.Employee
import com.test.mytesttask.repository.EmployeeRepository
import javax.inject.Inject

class EmployeeViewModel @Inject constructor(
    val repository: EmployeeRepository
) : ViewModel() {

    private val _specialtyId = MutableLiveData<Int>()
    val specialtyId : LiveData<Int>
        get() = _specialtyId

    val employeeList : LiveData<List<Employee>> = Transformations
        .switchMap(_specialtyId) { specialtyId ->
            repository.getEmployeesForSpecialty(specialtyId)
        }

    internal fun setSpecialtyId(specialtyId: Int) {
        if (_specialtyId.value != specialtyId) {
            _specialtyId.value = specialtyId
        }
    }
}