package com.williamfq.domain.usecases

class SendPanicAlertUseCase(
    private val repository: PanicRepository
) {
    fun invoke(message: String) {
        repository.sendPanicAlert(message)
    }
}
