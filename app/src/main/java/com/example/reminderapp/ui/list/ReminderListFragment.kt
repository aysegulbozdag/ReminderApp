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
import com.example.reminderapp.R
import com.example.reminderapp.databinding.FragmentListBinding
import com.example.reminderapp.ui.list.adapter.ReminderListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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