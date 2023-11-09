package com.example.hw_1_4.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.hw_1_4.databinding.FragmentNotificationsBinding
import com.example.hw_1_4.ui.model.Cinema
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener{
            val cinema = Cinema(
                name = binding.etName.text.toString(),
                author = binding.etAuthor.text.toString()
            )

            Firebase.firestore.collection(FirebaseAuth.getInstance().currentUser?.uid.toString())
                .add(cinema)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(),"Данные успешно сохранены", Toast.LENGTH_SHORT).show()
                    binding.etAuthor.text?.clear()
                    binding.etName.text?.clear()
                }
                .addOnFailureListener{
                    Toast.makeText(requireContext(),it.message.toString(), Toast.LENGTH_SHORT).show()
                }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}