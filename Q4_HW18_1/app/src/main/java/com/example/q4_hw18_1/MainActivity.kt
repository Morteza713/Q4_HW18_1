package com.example.q4_hw18_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.q4_hw18_1.data.network.model.UserItem
import com.example.q4_hw18_1.databinding.ActivityMainBinding
import com.example.q4_hw18_1.ui.MainViewModel
import com.example.q4_hw18_1.ui.user.DialogListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), DialogListener {

    private val viewModel by viewModels<MainViewModel>()
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val navController by lazy { val navHostFragment = supportFragmentManager
        .findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navHostFragment.navController }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNavView.setupWithNavController(navController)

        binding.btnAddUser.setOnClickListener {
            navController.navigate(R.id.addUserDialog)
        }
    }
    override fun onDialogPositiveClick(dialog: DialogFragment, userItem: UserItem) {
        viewModel.createUser(userItem)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}