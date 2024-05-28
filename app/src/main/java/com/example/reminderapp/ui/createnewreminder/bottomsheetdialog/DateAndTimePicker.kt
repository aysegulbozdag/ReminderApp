package com.example.reminderapp.ui.createnewreminder.bottomsheetdialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.reminderapp.databinding.DateTimeBottomDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DateAndTimePicker : BottomSheetDialogFragment() {

    private var _binding: DateTimeBottomDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DateTimeBottomDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.timePicker.setIs24HourView(true)
        binding.timePicker.setOnTimeChangedListener { view, hourOfDay, minute ->
            Log.i("TimePicker", "Hour: $hourOfDay, Minute: $minute")
        }

        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            Log.i("CalendarView", "Year: $year, Month: $month, Day: $dayOfMonth")
        }
    }
}