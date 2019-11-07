package com.test.mytesttask.ui.specialty

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.test.mytesttask.model.Specialty
import com.test.mytesttask.repository.EmployeeRepository
import javax.inject.Inject

class SpecialtyViewModel @Inject constructor(
    val repository: EmployeeRepository
) : ViewModel() {

    val specialtyList : LiveData<List<Specialty>> = repository.getSpecialties()
}