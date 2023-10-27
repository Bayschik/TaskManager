package com.example.hw_1_4.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.hw_1_4.App
import com.example.hw_1_4.R
import com.example.hw_1_4.databinding.FragmentHomeBinding
import com.example.hw_1_4.ui.Task.Task
import com.example.hw_1_4.ui.Task.TaskAdapter
import com.example.hw_1_4.ui.Task.TaskFragment.Companion.RESULT_KEY
import com.example.hw_1_4.ui.Task.TaskFragment.Companion.TASK_KEY
import com.example.hw_1_4.ui.data.local.db.AppDatabase
import com.example.hw_1_4.ui.data.local.db.TaskDao

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val adapter = TaskAdapter(this::onLongClick)

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tasksRv.adapter = adapter

        //
        /*
        setFragmentResultListener(RESULT_KEY){_,bundle ->
            val data = bundle.getSerializable(TASK_KEY) as Task
            adapter.addTask(data)
        }
         */
        //
        val data = App.db.taskDao().getAll()
        adapter.addTasks(data)
        //Log.e("TAG", "onViewCreated " + data)

        binding.FAB.setOnClickListener{
            findNavController().navigate(R.id.taskFragment)
        }
    }
    private fun onLongClick(position: Task): Boolean {
        val alertDialBuilder = AlertDialog.Builder(requireContext())
        alertDialBuilder.setTitle("Удаление item")
        alertDialBuilder.setMessage("Удалить текс?")

        alertDialBuilder.setPositiveButton("Подтвердить"){dialog,which ->
            App.db.taskDao().delete(position)
            findNavController().navigate(R.id.navigation_home)
        }

        alertDialBuilder.setNegativeButton("Отмена"){dialog,which ->
            findNavController().navigateUp()
        }

        val alertDialog = alertDialBuilder.create()
        alertDialog.show()
        return true
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}