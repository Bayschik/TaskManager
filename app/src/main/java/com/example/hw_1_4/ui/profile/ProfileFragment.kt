package com.example.hw_1_4.ui.profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.hw_1_4.databinding.FragmentProfileBinding
import com.example.hw_1_4.ui.data.local.Pref

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null

    private val GALLERY_REQUEST_CODE = 123

    private val pref by lazy {
        Pref(requireContext())
    }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.profileEt.setText(pref.getName())

        binding.safeButton.setOnClickListener {
            pref.saveName(binding.profileEt.text.toString())
        }

        binding.imageView.setImageURI(pref.getImage().toString().toUri())

        binding.imageView.setOnClickListener {
            val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val selectedImageUri = data?.data
            pref.saveImage(selectedImageUri.toString())
            if (selectedImageUri != null) {
                val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("image_uri", selectedImageUri.toString())
                editor.apply()

                //val savedImageUri = sharedPreferences.getString("image_uri", null)
                Glide.with(this).load(selectedImageUri).into(binding.imageView)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}