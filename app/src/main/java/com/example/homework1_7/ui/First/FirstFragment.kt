package com.example.homework1_7.ui.First

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.homework1_7.App
import com.example.homework1_7.base.BaseFragment
import com.example.homework1_7.databinding.FragmentFirstBinding
import com.example.homework1_7.model.FirstEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class FirstFragment : BaseFragment() {

    private lateinit var binding: FragmentFirstBinding
    private val viewModel: FirstViewModel by viewModels()
    private val adapter = FirstAdapter(false)
    private var list: List<FirstEntity> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvCameras.adapter = adapter
        CoroutineScope(Dispatchers.IO).launch {
            list = App.db.cameraDao().getAll()
            withContext(Dispatchers.Main) {
                if (list.isEmpty()) {
                    getData()
                } else {
                    adapter.submitList(list)
                    adapter.notifyDataSetChanged()
                }
            }
        }


        binding.swiperefresh.setOnRefreshListener {
            getData()
        }

    }

    fun getData() {
        viewModel.getCameras().stateHandler(
            success = { it ->
                val list = it.data.cameras
                Log.e("ololo", "List of firstModels: ${list.toString()}")
                CoroutineScope(Dispatchers.IO).launch {
                    App.db.cameraDao().clearAll()
                    list.forEach {
                        val camera = FirstEntity(
                            favorites = it.favorites,
                            name = it.name,
                            rec = it.rec,
                            room = it.room,
                            snapshot = it.snapshot
                        )
                        Log.e("ololo", "first : ${camera.toString()}")
                        App.db.cameraDao().insertCamera(camera)
                    }
                    withContext(Dispatchers.Main) {
                        val listDB = App.db.cameraDao().getAll()
                        Log.e("ololo", "List of firstEntiies: ${listDB.toString()}")
                        adapter.submitList(listDB)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        )
    }


    }
