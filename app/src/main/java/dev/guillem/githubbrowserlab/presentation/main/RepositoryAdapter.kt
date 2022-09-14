package dev.guillem.githubbrowserlab.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.guillem.githubbrowserlab.databinding.ItemRepositoryBinding
import dev.guillem.githubbrowserlab.domain.entity.User

class RepositoryAdapter(
    private val repositories: MutableList<User>
) : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding =
            ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun getItemCount() = repositories.size

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        with(holder) {
            with(repositories[position]) {
                binding.textUserName.text = name
                binding.textUserAddress.text = address
                binding.textUserPhone.text = phone
                binding.textUserEmail.text = email
            }
        }
    }

    fun update(users: List<User>) {
        this.repositories.clear()
        this.repositories.addAll(users)
        notifyDataSetChanged()
    }

    inner class RepositoryViewHolder(val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root)
}
