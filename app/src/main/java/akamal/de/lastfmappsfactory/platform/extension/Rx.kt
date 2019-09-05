package akamal.de.lastfmappsfactory.platform.extension

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.exceptions.CompositeException

//Helper extensions for detecting Rx error clearly in Logcat

fun <T> Observable<T>.dropRequest(): Observable<T> {
    return this.onErrorResumeNext { error: Throwable ->
        throw CompositeException(error)
    }
}

fun <T> Single<T>.dropRequest(): Single<T> {
    return this.onErrorResumeNext { error: Throwable ->
        throw CompositeException(error)
    }
}

fun Completable.dropRequest(): Completable {
    return this.onErrorResumeNext { error: Throwable ->
        throw CompositeException(error)
    }
}