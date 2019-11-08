package com.test.mytesttask.ui.employee

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.test.mytesttask.BR
import com.test.mytesttask.R
import com.test.mytesttask.model.Employee

class EmployeeRecyclerAdapter internal constructor(
    context: Context,
    private val navActionListener: NavActionListener
) : RecyclerView.Adapter<EmployeeRecyclerAdapter.EmployeeViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var employees = emptyList<Employee>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        return EmployeeViewHolder(
            DataBindingUtil.inflate(inflater, R.layout.item_list_employee, parent, false)
        )
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bind(employees[position])
    }

    override fun getItemCount() = employees.size

    internal fun setSpecialties(_employees: List<Employee>) {
        employees = _employees
        notifyDataSetChanged()
    }

    inner class EmployeeViewHolder(
        private val binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Employee) {
            binding.setVariable(BR.employee, item)
            binding.setVariable(BR.navActionListener, navActionListener)
            binding.executePendingBindings()
        }
    }

    interface NavActionListener {
        fun listAction(item: Employee?)
    }
}