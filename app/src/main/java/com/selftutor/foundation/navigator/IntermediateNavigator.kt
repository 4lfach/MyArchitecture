package com.selftutor.foundation.navigator

import com.selftutor.foundation.utils.ResourceActions
import com.selftutor.foundation.view.BaseScreen

class IntermediateNavigator: Navigator {

	private val targetNavigator = ResourceActions<Navigator>()

	override fun launch(screen: BaseScreen) = targetNavigator{
		it.launch(screen)
	}

	override fun goBack(result: Any?) = targetNavigator {
		it.goBack(result)
	}

	fun setTargetNavigator(navigator: Navigator?){
		targetNavigator.resource = navigator
	}

	fun clear(){
		targetNavigator.clear()
	}
}