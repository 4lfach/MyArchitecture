package com.selftutor.foundation.view

import com.selftutor.foundation.ActivityScopeViewModel

interface FragmentsHolder {
	fun notifyScreenUpdates()

	fun getActivityScopeViewModel() : ActivityScopeViewModel
}