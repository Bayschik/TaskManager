package com.example.hw_1_4.ui.Task

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.hw_1_4.databinding.ItemTaskBinding
import com.example.hw_1_4.ui.model.Task

class TaskAdapter(
    val onclick:(task: Task)->Boolean,
    val updateClick:(task: Task)-> Boolean):Adapter<TaskAdapter.TaskViewHolder>(){
    private val list = arrayListOf<Task>()

    @SuppressLint("NotifyDataSetChanged")
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
        holder.bind(list[position])
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
            itemView.setBackgroundColor(if (adapterPosition % 2 == 0) Color.BLACK else Color.WHITE)
            binding.tvDesc.setTextColor(if (adapterPosition % 2 == 0) Color.WHITE else Color.BLACK)
            binding.tvTitle.setTextColor(if (adapterPosition % 2 == 0) Color.WHITE else Color.BLACK)
        }
    }
}