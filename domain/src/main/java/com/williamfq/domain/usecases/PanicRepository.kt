
package com.williamfq.domain.usecases

interface PanicRepository {
    fun sendPanicAlert(message: String)
}
