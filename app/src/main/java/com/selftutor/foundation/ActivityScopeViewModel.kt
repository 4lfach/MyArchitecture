package com.selftutor.foundation

import android.app.Application
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.selftutor.foundation.navigator.IntermediateNavigator
import com.selftutor.foundation.utils.Event
import com.selftutor.foundation.utils.ResourceActions
import com.selftutor.foundation.navigator.Navigator
import com.selftutor.foundation.uiactions.UiActions
import com.selftutor.foundation.view.BaseScreen
import com.selftutor.foundation.view.LiveEvent
import com.selftutor.foundation.view.MutableLiveEvent

const val ARG_SCREEN = "screen"

class ActivityScopeViewModel(
	val uiActions: UiActions,
	val navigator: IntermediateNavigator
) : ViewModel(),
	Navigator by navigator,
	UiActions by uiActions {

	override fun onCleared() {
		super.onCleared()
		navigator.clear()
	}

}