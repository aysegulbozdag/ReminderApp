package com.example.reminderapp.ui.createnewreminder

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.reminderapp.data.model.CheckModel
import com.example.reminderapp.data.model.Reminder
import com.example.reminderapp.databinding.FragmentNewReminderBinding
import com.example.reminderapp.services.ReminderWorker
import com.example.reminderapp.utility.createNotificationChannel
import com.example.reminderapp.ui.createnewreminder.adapter.CheckListAdapter
import com.example.reminderapp.ui.createnewreminder.adapter.PhotoListAdapter
import com.example.reminderapp.ui.createnewreminder.bottomsheetdialog.DateAndTimePicker
import com.example.reminderapp.utility.intervalDateFormatted
import com.example.reminderapp.utility.timeFormat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.lang.System.currentTimeMillis
import java.util.Date
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class NewReminderFragment : Fragment(), PhotoListAdapter.OnAddPhotoClickListener,
    DateAndTimePicker.OnDateSelectedListener {

    private var _binding: FragmentNewReminderBinding? = null
    private val binding get() = _binding!!

    private lateinit var pickImageLauncher: ActivityResultLauncher<Intent>

    private val viewModel: NewReminderViewModel by viewModels()

    private var selectedDate: Date? = null

    private val checkListAdapter: CheckListAdapter by lazy {
        CheckListAdapter(
            mutableListOf(
                CheckModel()
            )
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNewReminderBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClickListener()
        pickImageGallery()

    }

    private fun pickImageGallery() {
        pickImageLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    /*
                     tekli seçim
                     val selectedImage: Uri? = result.data?.data
                     binding.imgPhoto.setImageURI(selectedImage)*/

                        val clipData = result.data?.clipData
                        val uris = mutableListOf<Uri>()
                        if (clipData != null) {
                            for (i in 0 until clipData.itemCount) {
                                uris.add(clipData.getItemAt(i).uri)
                            }
                        } else {
                            result.data?.data?.let { uris.add(it) }
                        }
                    if (uris.isEmpty().not())
                        setAdapter(uris)
                    }
            }
    }

    private fun setAdapter(uris: MutableList<Uri>) {
        binding.rvPhotoList.adapter = PhotoListAdapter(uris, this)

    }

    private fun onClickListener()= with(binding) {



       txtTittle.doAfterTextChanged {
           viewModel.setTitle(txtTittle.text.toString())
       }

       viewLifecycleOwner.lifecycleScope.launch {
           viewModel.btnIsEnabled.collect{
               if (it){
                   btnFinish.isEnabled = true
                   btnFinish.alpha = 0.9F
               }
           }
       }

        binding.txtAddCheckList.setOnClickListener {
            binding.rvCheckList.adapter = checkListAdapter
        }

        binding.txtAddImage.setOnClickListener {
            openGallery()
        }

        binding.txtSetDateTime.setOnClickListener {
            val dateAndTimePicker = DateAndTimePicker()
            dateAndTimePicker.setListener(this@NewReminderFragment)
            dateAndTimePicker.show(childFragmentManager, "DateAndTimePicker")
        }

        binding.btnCancel.setOnClickListener {
            onBackPressed()
        }

        binding.btnFinish.setOnClickListener {
            viewModel.saveReminder(
                Reminder(
                    title = binding.txtTittle.text.toString(),
                    checkList = checkListAdapter.getCheckModelList(),
                    photoList = (binding.rvPhotoList.adapter as? PhotoListAdapter)?.getPhotoList(),
                    date = selectedDate ?: Date(),
                    active = "true"
                )
            )

            scheduleWork()
            onBackPressed()
        }
    }

    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    private fun scheduleWork() {
        val data = Data.Builder()
            .putString("title", binding.txtTittle.text.toString())
            .putString(
                "message",
                " ${selectedDate?.intervalDateFormatted()}, ${selectedDate?.timeFormat()}"
            )
            .build()

        val customTime = selectedDate?.time
        val currentTime = currentTimeMillis()
        val delay = customTime?.minus(currentTime)

        this.context?.let { createNotificationChannel(it) }

        val workRequest = OneTimeWorkRequestBuilder<ReminderWorker>()
            .setInputData(data)
            .setInitialDelay(delay!!, TimeUnit.MILLISECONDS)
            .build()

        this.context?.let { WorkManager.getInstance(it).enqueue(workRequest) }
    }

    private fun openGallery() {
        val intent =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
                type = "image/*"
                putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            }
        pickImageLauncher.launch(intent)
    }

    override fun onAddPhotoClick() {
        openGallery()
    }

    override fun onDateSelected(date: Date) {
        selectedDate = date
        viewModel.setSelectedDate(date)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}