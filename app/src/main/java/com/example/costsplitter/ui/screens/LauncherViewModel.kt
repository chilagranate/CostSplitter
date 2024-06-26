package com.example.costsplitter.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class LauncherViewModel() : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    init {
        checkLoggedInState()
    }
    private fun checkLoggedInState() {
        viewModelScope.launch {
            firebaseAuth.currentUser?.let {
                _isLoggedIn.value = true
            } ?: run {
                _isLoggedIn.value = false
            }
        }
    }



}