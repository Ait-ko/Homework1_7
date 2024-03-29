package com.example.homework1_7.base

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.example.homework1_7.data.Resource

abstract class BaseFragment : Fragment() {

    fun <T> LiveData<Resource<T>>.stateHandler(
        success: (data: T) -> Unit,
        state: ((res: Resource<T>) -> Unit)? = null
    ) {
        observe(this@BaseFragment) { res ->
            state?.invoke(res)
            when (res) {
                is Resource.Error -> {
                    Toast.makeText(requireContext(), res.message.toString(), Toast.LENGTH_SHORT)
                        .show()

                }

                is Resource.Loading -> {}
                is Resource.Success -> {
                    if (res.data != null) {
                        success(res.data)
                    }
                }

                else -> {
                    Toast.makeText(requireContext(), res.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

}