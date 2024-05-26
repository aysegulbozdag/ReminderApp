package com.example.reminderapp.ui.createnewreminder.adapter

import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.reminderapp.data.CheckModel
import com.example.reminderapp.databinding.RvCheckListBinding

class PhotoListAdapter(var checkList: MutableList<CheckModel>) :
    Adapter<PhotoListAdapter.ViewHolder>() {

    private lateinit var binding: RvCheckListBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = RvCheckListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(checkList[position])
    }

    override fun getItemCount(): Int = checkList.size

    inner class ViewHolder(binding: RvCheckListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(checkModel: CheckModel) {
            checkModel.title = binding.editTextText.text.toString()

            binding.editTextText.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    checkList.add(adapterPosition + 1, CheckModel())
                    v.clearFocus()
                    binding.editTextText.post {
                        binding.editTextText.requestFocus()
                    }
                    notifyItemInserted(adapterPosition + 1)
                    return@setOnKeyListener true
                }
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    if (checkList.size > 0) {
                        checkList.removeAt(adapterPosition)
                        notifyItemRemoved(adapterPosition)
                    }
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }
    }
}