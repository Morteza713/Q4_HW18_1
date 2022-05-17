package com.example.q4_hw18_1.ui.save

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.q4_hw18_1.R
import com.example.q4_hw18_1.databinding.SaveUserFragmentBinding
import com.example.q4_hw18_1.ui.SwipeHandel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SaveFragment():Fragment(R.layout.save_user_fragment) {

    private var _binding: SaveUserFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<SaveViewModel>()
    private lateinit var saveAdapter: SaveAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = SaveUserFragmentBinding.bind(view)

        saveAdapter = SaveAdapter()
        binding.rvSaveUser.adapter = saveAdapter

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getUsersInDataBase.collect {
                    saveAdapter.submitList(it)
                }
            }
        }

        swipe(saveAdapter)
    }

    private fun swipe(saveAdapter: SaveAdapter) {
        val swipe = object : SwipeHandel(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val user = saveAdapter.itemsSwipe(viewHolder.adapterPosition)
                when (direction) {
                        ItemTouchHelper.RIGHT -> {
                            viewModel.removeUser(user.id)
                        }
                    }
                }
            }
            val touchHelper = ItemTouchHelper(swipe)
            touchHelper.attachToRecyclerView(binding.rvSaveUser)
        }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.rvSaveUser.adapter = null
        _binding = null
    }
}