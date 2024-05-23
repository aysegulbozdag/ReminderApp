package com.example.reminderapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.reminderapp.databinding.FragmentNewReminderBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class NewReminderFragment : Fragment() {

    private var _binding: FragmentNewReminderBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNewReminderBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       binding.txtAddCheckList.setOnClickListener {
           binding.rvCheckList.adapter = CheckListAdapter(mutableListOf(CheckModel()))
       }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}