package com.williamfq.xhat.viewmodel

import androidx.lifecycle.ViewModel
import com.williamfq.domain.usecases.SendPanicAlertUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PanicViewModel @Inject constructor(
    private val sendPanicAlertUseCase: SendPanicAlertUseCase
) : ViewModel() {

    fun sendAlert(message: String) {
        sendPanicAlertUseCase.invoke(message)
    }
}
