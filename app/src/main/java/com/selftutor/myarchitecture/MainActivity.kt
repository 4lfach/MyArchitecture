package com.selftutor.myarchitecture

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.selftutor.foundation.ActivityScopeViewModel
import com.selftutor.foundation.navigator.IntermediateNavigator
import com.selftutor.foundation.navigator.StackFragmentNavigator
import com.selftutor.foundation.uiactions.AndroidUiActions
import com.selftutor.foundation.utils.viewModelCreator
import com.selftutor.foundation.view.FragmentsHolder
import com.selftutor.myarchitecture.view.currentcolor.CurrentColorFragment

class MainActivity : AppCompatActivity(), FragmentsHolder {

	private lateinit var navigator: StackFragmentNavigator

	private val activityViewModel by viewModelCreator<ActivityScopeViewModel> {
		ActivityScopeViewModel(
			uiActions = AndroidUiActions(applicationContext),
			navigator = IntermediateNavigator()
		)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		navigator = StackFragmentNavigator(activity = this,
			containerId = R.id.fragmentContainer,
			defaultTitle = R.string.app_name,
			animations = StackFragmentNavigator.Animaions(
				R.anim.enter,
				R.anim.exit,
				R.anim.pop_enter,
				R.anim.pop_exit,
			),
			initialScreenCreator = { CurrentColorFragment.Screen() }
		)
		navigator.onCreate(savedInstanceState)
	}

	override fun onDestroy() {
		super.onDestroy()
		navigator.onDestroy()
	}

	override fun onSupportNavigateUp(): Boolean {
		onBackPressed()
		return true
	}

	override fun onResume() {
		super.onResume()
		// execute navigation actions only when activity is active
		activityViewModel.navigator.setTargetNavigator(navigator)
	}

	override fun onStop() {
		super.onStop()
		// stop navigation actions when activity is stopped
		activityViewModel.navigator.setTargetNavigator(null)
	}

	override fun notifyScreenUpdates() {
		navigator.notifyScreenUpdates()
	}

	override fun getActivityScopeViewModel(): ActivityScopeViewModel {
		return activityViewModel
	}

}