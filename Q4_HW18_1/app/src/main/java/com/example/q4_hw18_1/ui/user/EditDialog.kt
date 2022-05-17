package com.example.q4_hw18_1.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.q4_hw18_1.data.datasource.model.User
import com.example.q4_hw18_1.data.network.model.UserItem
import com.example.q4_hw18_1.databinding.EditDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EditDialog(val user: User, private val userItem: (UserItem) -> Unit):BottomSheetDialogFragment() {

    private var _binding: EditDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = EditDialogBinding.inflate(inflater, container, false)

        binding.edFirstname.setText(user.firstName)
        binding.edLastname.setText(user.lastName)
        binding.edNationalCode.setText(user.nationalCode)

        val hobbies = mutableListOf<String>()
        binding.btnUpdate.setOnClickListener {
            if (binding.cbMovie.isChecked) hobbies.add("movie")
            if (binding.cbSport.isChecked) hobbies.add("sport")
            val addUser = UserItem(binding.edFirstname.text.toString(), binding.edLastname.text.toString(),
                binding.edNationalCode.text.toString(), hobbies as ArrayList<String>)
            userItem(addUser)
            dismiss()
        }
        binding.btnCancel.setOnClickListener { dismiss() }
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}