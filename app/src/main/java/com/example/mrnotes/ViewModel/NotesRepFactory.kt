package com.example.mrnotes.ViewModel

import NotesRepository
import NotesViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NotesRepFactory(private val repository: NotesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NotesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
