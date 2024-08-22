package com.example.tel

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tel.adapters.ContactAdapter
import com.example.tel.data.models.Contact
import com.example.tel.databinding.ActivityMainBinding
import com.example.tel.utils.ContactManager

class MainActivity : AppCompatActivity() {

    private lateinit var contactManager: ContactManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contactManager = ContactManager(this)

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE, Manifest.permission.SEND_SMS), 1)

        val contacts = contactManager.loadContacts()
        val adapter = ContactAdapter(contacts, ::makeCall, ::sendSms)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun makeCall(contact: Contact) {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:${contact.phoneNumber}")
        try {
            startActivity(intent)
        } catch (e: SecurityException) {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendSms(contact: Contact) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:${contact.phoneNumber}"))
        intent.putExtra("sms_body", "Hello, ${contact.name}!")
        startActivity(intent)
    }
}

