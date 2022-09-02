package com.selftutor.myarchitecture.view

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.children
import com.selftutor.foundation.model.Result
import com.selftutor.foundation.view.BaseFragment
import com.selftutor.myarchitecture.R
import com.selftutor.myarchitecture.databinding.PartLayoutBinding

fun <T> BaseFragment.renderSimpleResult(root: ViewGroup, result: Result<T>, onSuccess: (T) -> Unit){
	val binding = PartLayoutBinding.bind(root)
	renderResult(root = root, result = result,
	onPending = {
		binding.progressBar.visibility = View.VISIBLE
	},
	onError = {
		binding.tryAgainLayout.visibility = View.VISIBLE
	},
	onSuccess = { successData ->
		root.children.filter { it.id != R.id.progressBar && it.id != R.id.tryAgainLayout }
			.forEach { it.visibility = View.VISIBLE }
		onSuccess(successData)
	})
}

fun BaseFragment.onTryAgain(root: View, onTryAgainPressed: () -> Unit){
	root.findViewById<Button>(R.id.tryAgainButton).setOnClickListener {
		onTryAgainPressed()
	}
}