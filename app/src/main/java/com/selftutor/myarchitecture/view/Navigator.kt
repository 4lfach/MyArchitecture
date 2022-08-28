package com.selftutor.myarchitecture.view

import com.selftutor.myarchitecture.view.base.BaseScreen

/**
 * Interface for navigation
 */
interface Navigator {

	/**
	 * Launch a new screen
	 */
	fun launch(screen: BaseScreen)

	/**
	 * Go back to previous screen and optionally send the result
	 */
	fun goBack(result: Any? = null)
}