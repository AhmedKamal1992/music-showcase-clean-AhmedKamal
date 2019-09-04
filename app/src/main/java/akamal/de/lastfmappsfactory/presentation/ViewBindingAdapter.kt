package akamal.de.lastfmappsfactory.presentation

import akamal.de.lastfmappsfactory.data.topAlbums.model.TopAlbum
import akamal.de.lastfmappsfactory.platform.bases.BaseViewState
import akamal.de.lastfmappsfactory.presentation.topAlbums.TopAlbumsRvAdapter
import android.view.View
import android.widget.*
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("loadImageFromUrl")
fun loadImageFromUrl(view: ImageView, url: String?) {
    Glide.with(view.context).load(url).
        diskCacheStrategy(DiskCacheStrategy.ALL).
        transition(DrawableTransitionOptions.withCrossFade()).into(view)
}

@BindingAdapter("loadImageFromCache")
fun loadImageFromCache(view: ImageView, url: String?) {
    Glide.with(view.context).load(url).
        onlyRetrieveFromCache(true).
        transition(DrawableTransitionOptions.withCrossFade()).into(view)
}

@BindingAdapter("bindingViewsErrorState")
fun <T> bindingViewsErrorState(view: TextView, viewState: BaseViewState<List<T>>) {
    if(viewState is BaseViewState.Error) { view.visibility = View.VISIBLE; view.text = viewState.message } else view.visibility = View.GONE
}

@BindingAdapter("bindingViewsLoadingState")
fun <T> bindingViewsLoadingState(view: ProgressBar, viewState: BaseViewState<List<T>>) {
    view.visibility =  if(viewState is BaseViewState.Loading) View.VISIBLE else View.GONE
}


@BindingAdapter("bindingViewsRetryState")
fun <T> bindingViewsRetryState(view: Button, viewState: BaseViewState<List<T>>) {
    view.visibility =  if(viewState is BaseViewState.Retry) View.VISIBLE else View.GONE
}


@BindingAdapter("bindingAdapterSuccessState")
fun <T> bindingAdapterSuccessState(rv: RecyclerView, viewState: BaseViewState<List<T>>) {
    val adapter = rv.adapter
    if(viewState is BaseViewState.Success) {
        rv.visibility = View.VISIBLE
        when (adapter) {
            is TopAlbumsRvAdapter -> {
                rv.addItemDecoration(DividerItemDecoration(rv.context, DividerItemDecoration.VERTICAL))
                adapter.submitList(viewState.data as List<TopAlbum>)
                //submit list
            }
        }
    } else {
        rv.visibility = View.GONE
    }
}