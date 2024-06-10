package com.example.reminderapp.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.reminderapp.data.model.Reminder
import com.example.reminderapp.databinding.DateHeaderItemBinding
import com.example.reminderapp.databinding.ReminderItemListBinding
import java.util.Date

class ReminderListAdapter(private val reminderList: List<ListItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val VIEW_TYPE_DATE_HEADER = 0
        private const val VIEW_TYPE_ITEM = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (reminderList[position]) {
            is ListItem.ReminderItem-> VIEW_TYPE_ITEM
            is ListItem.DateHeader-> VIEW_TYPE_DATE_HEADER
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_DATE_HEADER -> {
                val binding = DateHeaderItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                DateHeaderViewHolder(binding)
            }

            VIEW_TYPE_ITEM -> {
                val binding = ReminderItemListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ReminderViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = reminderList[position]) {
            is ListItem.ReminderItem -> {
                (holder as ReminderViewHolder).bind(item.reminder)
            }

            is ListItem.DateHeader -> {
                (holder as DateHeaderViewHolder).bind(item.date)

            }
        }
    }

    override fun getItemCount(): Int = reminderList.size


    inner class ReminderViewHolder(private val binding: ReminderItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(reminder: Reminder) {
            binding.reminderTitle.text = reminder.title
            binding.reminderDescription.text = reminder.date.toString()
        }
    }

    inner class DateHeaderViewHolder(private val binding: DateHeaderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(date: Date) {
            binding.dateHeader.text = date.toString()
        }
    }
}