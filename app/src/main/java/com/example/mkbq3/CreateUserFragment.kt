package com.example.mkbq3

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.mkbq3.data.CreatUserBody
import com.example.mkbq3.data.User
import com.example.mkbq3.databinding.FragmentCreateuserBinding
import com.example.mkbq3.service.NetworkManager
import com.example.mkbq3.ui.SendImageFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateUserFragment:Fragment(R.layout.fragment_createuser) {
    lateinit var binding :FragmentCreateuserBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreateuserBinding.bind(view)

        binding.btnCreatUser.setOnClickListener {
            with(binding){
                val firstName = edFirstName.text.toString()
                val lastName = edLastName.text.toString()
                val nationalCode = edNationalCode.text.toString()
                createUser(firstName,lastName,nationalCode)

                parentFragmentManager.commit {

                    setReorderingAllowed(true)
                    replace<SendImageFragment>(R.id.fragmentContainer)
                    addToBackStack(null)
                }

            }

        }


    }

    fun createUser(firstName : String , lastName : String, nationalCode:String){
        with(binding){
            val userCreat : Call<String> = NetworkManager.service.createUser(CreatUserBody(firstName,lastName,nationalCode))

            userCreat.enqueue(object : Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Toast.makeText(requireContext(), response.body().toString(), Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<String>, t: Throwable) {

                    Toast.makeText(requireContext(), t.message.toString(), Toast.LENGTH_LONG).show()

                }

            })
        }
    }
}