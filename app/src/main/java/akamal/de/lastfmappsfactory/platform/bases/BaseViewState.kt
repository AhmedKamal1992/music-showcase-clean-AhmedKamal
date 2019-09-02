package akamal.de.lastfmappsfactory.platform.bases

sealed class BaseViewState<E> {
    class Success<E>(val data: E): BaseViewState<E>()
    class Loading<E>: BaseViewState<E>()
    class Nothing<E>: BaseViewState<E>()
    class Error<E>(val message: String): BaseViewState<E>()
    class Retry<E>: BaseViewState<E>()
}