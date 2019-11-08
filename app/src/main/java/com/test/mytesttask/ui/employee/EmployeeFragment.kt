package com.test.mytesttask.ui.employee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.test.mytesttask.R
import com.test.mytesttask.databinding.FragmentListBinding
import com.test.mytesttask.model.Employee
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class EmployeeFragment : DaggerFragment(), EmployeeRecyclerAdapter.NavActionListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: EmployeeViewModel

    private val navArgs: EmployeeFragmentArgs by navArgs()

    private lateinit var recyclerAdapter: EmployeeRecyclerAdapter

    private lateinit var dataBinding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(EmployeeViewModel::class.java)

        recyclerAdapter = EmployeeRecyclerAdapter(requireActivity(), this)
        dataBinding.recyclerView.adapter = recyclerAdapter

        viewModel.setSpecialtyId(navArgs.specialtyId)

        viewModel.employeeList.observe(viewLifecycleOwner, Observer { employeeList ->
            employeeList?.let {
                recyclerAdapter.setSpecialties(it)
                dataBinding.isVisibleData = true
            }
        })
    }

    override fun listAction(item: Employee?) {
        if (item != null) {
            val action = EmployeeFragmentDirections.actionEmployeeFragmentToProfileFragment(item.id)
            findNavController().navigate(action)
        } else {
            Toast.makeText(requireActivity(), R.string.error_open_page, Toast.LENGTH_LONG).show()
        }
    }
}