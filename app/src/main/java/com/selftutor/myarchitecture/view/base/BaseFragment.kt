package com.selftutor.myarchitecture.view.base

import androidx.fragment.app.Fragment
import com.selftutor.myarchitecture.MainActivity

/**
 * Base class for all fragments
 */
abstract class BaseFragment: Fragment() {
	/**
	 * ViewModel that manages fragment
	 */
	abstract val viewModel: BaseViewModel

	/**
	 * Call this method when activity controls (e.g. toolbar) should be re-rendered
	 */
	fun notifyScreenUpdates(){
		//If there are 2 or more activities then interfaces should be used
		(requireActivity() as MainActivity).notifyScreenUpdates()
	}
}