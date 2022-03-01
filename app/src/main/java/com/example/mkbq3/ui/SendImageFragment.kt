package com.example.mkbq3.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.system.Os.open
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.mkbq3.R
import com.example.mkbq3.databinding.FragmentSendimageBinding
import com.example.mkbq3.service.NetworkManager
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.nio.channels.FileChannel.open
import okhttp3.MultipartBody
import java.nio.channels.DatagramChannel.open


class SendImageFragment:Fragment(R.layout.fragment_sendimage) {

    lateinit var binding: FragmentSendimageBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSendimageBinding.bind(view)


        var getImageFromGallery = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                binding.imgProfile.setImageURI(it)
            })


        binding.btnSelectImage.setOnClickListener {
            getImageFromGallery.launch("image/*")
        }


        binding.btnSendImage.setOnClickListener {


        }


    }

}

