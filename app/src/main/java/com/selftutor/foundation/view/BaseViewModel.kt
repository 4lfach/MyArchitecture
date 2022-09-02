package com.selftutor.foundation.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.selftutor.foundation.model.Result
import com.selftutor.foundation.utils.Event

typealias LiveEvent<T> = LiveData<Event<T>>
typealias MutableLiveEvent<T> = MutableLiveData<Event<T>>

typealias LiveResult<T> = LiveData<Result<T>>
typealias MutableLiveResult<T> = MutableLiveData<Result<T>>
typealias MediatorLiveResult<T> = MediatorLiveData<Result<T>>

/**
 * Class is inherited by every viewModel
 */
open class BaseViewModel: ViewModel() {

	/**
	 * Override this method in case you need to listen for results from screens
	 */
	open fun onResult(result: Any){ }
}
