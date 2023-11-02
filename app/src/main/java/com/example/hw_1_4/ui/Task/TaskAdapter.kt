package com.example.hw_1_4.ui.Task

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.hw_1_4.R
import com.example.hw_1_4.databinding.ItemTaskBinding

class TaskAdapter(
    val onclick:(task:Task)->Boolean,
    val updateClick:(task: Task)-> Boolean):Adapter<TaskAdapter.TaskViewHolder>(){
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

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(list[position])

        if (position % 2 == 0){
            holder.itemView.setBackgroundColor(R.color.black)
        } else {
            holder.itemView.setBackgroundColor(R.color.white)
        }
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding):ViewHolder(binding.root){
        fun bind(task: Task){
            binding.tvTitle.text = task.title
            binding.tvDesc.text = task.desc
            itemView.setOnLongClickListener{
                onclick(task)
            }
            itemView.setOnClickListener{
                updateClick(task)
            }
        }
    }
}