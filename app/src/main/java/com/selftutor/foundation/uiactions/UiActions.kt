package com.selftutor.foundation.uiactions

interface UiActions {

	/**
	 * Display a toast message
	 */
	fun toast(message: String)

	/**
	 * Get string resource content by its identifier
	 */
	fun getString(messageRes: Int, vararg args: Any): String
}