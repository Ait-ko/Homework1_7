package com.example.homework1_7.presentation.ui.Second

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.homework1_7.databinding.ItemCameraBinding
import com.example.homework1_7.model.SecondEntity


class DoorAdapter(private val isDoor: Boolean) :
    ListAdapter<SecondEntity, DoorViewHolder>(DoorDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoorViewHolder {
        return DoorViewHolder(
            ItemCameraBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), isDoor
        )

    }

    override fun onBindViewHolder(holder: DoorViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}

class DoorViewHolder(private var binding: ItemCameraBinding, private val isDoor: Boolean) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(camera: SecondEntity) = with(binding) {
        ivLock.visibility = if (isDoor) View.VISIBLE else View.GONE
        iv.load(camera.snapshot)
        tv.text = camera.name
        tv.setOnClickListener {
            if (binding.iv.visibility == View.GONE) {
                slideOutViews(binding.iv)
            } else {
                slideInViews(binding.iv)
            }
        }
    }

    private fun slideOutViews(view: View) {
        val duration = 500L

        view.apply {
            visibility = View.VISIBLE
            translationY = -height.toFloat()

            animate()
                .translationY(0f)
                .setDuration(duration)
                .start()
        }
    }


    private fun slideInViews(view: View) {
        val duration = 500L

        view.apply {
            animate()
                .translationY(-height.toFloat())
                .setDuration(duration)
                .withEndAction {
                    visibility = View.GONE
                    translationY = 0f
                }
                .start()
        }
    }
}

class DoorDiffUtil : DiffUtil.ItemCallback<SecondEntity>() {
    override fun areItemsTheSame(oldItem: SecondEntity, newItem: SecondEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SecondEntity, newItem: SecondEntity): Boolean {
        return oldItem == newItem
    }

}