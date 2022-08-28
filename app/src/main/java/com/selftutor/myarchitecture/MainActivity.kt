package com.selftutor.myarchitecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.selftutor.myarchitecture.view.HasScreenTitle
import com.selftutor.myarchitecture.view.base.BaseFragment
import com.selftutor.myarchitecture.view.currentcolor.CurrentColorFragment

class MainActivity : AppCompatActivity() {

	private val activityViewModel by viewModels<MainViewModel> {
		ViewModelProvider.AndroidViewModelFactory(application)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		if(savedInstanceState == null){
			activityViewModel.launchFragment(this,
			CurrentColorFragment.Screen(),
			false)
		}

		supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentCallbacks, false)
	}

	override fun onDestroy() {
		super.onDestroy()
		supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentCallbacks)
	}

	override fun onSupportNavigateUp(): Boolean {
		onBackPressed()
		return true
	}

	override fun onResume() {
		super.onResume()
		// execute navigation actions only when activity is active
		activityViewModel.whenActivityActive.resource = this
	}

	override fun onStop() {
		super.onStop()
		// stop navigation actions when activity is stopped
		activityViewModel.whenActivityActive.resource = null
	}

	fun notifyScreenUpdates(){
		val f  = supportFragmentManager.findFragmentById(R.id.fragmentContainer)

		if (supportFragmentManager.backStackEntryCount > 0){
			//more than 1 screen -> show BACK button on toolbar
			supportActionBar?.setDisplayHomeAsUpEnabled(true)
		} else{
			supportActionBar?.setDisplayHomeAsUpEnabled(false)
		}

		if(f is HasScreenTitle && f.getScreenTitle() != null){
			supportActionBar?.title = f.getScreenTitle()
		} else{
			supportActionBar?.title = getString(R.string.app_name)
		}

		val result = activityViewModel.result.value?.getValue() ?: return
		if(f is BaseFragment){
			// has result that can be delivered to the screen's view-model
			f.viewModel.onResult(result)
		}
	}

	private val fragmentCallbacks = object: FragmentManager.FragmentLifecycleCallbacks() {
		override fun onFragmentViewCreated(
			fm: FragmentManager,
			f: Fragment,
			v: View,
			savedInstanceState: Bundle?
		) {
			super.onFragmentViewCreated(fm, f, v, savedInstanceState)
			notifyScreenUpdates()
		}
	}
}