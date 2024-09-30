package kata.practice.concertcomparison

data class Page<T>(
    val items: Iterable<T>,
    val continuationToken: String?
) {
    fun <R> map(mapper: (T) -> R): Page<R> {
        return Page(
            items.map(mapper),
            continuationToken
        )
    }
}
