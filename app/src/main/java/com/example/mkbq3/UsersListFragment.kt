package com.example.mkbq3

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.enableSavedStateHandles
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mkbq3.data.User
import com.example.mkbq3.databinding.FragmentUserslistBinding
import com.example.mkbq3.service.NetworkManager
import com.example.mkbq3.ui.CustomAdapter
import com.example.mkbq3.ui.ShareViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersListFragment:Fragment(R.layout.fragment_userslist),CustomAdapter.OnItemClickListener {
    lateinit var binding: FragmentUserslistBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserslistBinding.bind(view)

        getUsersList()

    }

    fun getUsersList(){
        val sharedViewModel = ViewModelProvider(requireActivity()).get(ShareViewModel::class.java)
        val getUsers : Call<List<User>> = NetworkManager.service.getUsersList()
        getUsers.enqueue(object : Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                sharedViewModel.usersList.value = response.body()
                val customAdapter = CustomAdapter(requireContext(),response.body(),this@UsersListFragment)
                binding.rcUsersList.adapter = customAdapter
                binding.rcUsersList.layoutManager =LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(requireContext(), t.message.toString(), Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onItemClick(position: Int) {
        val sharedViewModel = ViewModelProvider(requireActivity()).get(ShareViewModel::class.java)
        sharedViewModel.userId = sharedViewModel.usersList.value?.get(position).toString()
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            replace<UserDetailsFragment>(R.id.fragmentContainer)
            addToBackStack(null)
        }
    }
}