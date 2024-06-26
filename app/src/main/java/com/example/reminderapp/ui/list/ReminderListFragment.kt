package com.example.reminderapp.ui.list

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.reminderapp.R
import com.example.reminderapp.databinding.FragmentListBinding
import com.example.reminderapp.services.ReminderWorker
import com.example.reminderapp.services.createNotificationChannel
import com.example.reminderapp.ui.list.adapter.ReminderListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class ReminderListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ReminderListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClickListener()
       /* this.context?.let { createNotificationChannel(it) }
        val workRequest = OneTimeWorkRequestBuilder<ReminderWorker>()
            .setInitialDelay(Date().time, TimeUnit.MILLISECONDS)
            .build()

        this.context?.let { WorkManager.getInstance(it).enqueue(workRequest) }*/
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.remindersState.collect {

                if (it.isEmpty()) {
                    binding.btnCreateReminder.visibility = View.VISIBLE
                    binding.rvReminderList.visibility = View.GONE
                } else {
                    binding.btnCreateReminder.visibility = View.GONE
                    binding.rvReminderList.visibility = View.VISIBLE
                    binding.rvReminderList.adapter = ReminderListAdapter(it)
                }
            }
        }

    }


    private fun onClickListener() = with(binding) {

        btnCreateReminder.setOnClickListener {
            openNewReminderFragment()
        }

        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.addReminder -> {
                    openNewReminderFragment()
                    true
                }

                R.id.searchReminder ->
                    true

                else ->
                    false

            }
        }

    }

    private fun openNewReminderFragment() {
        findNavController().navigate(R.id.action_ListFragment_to_NewFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}