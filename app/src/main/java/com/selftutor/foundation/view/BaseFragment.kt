package com.selftutor.foundation.view

import android.opengl.Visibility
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.selftutor.foundation.model.ExceptionResult
import com.selftutor.foundation.model.PendingResult
import com.selftutor.foundation.model.Result
import com.selftutor.foundation.model.SuccessResult

/**
 * Base class for all fragments
 */
abstract class BaseFragment : Fragment() {
	/**
	 * ViewModel that manages fragment
	 */
	abstract val viewModel: BaseViewModel

	/**
	 * Call this method when activity controls (e.g. toolbar) should be re-rendered
	 */
	fun notifyScreenUpdates() {
		//If there are 2 or more activities then interfaces should be used
		(requireActivity() as FragmentsHolder).notifyScreenUpdates()
	}

	fun <T> renderResult(
		root: ViewGroup,
		result: Result<T>,
		onPending: () -> Unit,
		onError: (Exception) -> Unit,
		onSuccess: (T) -> Unit
	) {
		root.children.forEach { it.visibility = View.GONE }

		when(result){
			is SuccessResult -> onSuccess(result.data)
			is ExceptionResult -> onError(result.exception)
			is PendingResult -> onPending()
		}
	}
}