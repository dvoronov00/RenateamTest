package com.dvoronov00.rentateamtest.ui.user

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.dvoronov00.rentateamtest.App
import com.dvoronov00.rentateamtest.R
import com.dvoronov00.rentateamtest.databinding.FragmentUserBinding
import com.dvoronov00.rentateamtest.databinding.FragmentUsersBinding
import com.dvoronov00.rentateamtest.di.ViewModelFactory
import com.dvoronov00.rentateamtest.model.User
import com.dvoronov00.rentateamtest.ui.users.UsersViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class UserFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val vm: UserViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)
    }

    private val args: UserFragmentArgs by navArgs()
    private var binding: FragmentUserBinding? = null
    private var disposable: Disposable? = null

    override fun onAttach(context: Context) {
        App.getComponent().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentUserBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userId = args.userId
        vm.getUser(userId)
        disposable = vm.userRelay
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                setUser(it)
            }
    }

    private fun setUser(user: User){
        binding?.let { binding ->
            Glide.with(requireContext())
                .load(user.avatar)
                .placeholder(R.drawable.ic_group)
                .error(R.drawable.ic_help)
                .into(binding.imageViewAvatar)
            binding.textViewName.text = "${user.first_name} ${user.last_name}"
            binding.textViewEmail.text = user.email
        }
    }

    override fun onDestroy() {
        disposable?.dispose()
        super.onDestroy()
    }
}
