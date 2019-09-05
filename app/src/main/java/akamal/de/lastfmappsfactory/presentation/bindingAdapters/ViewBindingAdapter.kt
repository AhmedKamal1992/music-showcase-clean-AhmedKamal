package akamal.de.lastfmappsfactory.presentation.bindingAdapters

import akamal.de.lastfmappsfactory.platform.bases.BaseViewState
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter


@BindingAdapter("bindingViewsObjectErrorState")
fun <T> bindingViewsObjectErrorState(view: TextView, viewState: BaseViewState<T>) {
    if(viewState is BaseViewState.Error) { view.visibility = View.VISIBLE; view.text = viewState.message } else view.visibility = View.GONE
}

@BindingAdapter("bindingViewsObjectLoadingState")
fun <T> bindingViewsObjectLoadingState(view: ProgressBar, viewState: BaseViewState<T>) {
    view.visibility =  if(viewState is BaseViewState.Loading) View.VISIBLE else View.GONE
}

@BindingAdapter("bindingViewsObjectLoadingState")
fun <T> bindingViewsObjectLoadingState(view: Button, viewState: BaseViewState<T>) {
    view.visibility =  if(viewState is BaseViewState.Success) View.VISIBLE else View.GONE
}


@BindingAdapter("bindingViewsObjectRetryState")
fun <T> bindingViewsObjectRetryState(view: Button, viewState: BaseViewState<T>) {
    view.visibility =  if(viewState is BaseViewState.Retry) View.VISIBLE else View.GONE
}