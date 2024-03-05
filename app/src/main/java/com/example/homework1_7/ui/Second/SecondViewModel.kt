package com.example.homework1_7.ui.Second

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.homework1_7.data.Repository
import com.example.homework1_7.data.Resource
import com.example.homework1_7.model.SecondModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SecondViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    fun getCameras(): LiveData<Resource<SecondModel>> = repository.getDoors()
}