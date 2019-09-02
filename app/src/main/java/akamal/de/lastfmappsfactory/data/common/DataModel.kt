package akamal.de.lastfmappsfactory.data.common

sealed class DataResult<E>(val source: DataSource) {
    class Success<E>(source: DataSource, val result: E): DataResult<E>(source)
    class Error<E>(source: DataSource, val throwable: Throwable): DataResult<E>(source)
}

enum class DataSource { Network, Local }