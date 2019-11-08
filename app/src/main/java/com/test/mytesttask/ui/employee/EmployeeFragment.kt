package com.test.mytesttask.ui.employee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.test.mytesttask.R
import dagger.android.support.DaggerFragment

class EmployeeFragment : DaggerFragment() {

    companion object {
        fun newInstance() = EmployeeFragment()
    }

    private lateinit var viewModel: EmployeeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EmployeeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
