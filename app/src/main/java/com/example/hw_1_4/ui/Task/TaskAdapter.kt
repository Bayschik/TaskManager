package com.example.hw_1_4.ui.Task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.hw_1_4.databinding.ItemTaskBinding

class TaskAdapter( val onclick:(task:Task)->Boolean):Adapter<TaskAdapter.TaskViewHolder>(){
    private val list = arrayListOf<Task>()

    /*fun addTask(task: Task){
        list.add(0, task)
        notifyDataSetChanged()
    }
     */


    fun addTasks(tasks:List<Task>){
        list.clear()
        list.addAll(tasks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        return holder.bind(list[position])
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding):ViewHolder(binding.root){
        fun bind(task: Task){
            binding.tvTitle.text = task.title
            binding.tvDesc.text = task.desc
            itemView.setOnLongClickListener{
                onclick(task)
            }
        }
    }
}