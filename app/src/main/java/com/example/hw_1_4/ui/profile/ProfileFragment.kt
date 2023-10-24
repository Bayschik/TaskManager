package com.example.hw_1_4.ui.profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.hw_1_4.databinding.FragmentProfileBinding
import com.example.hw_1_4.loadImage
import com.example.hw_1_4.ui.data.local.Pref

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    private val pref by lazy {
        Pref(requireContext())
    }

    private val getCommentMedia = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val selectedFileUri = result.data?.data
            pref.saveImage(selectedFileUri.toString())
            binding.imageView.loadImage(selectedFileUri.toString())
        }
    }

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
        binding.imageView.loadImage(pref.getImage().toString())

        binding.safeButton.setOnClickListener {
            pref.saveName(binding.profileEt.text.toString())
        }
        binding.imageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            getCommentMedia.launch(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}