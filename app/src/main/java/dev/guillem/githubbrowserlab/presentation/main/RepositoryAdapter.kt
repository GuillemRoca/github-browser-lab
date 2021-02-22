package dev.guillem.githubbrowserlab.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.guillem.githubbrowserlab.databinding.ItemRepositoryBinding
import dev.guillem.githubbrowserlab.presentation.model.RepositoryView
import dev.guillem.githubbrowserlab.presentation.tools.extensions.getColorFromAttr
import dev.guillem.githubbrowserlab.presentation.tools.imageloader.ImageLoader

class RepositoryAdapter(
    private val repositories: MutableList<RepositoryView>,
    private val repositoryClickListener: RepositoryClickListener,
    private val imageLoader: ImageLoader,
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
                binding.textRepositoryTitle.text = name
                binding.textRepositoryDescription.text = description
                binding.textRepositoryOwner.text = owner.login
                imageLoader.load(owner.avatarUrl, binding.imageRepositoryOwner)
                holder.itemView.setBackgroundColor(
                    holder.itemView.context.getColorFromAttr(
                        backgroundAttrColor
                    )
                )
                holder.itemView.setOnLongClickListener {
                    repositoryClickListener.onRepositoryLongClick(this)
                    true
                }
            }
        }
    }

    fun update(repositories: List<RepositoryView>) {
        this.repositories.clear()
        this.repositories.addAll(repositories)
        notifyDataSetChanged()
    }

    inner class RepositoryViewHolder(val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root)

}