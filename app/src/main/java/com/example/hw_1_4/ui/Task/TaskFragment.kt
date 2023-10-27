package com.example.hw_1_4.ui.Task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hw_1_4.App
import com.example.hw_1_4.databinding.FragmentTaskBinding
import com.example.hw_1_4.ui.Task.Task

class TaskFragment : Fragment() {
    private lateinit var binding: FragmentTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentTaskBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener{
            val data = Task(
                title = binding.etTitle.text.toString(),
                desc = binding.etDesc.text.toString()
            )

            //setFragmentResult(RESULT_KEY, bundleOf(TASK_KEY to data))

            //
            App.db.taskDao().insert(data)
            //
            findNavController().navigateUp()

        }

    }


    companion object{
        const val RESULT_KEY = "result_key"
        const val TASK_KEY = "task_key"
    }

}