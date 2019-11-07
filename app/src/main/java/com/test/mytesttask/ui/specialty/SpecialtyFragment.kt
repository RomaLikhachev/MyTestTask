package com.test.mytesttask.ui.specialty

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
import com.test.mytesttask.R
import com.test.mytesttask.databinding.FragmentListBinding
import com.test.mytesttask.model.Specialty
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SpecialtyFragment : DaggerFragment(), SpecialtyRecyclerAdapter.NavActionListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: SpecialtyViewModel

    private lateinit var specialtyRecyclerAdapter: SpecialtyRecyclerAdapter

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
            .get(SpecialtyViewModel::class.java)

        specialtyRecyclerAdapter = SpecialtyRecyclerAdapter(requireActivity(), this)
        dataBinding.recyclerView.adapter = specialtyRecyclerAdapter

        viewModel.specialtyList.observe(viewLifecycleOwner, Observer { specialties ->
            specialties?.let {
                specialtyRecyclerAdapter.setSpecialties(specialties)
            }
            dataBinding.isVisibleData = true
        })
    }

    override fun listAction(item: Specialty?) {
        if (item != null) {
            val action = SpecialtyFragmentDirections.actionSpecialtyFragmentToEmployeeFragment(item.id)
            findNavController().navigate(action)
        } else {
            Toast.makeText(requireActivity(), R.string.error_open_page, Toast.LENGTH_LONG).show()
        }
    }
}
