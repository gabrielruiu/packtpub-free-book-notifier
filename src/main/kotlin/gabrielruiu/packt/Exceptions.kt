package gabrielruiu.packt

class EmailPreparationException(
        bookSummary: BookSummary,
        throwable: Throwable
) : RuntimeException(
        String.format("Error while trying to preparing the email with the following content: [%s]", bookSummary.toString()),
        throwable)