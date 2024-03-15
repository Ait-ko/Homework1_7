package com.example.homework1_7.presentation.ui.Second
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.homework1_7.App
import com.example.homework1_7.R
import com.example.homework1_7.base.BaseFragment
import com.example.homework1_7.databinding.FragmentSecondBinding
import com.example.homework1_7.model.SecondEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class SecondFragment : BaseFragment() {

    private lateinit var binding: FragmentSecondBinding
    private val viewModel: SecondViewModel by viewModels()
    private val adapter = DoorAdapter(true)
    private var list: List<SecondEntity> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvCameras.adapter = adapter
        CoroutineScope(Dispatchers.IO).launch {
            list = App.db.doorDao().getAll()
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
                val list = it.data
                Log.e("ololo", "List of secondModels: ${list.toString()}")
                CoroutineScope(Dispatchers.IO).launch {
                    App.db.doorDao().clearAll()
                    list.forEach {
                        val door = SecondEntity(
                            favorites = it.favorites,
                            name = it.name,
                            room = it.room,
                            snapshot = it.snapshot
                        )
                        Log.e("ololo", "second: ${door.toString()}")
                        App.db.doorDao().insertDoor(door)

                    }
                    val listDB = App.db.doorDao().getAll()
                    Log.e("ololo", "List of secondEntiies: ${listDB.toString()}")
                    withContext(Dispatchers.Main) {
                        adapter.submitList(listDB)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        )
    }


}