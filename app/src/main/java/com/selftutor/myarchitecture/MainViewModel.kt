package com.selftutor.myarchitecture

import android.app.Application
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import com.selftutor.myarchitecture.utils.Event
import com.selftutor.myarchitecture.utils.ResourceActions
import com.selftutor.myarchitecture.view.Navigator
import com.selftutor.myarchitecture.view.UiActions
import com.selftutor.myarchitecture.view.base.BaseScreen
import com.selftutor.myarchitecture.view.base.LiveEvent
import com.selftutor.myarchitecture.view.base.MutableLiveEvent

const val ARG_SCREEN = "screen"

class MainViewModel(application: Application) : AndroidViewModel(application), Navigator,
	UiActions {

	val whenActivityActive = ResourceActions<MainActivity>()

	private val _result = MutableLiveEvent<Any>()
	val result: LiveEvent<Any> = _result

	override fun launch(screen: BaseScreen) = whenActivityActive {
		launchFragment(it, screen)
	}

	override fun goBack(result: Any?) = whenActivityActive {
		if (result != null) {
			_result.value = Event(result)
		}

		it.onBackPressed()
	}

	override fun toast(message: String) {
		Toast.makeText(getApplication(), message, Toast.LENGTH_SHORT).show()
	}

	override fun getString(messageRes: Int, vararg args: Any): String {
		return getApplication<App>().getString(messageRes, *args)
	}

	fun launchFragment(activity: MainActivity, screen: BaseScreen, addToBackStack: Boolean = true) {
		val fragment = screen.javaClass.enclosingClass.newInstance() as Fragment

		fragment.arguments = bundleOf(ARG_SCREEN to screen)

		val transaction = activity.supportFragmentManager.beginTransaction()
		if(addToBackStack) transaction.addToBackStack(null)
		transaction
			.setCustomAnimations(
				R.anim.enter,
				R.anim.exit,
				R.anim.pop_enter,
				R.anim.pop_exit,
			)
			.replace(R.id.fragmentContainer, fragment)
			.commit()
	}

	override fun onCleared() {
		super.onCleared()
		whenActivityActive.clear()
	}

}