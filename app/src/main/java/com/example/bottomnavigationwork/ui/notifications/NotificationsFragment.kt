package com.example.bottomnavigationwork.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bottomnavigationwork.databinding.FragmentNotificationsBinding
import com.example.bottomnavigationwork.model.Quote
import com.example.bottomnavigationwork.ui.notifications.adapter.QuoteAdapter
import com.geektech.taskmanager.databinding.FragmentNotificationsBinding
import com.google.firebase.firestore.FirebaseFirestore

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: FirebaseFirestore
    private val adapter = QuoteAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = FirebaseFirestore.getInstance()
        binding.recyclerView.adapter = adapter
        db.collection(FirebaseAuth.getInstance().currentUser?.uid.toString()).get()
            .addOnSuccessListener {
                val data = it.toObjects(Quote::class.java)
                adapter.addQuote(data)
            }.addOnFailureListener {

            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}