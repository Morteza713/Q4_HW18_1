package com.example.q4_hw18_1.ui.user

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.q4_hw18_1.R
import com.example.q4_hw18_1.data.datasource.model.User
import com.example.q4_hw18_1.databinding.UserFragmentBinding
import com.example.q4_hw18_1.ui.SwipeHandel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserFragment: Fragment(R.layout.user_fragment) {

    private var _binding: UserFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<UserViewModel>()
    private lateinit var userAdapter: UserAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = UserFragmentBinding.bind(view)

        userAdapter = UserAdapter()
        binding.rvUser.adapter = userAdapter

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getUsers.collect {
                    userAdapter.submitList(it.data)
                }
            }
        }

        swipe(userAdapter)
    }

    private fun swipe(adapterUsers: UserAdapter) {
        val swipe = object : SwipeHandel(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        val user = adapterUsers.addInData(viewHolder.adapterPosition)
                        viewModel.addHobbies(user._id)
                        viewModel.addUser(
                            User(
                                user._id,
                                user.firstName,
                                user.lastName,
                                user.nationalCode
                            )
                        )
                        binding.rvUser.adapter?.notifyItemChanged(viewHolder.adapterPosition)
                    }
                }
            }
        }
        val touchHelper = ItemTouchHelper(swipe)
        touchHelper.attachToRecyclerView(binding.rvUser)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvUser.adapter = null
        _binding = null
    }
}