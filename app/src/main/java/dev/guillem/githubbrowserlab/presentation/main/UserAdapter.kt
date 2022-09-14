package dev.guillem.githubbrowserlab.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.guillem.githubbrowserlab.databinding.ItemRepositoryBinding
import dev.guillem.githubbrowserlab.domain.entity.User

class UserAdapter(
    private val users: MutableList<User>
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding =
            ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        with(holder) {
            with(users[position]) {
                binding.textUserName.text = name
                binding.textUserAddress.text = address
                binding.textUserPhone.text = phone
                binding.textUserEmail.text = email
            }
        }
    }

    fun update(users: List<User>) {
        this.users.clear()
        this.users.addAll(users)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root)
}
