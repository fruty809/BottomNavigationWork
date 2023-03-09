package com.example.bottomnavigationwork.ui.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bottomnavigationwork.R
import com.example.bottomnavigationwork.data.local.Pref
import com.example.bottomnavigationwork.databinding.FragmentProfileBinding
import com.example.bottomnavigationwork.utils.loadImage
import com.geektech.taskmanager.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {
    private lateinit var auth: Firebase
    private lateinit var binding: FragmentProfileBinding
    private lateinit var pref: Pref

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK && it.data != null) {
                val uri: Uri? = it.data?.data
                pref.setImage(uri.toString())
                binding.image.loadImage(uri.toString())
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        pref = Pref(requireContext())

        saveName()
        binding.image.loadImage(pref.getImage())
        saveImage()
        signOut()

    }

    private fun signOut() {
        binding.btnSignOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.authFragment)
            auth.currentUser == null
            findNavController().navigate(R.id.authFragment)
        }
    }

    private fun saveImage() {
        binding.image.loadImage(pref.getImage())
        binding.image.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            launcher.launch(intent)
        }
    }

    private fun saveName() {
        binding.etProfile.setText(pref.getName())
        binding.etProfile.addTextChangedListener {
            pref.saveName(binding.etProfile.text.toString())
        }
    }
}