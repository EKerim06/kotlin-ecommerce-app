package com.example.e_commerce_app_2.Adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_commerce_app_2.Model.BrandModel
import com.example.e_commerce_app_2.R
import com.example.e_commerce_app_2.databinding.ViewholderBrandBinding

class BrandAdapter(val items: MutableList<BrandModel>) :
    RecyclerView.Adapter<BrandAdapter.ViewHolder>() {

    private var selectedPostition = -1

    private var lastSelectedPosition = -1

    private lateinit var context: Context


    class ViewHolder(val binding: ViewholderBrandBinding) : RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandAdapter.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: BrandAdapter.ViewHolder, position: Int) {
        val item = items[position]

        holder.binding.title.text = item.title

        Glide
            .with(holder.itemView.context)
            .load(item.picUrl)
            .into(holder.binding.pic)


        holder.binding.root.setOnClickListener {

            lastSelectedPosition = selectedPostition
            selectedPostition = position

            notifyItemChanged(lastSelectedPosition)

            notifyItemChanged(selectedPostition)
        }

        holder.binding.title.setTextColor(context.resources.getColor(R.color.white))

        if (selectedPostition == position) {

            holder.binding.pic.setBackgroundResource(0)

            holder.binding.mainLayout.setBackgroundResource(R.drawable.purple_bg)

            ImageViewCompat.setImageTintList(
                holder.binding.pic,
                ColorStateList.valueOf(context.getColor(R.color.white))
            )

            holder.binding.title.visibility = View.VISIBLE

        } else {
            holder.binding.pic.setBackgroundResource(R.drawable.grey_bg)
            holder.binding.mainLayout.setBackgroundResource(0)
            ImageViewCompat.setImageTintList(holder.binding.pic,ColorStateList.valueOf(context.getColor(R.color.black)))
            holder.binding.title.visibility = View.GONE
        }


    }

    override fun getItemCount(): Int = items.size

}