package com.example.bottomnavigationwork.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bottomnavigationwork.R
import com.example.bottomnavigationwork.databinding.FragmentHomeBinding
import com.example.bottomnavigationwork.ui.home.adapter.TaskAdapter
import com.example.bottomnavigationwork.ui.model.Task

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var adapter:TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fab.setOnClickListener{
findNavController().navigate(R.id.taskFragment)
        }
        adapter= TaskAdapter()
        setFragmentResultListener(RESULT_REQUEST_KEY){ key, bundle ->
            val result = bundle.getSerializable(TASK_KEY) as Task
            Log.e("ololoo" ,"onViewCreated:" + result)
        }
        binding.recyclerView.adapter = adapter
    }
    companion object{
        const val RESULT_REQUEST_KEY="requestKey"
        const val TASK_KEY="task_key"
    }



}