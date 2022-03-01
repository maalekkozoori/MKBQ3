package com.example.mkbq3

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mkbq3.data.User
import com.example.mkbq3.databinding.ActivityMainBinding
import com.example.mkbq3.databinding.FragmentUserdetailsBinding
import com.example.mkbq3.service.NetworkManager
import com.example.mkbq3.ui.ShareViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetailsFragment:Fragment(R.layout.fragment_userdetails) {

    lateinit var binding: FragmentUserdetailsBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserdetailsBinding.bind(view)

        getUserInfo()


    }

    fun getUserInfo(){
        val sharedViewModel = ViewModelProvider(requireActivity()).get(ShareViewModel::class.java)

        val userInfo : Call<User> = NetworkManager.service.getUserInfo(sharedViewModel.userId)
        userInfo.enqueue(object : Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                initViews(response.body())
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun initViews(user : User?){
        Glide.with(this)
            .load(user?.image)
            .into(binding.imgUserAvatar)

        binding.tvUserName.text = user?.lastName+" "+user?.lastName
        binding.tvNationalCode.text = user?.nationalCode
    }

}