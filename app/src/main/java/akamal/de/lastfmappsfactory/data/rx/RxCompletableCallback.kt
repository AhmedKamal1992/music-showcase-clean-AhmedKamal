package akamal.de.lastfmappsfactory.data.rx

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RxCompletableCallback {
    companion object {
        fun getSchedulersForCompletable(): (Completable) -> Completable {
            return { completable: Completable ->
                completable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }
    }
}