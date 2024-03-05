package com.example.homework1_7

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.homework1_7.ui.First.FirstFragment
import com.example.homework1_7.ui.Second.SecondFragment


class VPAdapter (
    private val fragmentManager: FragmentManager,
    private val lifecycle: Lifecycle
    ) : FragmentStateAdapter(fragmentManager, lifecycle)
    {
        private val fragments = listOf(
            FirstFragment(), SecondFragment()
        )

        override fun getItemCount() = fragments.size

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> FirstFragment()
                1 -> SecondFragment()
                else -> FirstFragment()
            }
        }
    }