package com.example.tel.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tel.data.models.Contact
import com.example.tel.databinding.ItemContactBinding

class ContactAdapter(
    private val contacts: List<Contact>,
    private val onCallClick: (Contact) -> Unit,
    private val onMessageClick: (Contact) -> Unit
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.bind(contact, onCallClick, onMessageClick)
    }

    override fun getItemCount(): Int = contacts.size

    class ContactViewHolder(private val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact, onCallClick: (Contact) -> Unit, onMessageClick: (Contact) -> Unit) {
            binding.contactName.text = contact.name
            binding.contactPhone.text = contact.phoneNumber

            binding.callButton.setOnClickListener { onCallClick(contact) }
            binding.messageButton.setOnClickListener { onMessageClick(contact) }
        }
    }
}

