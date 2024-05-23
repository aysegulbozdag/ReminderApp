package com.example.reminderapp

import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.reminderapp.databinding.RvCheckListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CheckListAdapter(var checkList: MutableList<CheckModel>) :
    Adapter<CheckListAdapter.ViewHolder>() {

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