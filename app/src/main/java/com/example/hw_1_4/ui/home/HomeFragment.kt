package com.example.hw_1_4.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hw_1_4.App
import com.example.hw_1_4.R
import com.example.hw_1_4.databinding.FragmentHomeBinding
import com.example.hw_1_4.ui.model.Task
import com.example.hw_1_4.ui.Task.TaskAdapter

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val adapter = TaskAdapter(this::onLongClick, this::onUpdateClick)
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tasksRv.adapter = adapter
        val data = App.db.taskDao().getAll()
        adapter.addTasks(data)

        binding.FAB.setOnClickListener{
            findNavController().navigate(R.id.taskFragment)
        }
    }
    private fun onLongClick(position: Task): Boolean {
        val alertDialBuilder = AlertDialog.Builder(requireContext())
            .setTitle("Удаление item")
            .setMessage("Удалить текс?")

            .setPositiveButton("Подтвердить") { _, _ ->
                App.db.taskDao().delete(position)
                findNavController().navigate(R.id.navigation_home)

            }

                .setNegativeButton("Отмена"){_,_ ->
                    findNavController().navigateUp()
                }

        val alertDialog = alertDialBuilder.create()
        alertDialog.show()
        return true
    }

    private fun onUpdateClick(task: Task):Boolean{
       val bundle = Bundle()
        bundle.putSerializable("key1", task)
        findNavController().navigate(R.id.taskFragment,bundle)
        return true
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}