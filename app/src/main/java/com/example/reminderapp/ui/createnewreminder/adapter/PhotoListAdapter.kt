package com.example.reminderapp.ui.createnewreminder.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.reminderapp.R
import com.example.reminderapp.databinding.ItemPhotoListBinding

class PhotoListAdapter(
    private var photoList: MutableList<Uri>,
    private val onAddPhotoClickListener: OnAddPhotoClickListener? = null
) :
    Adapter<RecyclerView.ViewHolder>() {

    private val ITEM_VIEW_TYPE_ITEM = 0
    private val ITEM_VIEW_TYPE_FOOTER = 1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_ITEM -> {
                val binding =
                    ItemPhotoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                PhotoViewHolder(binding)
            }

            else -> {
                val binding =
                    ItemPhotoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                FooterViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ITEM_VIEW_TYPE_ITEM) {
            (holder as PhotoViewHolder).bind(photoList[position])
        } else if (holder is FooterViewHolder) {
            holder.bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == photoList.size) {
            ITEM_VIEW_TYPE_FOOTER
        } else {
            ITEM_VIEW_TYPE_ITEM
        }
    }

    override fun getItemCount(): Int = photoList.size + 1

    inner class PhotoViewHolder(private val binding: ItemPhotoListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(uri: Uri) {
            binding.imageViewer.setImageURI(uri)
        }
    }

    inner class FooterViewHolder(private val binding: ItemPhotoListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.imageViewer.setImageResource(R.drawable.outline_add_photo_alternate_24)
            binding.imageViewer.alpha = 0.3f
            binding.imageViewer.setOnClickListener {
                onAddPhotoClickListener?.onAddPhotoClick()
            }
        }
    }

    fun getPhotoList(): List<Uri>? = photoList.toList()


    interface OnAddPhotoClickListener {
        fun onAddPhotoClick()
    }

}