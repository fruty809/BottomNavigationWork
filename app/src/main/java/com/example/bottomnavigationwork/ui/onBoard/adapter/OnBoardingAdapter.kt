package com.example.bottomnavigationwork.ui.onBoard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnavigationwork.databinding.ItemOnBoardingBinding
import com.example.bottomnavigationwork.ui.model.OnBoard
import com.example.bottomnavigationwork.utils.loadImage

class OnBoardingAdaptor(private val onStartClick: () -> Unit) :
    RecyclerView.Adapter<OnBoardingAdaptor.OnBoardingViewHolder>() {

    private val data = arrayListOf<OnBoard>(
        OnBoard(
            "Test1",
            "Desc1",
            "https://cutewallpaper.org/21/task/How-to-Instantly-Create-Shareable-Task-Lists-No-Signups-.jpg"
        ),
        OnBoard(
            "Test2",
            "Desc2",
            "https://cutewallpaper.org/21/task/How-to-Instantly-Create-Shareable-Task-Lists-No-Signups-.jpg"
        ),
        OnBoard(
            "Test3",
            "Desc3",
            "https://cutewallpaper.org/21/task/How-to-Instantly-Create-Shareable-Task-Lists-No-Signups-.jpg"
        )
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

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class OnBoardingViewHolder(private val binding: ItemOnBoardingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(onBoard: OnBoard) {

            binding.btnStart.isVisible = adapterPosition == 2
            binding.btnSkip.isVisible = adapterPosition != 2

            binding.btnStart.setOnClickListener {
                onStartClick()
            }
            binding.btnSkip.setOnClickListener {
                onStartClick()
            }
            binding.tvTitle.text = onBoard.title
            binding.tvDesk.text = onBoard.desc
            binding.imgBoard.loadImage(onBoard.image.toString())

        }

    }

}