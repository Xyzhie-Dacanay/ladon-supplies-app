package com.dacanay_xyzhie_f.dacanay.ladon_app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Api.RetrofitInstance
import com.dacanay_xyzhie_f.dacanay.ladon_app.data.Model.TicketRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TicketViewModel : ViewModel() {
    private val _ticketState = MutableStateFlow<String?>(null)
    val ticketState: StateFlow<String?> = _ticketState

    fun submitTicket(token: String, ticketRequest: TicketRequest) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.submitTicket("Bearer $token", ticketRequest)
                if (response.isSuccessful) {
                    _ticketState.value = response.body()?.get("message") ?: "Submitted successfully"
                } else {
                    _ticketState.value = "Failed to submit ticket"
                }
            } catch (e: Exception) {
                _ticketState.value = "Error: ${e.message}"
            }
        }
    }
}
