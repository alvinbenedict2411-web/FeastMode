package com.example.beastmode_2.Presentation.Viewmodels.ViewModelFactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.beastmode_2.Presentation.Viewmodels.ViewModels.AddRecipeViewModel

class AddRecipeViewModelFactory(private val application: Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddRecipeViewModel::class.java)) {
            return AddRecipeViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
    }
