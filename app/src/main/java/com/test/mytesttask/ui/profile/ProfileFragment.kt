package com.test.mytesttask.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.test.mytesttask.R
import com.test.mytesttask.databinding.FragmentProfileBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ProfileViewModel

    private val navArgs: ProfileFragmentArgs by navArgs()

    private lateinit var dataBinding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(ProfileViewModel::class.java)

        viewModel.setEmployeeId(navArgs.employeeId)

        viewModel.employee.observe(viewLifecycleOwner, Observer { employee ->
            employee?.let {
                dataBinding.employee = employee
            }
        })

        viewModel.specialtiesTitle.observe(viewLifecycleOwner, Observer {  specialtiesTitle ->
            specialtiesTitle?.let {
                dataBinding.specialtiesTitle = specialtiesTitle
            }
        })
    }
}
