package com.selftutor.myarchitecture.view.currentcolor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.selftutor.myarchitecture.R
import com.selftutor.myarchitecture.model.Repository
import com.selftutor.myarchitecture.model.colors.ColorListener
import com.selftutor.myarchitecture.model.colors.ColorsRepository
import com.selftutor.myarchitecture.model.colors.NamedColor
import com.selftutor.myarchitecture.view.Navigator
import com.selftutor.myarchitecture.view.UiActions
import com.selftutor.myarchitecture.view.base.BaseViewModel
import com.selftutor.myarchitecture.view.changecolor.ChangeColorFragment

class CurrentColorViewModel(
	private val navigator: Navigator,
	private val uiActions: UiActions,
	private val colorsRepository: ColorsRepository
): BaseViewModel()	 {

	private val _currentColor = MutableLiveData<NamedColor>()
	val currentColor : LiveData<NamedColor> = _currentColor

	private val colorListener: ColorListener = {
		_currentColor.postValue(it)
	}

	init{
		colorsRepository.addListener(colorListener)
	}

	override fun onCleared() {
		super.onCleared()
		colorsRepository.removeListener(colorListener)
	}

	override fun onResult(result: Any) {
		super.onResult(result)
		if(result is NamedColor){
			val message = uiActions.getString(R.string.changed_color, result.name)
			uiActions.toast(message)
		}
	}

	fun onChangeColor(){
		val currentColor = _currentColor.value ?: return
		val screen = ChangeColorFragment.Screen(currentColor.id)
		navigator.launch(screen)
	}
}