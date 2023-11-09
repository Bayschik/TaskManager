package com.example.hw_1_4.ui.Task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hw_1_4.App
import com.example.hw_1_4.R
import com.example.hw_1_4.databinding.FragmentTaskBinding
import com.example.hw_1_4.ui.model.Task

class TaskFragment : Fragment() {
    private lateinit var binding: FragmentTaskBinding
    private var task: Task? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentTaskBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        task = arguments?.getSerializable("key1") as Task?
        binding.etDesc.setText(task?.desc)
        binding.etTitle.setText(task?.title)

        if (task != null){
            binding.btnSave.text = getString(R.string._update)
            binding.btnSave.setOnClickListener {
                val updateTask = task?.copy(
                    title = binding.etTitle.text.toString(),
                    desc = binding.etDesc.text.toString()
                )
                App.db.taskDao().update(updateTask!!)
                findNavController().navigateUp()
            }
        } else {
            binding.btnSave.setOnClickListener {
                val data = Task(
                    title = binding.etTitle.text.toString(),
                    desc = binding.etDesc.text.toString()
                )
                App.db.taskDao().insert(data)
                findNavController().navigateUp()
            }
        }
    }
}