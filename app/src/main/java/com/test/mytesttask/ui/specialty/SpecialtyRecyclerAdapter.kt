package com.test.mytesttask.ui.specialty

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.test.mytesttask.BR
import com.test.mytesttask.R
import com.test.mytesttask.model.Specialty

class SpecialtyRecyclerAdapter internal constructor(
    context: Context,
    private val navActionListener: NavActionListener
) : RecyclerView.Adapter<SpecialtyRecyclerAdapter.SpecialtyViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var specialties = emptyList<Specialty>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialtyViewHolder {
        return SpecialtyViewHolder(
            DataBindingUtil.inflate(inflater, R.layout.item_list_specialty, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SpecialtyViewHolder, position: Int) {
        holder.bind(specialties[position])
    }

    override fun getItemCount() = specialties.size

    internal fun setSpecialties(_specialties: List<Specialty>) {
        specialties = _specialties
        notifyDataSetChanged()
    }

    inner class SpecialtyViewHolder(
        private val binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Specialty) {
            binding.setVariable(BR.specialty, item)
            binding.setVariable(BR.navActionListener, navActionListener)
            binding.executePendingBindings()
        }
    }

    interface NavActionListener {
        fun listAction(item: Specialty?)
    }
}