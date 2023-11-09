package com.example.hw_1_4.ui.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hw_1_4.R
import com.example.hw_1_4.databinding.FragmentProfileBinding
import com.example.hw_1_4.loadImage
import com.example.hw_1_4.ui.data.local.Pref
import com.google.android.material.textfield.TextInputEditText

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val pref by lazy {
        Pref(requireContext())
    }

    private val getCommentMedia =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val selectedFileUri = result.data?.data
                pref.saveImage(selectedFileUri.toString())
                binding.imageView.loadImage(selectedFileUri.toString())
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageView.loadImage(pref.getImage().toString())
        binding.profileEt.setText(pref.getName())

        binding.safeButton.setOnClickListener {
            pref.saveName(binding.profileEt.text.toString())
        }
        binding.imageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            getCommentMedia.launch(intent)
        }

        binding.exitIcon.setOnClickListener {
            val alertDialBuilder = AlertDialog.Builder(requireContext())
                .setTitle("Вы действительно хотите выйти?")

                .setPositiveButton("Подтвердить") { _, _ ->
                    findNavController().navigate(R.id.phoneFragment)

                }
                .setNegativeButton("Отмена") { _, _ -> }

            val alertDialog = alertDialBuilder.create()
            alertDialog.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}