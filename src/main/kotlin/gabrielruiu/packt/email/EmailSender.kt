package gabrielruiu.packt.email

import gabrielruiu.packt.BookSummary

interface EmailSender {
    fun sendEmailWithBookSummary(bookSummary: BookSummary)
}