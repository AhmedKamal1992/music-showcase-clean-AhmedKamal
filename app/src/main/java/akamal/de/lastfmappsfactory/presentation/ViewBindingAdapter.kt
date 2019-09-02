package akamal.de.lastfmappsfactory.presentation

import akamal.de.lastfmappsfactory.platform.bases.BaseViewState
import android.view.View
import android.widget.*
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("loadImageFromUrl")
fun loadImageFromUrl(view: ImageView, url: String?) {
    Glide.with(view.context).load(url).
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
fun <T> bindingAdapterSuccessState(view: RecyclerView, viewState: BaseViewState<List<T>>) {
    val adapter = view.adapter
    if(viewState is BaseViewState.Success) {
        view.visibility = View.VISIBLE
        when (adapter) {
            is BaseAdapter -> {
                view.addItemDecoration(DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL))
                //submit list
            }
        }
    } else {
        view.visibility = View.GONE
    }
}