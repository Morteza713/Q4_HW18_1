package com.example.q4_hw18_1.ui.showuser

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.q4_hw18_1.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.io.ByteArrayOutputStream
import com.example.q4_hw18_1.data.Result
import com.example.q4_hw18_1.databinding.UserInfoBinding
import kotlinx.coroutines.launch


class ShowUserFragment():Fragment(R.layout.user_info) {

    private var _binding: UserInfoBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<ShowUserFragmentArgs>()
    private val viewModel by viewModels<ShowViewModel>()
    private var byteArray: ByteArray? = null
    private lateinit var gallery: ActivityResultLauncher<String>
    private lateinit var camera: ActivityResultLauncher<Void?>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = DataBindingUtil.bind(view)

        uploadImage()
        viewModel.getUserListById(args.id)
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.show.collect {
                    binding.userList = it.data
                }
            }
        }

        gallery = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                val imageUri = context?.contentResolver?.openInputStream(uri)?.readBytes()
                byteArray = imageUri
                binding.ivPic.setImageURI(uri)
            }
        }

        camera = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
            if (bitmap != null) {
                byteArray = bitmap.byteArray()
                binding.ivPic.setImageBitmap(bitmap)
            }
        }

        binding.btnUploadPic.setOnClickListener {
            viewModel.uploadPic(args.id, byteArray!!)
        }
    }

    private fun uploadImage() {
        binding.ivPic.setOnClickListener {
            val builder = MaterialAlertDialogBuilder(requireContext())
            builder.setTitle("Upload Image")
            builder.setMessage("Select Camera or Gallery")

            builder.setPositiveButton("Gallery") { dialog, _ ->
                gallery.launch("image/*")
                dialog.dismiss()
            }
            builder.setNeutralButton("Camera") { dialog, _ ->
                camera.launch(null)
                dialog.dismiss()
            }
            builder.create().show()
        }
    }

    private fun Bitmap.byteArray(): ByteArray {
        ByteArrayOutputStream().apply {
            compress(Bitmap.CompressFormat.JPEG, 10, this)
            return toByteArray()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}