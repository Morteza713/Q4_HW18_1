package com.example.q4_hw18_1.ui.user

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.q4_hw18_1.data.network.model.UserItem
import com.example.q4_hw18_1.databinding.UserDialogBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AddUserDialog:DialogFragment() {

    private var _binding: UserDialogBinding? = null
    private val binding get() = _binding!!
    private lateinit var dialogListener: DialogListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        return super.onCreateDialog(savedInstanceState)

        _binding = UserDialogBinding.inflate(layoutInflater)

        val hobbies = mutableListOf<String>()

        return activity?.let {
            val builder = MaterialAlertDialogBuilder(it)
            builder.setView(binding.root)
                .setTitle("Add User")
                .setPositiveButton("Add") { _, _ ->
                    with(binding) {
                        if (cbMovie.isChecked) hobbies.add("movie")
                        if (cbSport.isChecked) hobbies.add("sport")
                        val newUser = UserItem(
                            edFirstname.text.toString(),
                            edLastname.text.toString(),
                            edNationalCode.text.toString(),
                            hobbies as ArrayList<String>
                        )
                        dialogListener.onDialogPositiveClick(this@AddUserDialog, newUser)
                        dismiss()
                    }
                }
                .setNeutralButton("Cancel") { _, _ ->
                    dismiss()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity null")
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            dialogListener = context as DialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                ("$context implement DialogListener")
            )
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
interface DialogListener {
    fun onDialogPositiveClick(dialog: DialogFragment, userItem: UserItem)
}