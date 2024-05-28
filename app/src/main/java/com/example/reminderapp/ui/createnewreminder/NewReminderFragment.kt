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
import com.example.reminderapp.data.CheckModel
import com.example.reminderapp.databinding.FragmentNewReminderBinding
import com.example.reminderapp.ui.createnewreminder.adapter.CheckListAdapter
import com.example.reminderapp.ui.createnewreminder.adapter.PhotoListAdapter

class NewReminderFragment : Fragment(), PhotoListAdapter.OnAddPhotoClickListener {

    private var _binding: FragmentNewReminderBinding? = null
    private val binding get() = _binding!!

    private lateinit var pickImageLauncher: ActivityResultLauncher<Intent>


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
            binding.rvCheckList.adapter = CheckListAdapter(mutableListOf(CheckModel()))
        }

        binding.txtAddImage.setOnClickListener {
            openGallery()
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