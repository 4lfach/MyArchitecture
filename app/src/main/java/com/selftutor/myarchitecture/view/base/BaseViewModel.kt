package com.selftutor.myarchitecture.view.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.selftutor.myarchitecture.utils.Event

typealias LiveEvent<T> = LiveData<Event<T>>
typealias MutableLiveEvent<T> = MutableLiveData<Event<T>>

/**
 * Class is inherited by every viewModel
 */
open class BaseViewModel: ViewModel() {

	/**
	 * Override this method in case you need to listen for results from screens
	 */
	open fun onResult(result: Any){ }
}