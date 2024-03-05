package com.example.homework1_7.ui.First

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.homework1_7.data.Repository
import com.example.homework1_7.data.Resource
import com.example.homework1_7.model.FirstModel
import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject


@HiltViewModel
class FirstViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    fun getCameras(): LiveData<Resource<FirstModel>> = repository.getCameras()
}