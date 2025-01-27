package com.example.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todo.databinding.CalenderItemBinding
import com.example.todo.model.Calender

class CalenderAdapter(val days: ArrayList<Calender>) :
    Adapter<CalenderAdapter.CalenderViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CalenderAdapter.CalenderViewHolder {
        val binding =
            CalenderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CalenderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalenderAdapter.CalenderViewHolder, position: Int) {
        val day = days[position]
        holder.bind(day, position)
    }

    override fun getItemCount() = days.size

    class CalenderViewHolder(val binding: CalenderItemBinding) : ViewHolder(binding.root) {
        fun bind(day: Calender, position: Int) {
            binding.dayName.text = day.dayName
            binding.dayNumber.text = day.dayNumber
        }
    }

}