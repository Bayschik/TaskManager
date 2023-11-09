package com.example.hw_1_4.ui.onBoarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.hw_1_4.databinding.ItemOnBoardingBinding
import com.example.hw_1_4.loadImage
import com.example.hw_1_4.ui.model.OnBoardingModel

class OnBoardingAdapter(private val onClick: () -> Unit) :
    Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {
    private val list = arrayListOf(
        OnBoardingModel("Shopping Place", "The bustling mall offered a wide variety \n of shops for all your shopping needs.", "https://clipart-library.com/img/2083233.png"),
        OnBoardingModel("Shopping on the way", "Stopped for some roadside \nshopping during our journey.", "https://cdn-icons-png.flaticon.com/512/3578/3578303.png"),
        OnBoardingModel("Pay on Delivery", "I prefer pay on delivery \nwhen shopping online for convenience.", "https://static.tildacdn.com/tild6436-6234-4665-b431-383530663135/visa-mastercard-logo.png")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnBoardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class OnBoardingViewHolder(private val binding: ItemOnBoardingBinding) :
        ViewHolder(binding.root) {
        fun bind(onBoarding: OnBoardingModel) = with(binding) {
            tvTitle.text = onBoarding.title
            tvDesc.text = onBoarding.desc
            tvSkip.isVisible = adapterPosition != list.lastIndex
            btnStart.isVisible = adapterPosition == list.lastIndex

            btnStart.setOnClickListener {onClick()}

            tvSkip.setOnClickListener {onClick()}

            ivBoard.loadImage(onBoarding.image.toString())
        }

    }
}