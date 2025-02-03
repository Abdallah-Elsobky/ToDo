package com.example.todo.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.todo.R
import com.example.todo.databinding.SpinnerItemBinding
import com.example.todo.model.SpinnerItem

class SpinnerAdapter(context: Context, items: List<SpinnerItem>) :
    ArrayAdapter<SpinnerItem>(context, R.layout.spinner_item, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, parent)
    }

    private fun createView(position: Int, parent: ViewGroup): View {
        val binding = SpinnerItemBinding.inflate(LayoutInflater.from(context), parent, false)

        val item = getItem(position)
        item?.let {
            binding.image.setImageResource(it.img)
            binding.title.text = it.title
        }

        return binding.root
    }
}