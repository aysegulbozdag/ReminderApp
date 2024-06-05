package com.example.reminderapp.ui.createnewreminder.bottomsheetdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.reminderapp.databinding.DateTimeBottomDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Date

class DateAndTimePicker : BottomSheetDialogFragment() {

    private var _binding: DateTimeBottomDialogBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DateTimePickerViewModel by viewModels()

    private var listener: OnDateSelectedListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DateTimeBottomDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onTimeChangedListener()
        onDateChangedListener()
        onClickListener()
    }

    private fun onTimeChangedListener() {
        binding.timePicker.setIs24HourView(true)
        binding.timePicker.setOnTimeChangedListener { view, hourOfDay, minute ->
            viewModel.onTimeChangedListener(hourOfDay, minute)
        }
    }

    private fun onDateChangedListener() {
        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            viewModel.onDateChangedListener(year, month, dayOfMonth)
        }
    }

    private fun onClickListener() {
        binding.btnFinish.setOnClickListener {
            listener?.onDateSelected(viewModel.date)
            dismiss()
        }

        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setListener(listener: OnDateSelectedListener) {
        this.listener = listener
    }

    interface OnDateSelectedListener {
        fun onDateSelected(date: Date)
    }
}