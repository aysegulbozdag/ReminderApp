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
import com.example.reminderapp.ui.createnewreminder.adapter.CheckListAdapter
import com.example.reminderapp.data.CheckModel
import com.example.reminderapp.databinding.FragmentNewReminderBinding


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class NewReminderFragment : Fragment() {

    private var _binding: FragmentNewReminderBinding? = null
    private val binding get() = _binding!!

    private val PICK_IMAGE_REQUEST = 1
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



       binding.txtAddCheckList.setOnClickListener {
           binding.rvCheckList.adapter = CheckListAdapter(mutableListOf(CheckModel()))
       }

        binding.txtAddImage.setOnClickListener {
            openGallery()
        }

        pickImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
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
                  uris.joinToString("\n")
                }
            }
        }

    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
            type = "image/*"
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false)
        }
        pickImageLauncher.launch(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}