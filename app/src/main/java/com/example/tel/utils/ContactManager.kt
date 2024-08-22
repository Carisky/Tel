package com.example.tel.utils

import android.content.Context
import com.example.tel.data.models.Contact
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class ContactManager(private val context: Context) {
    private val gson = Gson()
    private val fileName = "contacts.json"

    fun saveContacts(contacts: List<Contact>) {
        val jsonString = gson.toJson(contacts)
        val file = File(context.filesDir, fileName)
        file.writeText(jsonString)
    }

    fun loadContacts(): List<Contact> {
        val file = File(context.filesDir, fileName)
        if (!file.exists()) {
            return emptyList()
        }
        val jsonString = file.readText()
        val type = object : TypeToken<List<Contact>>() {}.type
        return gson.fromJson(jsonString, type)
    }
}
