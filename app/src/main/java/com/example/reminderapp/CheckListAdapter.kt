package com.example.reminderapp

import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.reminderapp.databinding.RvCheckListBinding

class CheckListAdapter(var checkList: MutableList<CheckModel>) : Adapter<CheckListAdapter.ViewHolder>() {

    private lateinit var binding: RvCheckListBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckListAdapter.ViewHolder {
        binding = RvCheckListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CheckListAdapter.ViewHolder, position: Int) {
        holder.bind(checkList[position])
    }

    override fun getItemCount(): Int = checkList.size

    inner class ViewHolder(binding: RvCheckListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(checkModel: CheckModel) {
            checkModel.title = binding.editTextText.text.toString()

            binding.editTextText.setOnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    checkList.add(CheckModel())
                    notifyDataSetChanged()
                    return@setOnKeyListener true
                }
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    if (checkList.size > 1) {
                        checkList = checkList.subList(0, checkList.size - 1)
                        notifyDataSetChanged()
                    }
                    return@setOnKeyListener true
                }
                false
            }
        }
    }
}