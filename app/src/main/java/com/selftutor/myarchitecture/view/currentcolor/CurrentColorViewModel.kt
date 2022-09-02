package com.selftutor.myarchitecture.view.currentcolor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.selftutor.foundation.model.ExceptionResult
import com.selftutor.foundation.model.PendingResult
import com.selftutor.foundation.model.SuccessResult
import com.selftutor.foundation.model.takeSuccess
import com.selftutor.myarchitecture.R
import com.selftutor.myarchitecture.model.colors.ColorListener
import com.selftutor.myarchitecture.model.colors.ColorsRepository
import com.selftutor.myarchitecture.model.colors.NamedColor
import com.selftutor.foundation.navigator.Navigator
import com.selftutor.foundation.uiactions.UiActions
import com.selftutor.foundation.view.BaseViewModel
import com.selftutor.foundation.view.LiveResult
import com.selftutor.foundation.view.MutableLiveResult
import com.selftutor.myarchitecture.view.changecolor.ChangeColorFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.RuntimeException

class CurrentColorViewModel(
	private val navigator: Navigator,
	private val uiActions: UiActions,
	private val colorsRepository: ColorsRepository
): BaseViewModel()	 {

	private val _currentColor = MutableLiveResult<NamedColor>(PendingResult())
	val currentColor : LiveResult<NamedColor> = _currentColor

	private val colorListener: ColorListener = {
		_currentColor.postValue(SuccessResult(it))
	}

	init{
		viewModelScope.launch {
			delay(2000)
			_currentColor.postValue(ExceptionResult(RuntimeException()))
		}
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
		val currentColor = _currentColor.value.takeSuccess() ?: return
		val screen = ChangeColorFragment.Screen(currentColor.id)
		navigator.launch(screen)
	}

	fun onTryAgain(){
		viewModelScope.launch {
			_currentColor.postValue(PendingResult())
			delay(2000)
			colorsRepository.addListener(colorListener)
		}
	}
}