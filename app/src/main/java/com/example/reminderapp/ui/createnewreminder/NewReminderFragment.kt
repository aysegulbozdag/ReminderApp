package com.example.reminderapp.ui.createnewreminder

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.reminderapp.data.model.CheckModel
import com.example.reminderapp.data.model.Reminder
import com.example.reminderapp.databinding.FragmentNewReminderBinding
import com.example.reminderapp.ui.createnewreminder.adapter.CheckListAdapter
import com.example.reminderapp.ui.createnewreminder.adapter.PhotoListAdapter
import com.example.reminderapp.ui.createnewreminder.bottomsheetdialog.DateAndTimePicker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewReminderFragment : Fragment(), PhotoListAdapter.OnAddPhotoClickListener {

    private var _binding: FragmentNewReminderBinding? = null
    private val binding get() = _binding!!

    private lateinit var pickImageLauncher: ActivityResultLauncher<Intent>

    private val viewModel: NewReminderViewModel by viewModels()

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

                    if (result.resultCode == RESULT_OK) {
                        val clipData = result.data?.clipData
                        val uris = mutableListOf<Uri>()
                        if (clipData != null) {
                            for (i in 0 until clipData.itemCount) {
                                uris.add(clipData.getItemAt(i).uri)
                            }
                        } else {
                            result.data?.data?.let { uris.add(it) }
                        }
                        binding.rvPhotoList.adapter = PhotoListAdapter(uris, this)
                        binding.rvPhotoList.layoutManager =
                            androidx.recyclerview.widget.LinearLayoutManager(
                                context,
                                androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL,
                                false
                            )
                    }
                }
            }
    }

    private fun onClickListener() {
        binding.txtAddCheckList.setOnClickListener {
            binding.rvCheckList.adapter = checkListAdapter
        }

        binding.txtAddImage.setOnClickListener {
            openGallery()
        }

        binding.txtSetDateTime.setOnClickListener {
            DateAndTimePicker().show(childFragmentManager, "DateAndTimePicker")
        }

        binding.btnCancel.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        binding.btnFinish.setOnClickListener {
            // save reminder
         /*   viewModel.saveReminder(Reminder(
                title = binding.txtTittle.text.toString(),
                checkList = checkListAdapter.getCheckList(),
                photoList = (binding.rvPhotoList.adapter as PhotoListAdapter).getPhotoList(),
                date = binding.txtDate.text.toString(),
                time = binding.txtTime.text.toString(),
                repeat = binding.txtRepeat.text.toString(),
                repeatNo = binding.edtRepeatNo.text.toString().toInt(),
                repeatType = binding.txtRepeatType.text.toString(),
                active = "true"))*/

        }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}