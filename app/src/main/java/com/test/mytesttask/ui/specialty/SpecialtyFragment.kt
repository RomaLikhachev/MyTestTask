package com.test.mytesttask.ui.specialty

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.test.mytesttask.R

class SpecialtyFragment : Fragment() {

    companion object {
        fun newInstance() = SpecialtyFragment()
    }

    private lateinit var viewModel: SpecialtyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SpecialtyViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
