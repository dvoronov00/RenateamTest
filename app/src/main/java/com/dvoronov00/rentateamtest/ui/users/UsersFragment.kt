package com.dvoronov00.rentateamtest.ui.users

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dvoronov00.rentateamtest.App
import com.dvoronov00.rentateamtest.R
import com.dvoronov00.rentateamtest.databinding.FragmentUsersBinding
import com.dvoronov00.rentateamtest.di.ViewModelFactory
import com.dvoronov00.rentateamtest.model.DataState
import com.dvoronov00.rentateamtest.model.User
import com.dvoronov00.rentateamtest.ui.user.UserViewModel
import com.dvoronov00.rentateamtest.ui.users.adapter.UserClickListener
import com.dvoronov00.rentateamtest.ui.users.adapter.UsersAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UsersFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val vm: UsersViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(UsersViewModel::class.java)
    }
    private val adapter = UsersAdapter()

    private var binding: FragmentUsersBinding? = null

    private var disposable : Disposable? = null

    override fun onAttach(context: Context) {
        App.getComponent().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentUsersBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let {
            it.recyclerViewUsers.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            it.recyclerViewUsers.adapter = adapter
        }
        adapter.setOnClickListener(object : UserClickListener {
            override fun onClick(user: User) {
                val action = UsersFragmentDirections.actionNavigationUsersToNavigationUser(user.id)
                findNavController().navigate(action)
            }
        })
        observe()
        vm.getUsers()
    }

    private fun observe() {
        disposable = vm.usersRelay
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is DataState.Loading -> {
                        showLoading()
                    }
                    is DataState.Success -> {
                        showData(it.data)
                    }
                    is DataState.Failure -> {
                        showError()
                    }
                }
            }
    }

    private fun showLoading() {
        binding?.let { binding ->
            binding.progressBar.visibility = View.VISIBLE
            binding.textViewError.visibility = View.GONE
            binding.recyclerViewUsers.visibility = View.GONE
        }
    }

    private fun showData(items: List<User>) {
        binding?.let { binding ->
            binding.progressBar.visibility = View.GONE
            binding.textViewError.visibility = View.GONE
            binding.recyclerViewUsers.visibility = View.VISIBLE
            adapter.setItems(items)
        }
    }

    private fun showError() {
        binding?.let { binding ->
            binding.progressBar.visibility = View.GONE
            binding.textViewError.visibility = View.VISIBLE
            binding.recyclerViewUsers.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        disposable?.dispose()
        super.onDestroy()
    }


}
