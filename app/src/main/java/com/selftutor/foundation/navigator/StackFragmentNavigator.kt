package com.selftutor.foundation.navigator

import android.os.Bundle
import android.view.View
import androidx.annotation.AnimRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.selftutor.foundation.ARG_SCREEN
import com.selftutor.foundation.utils.Event
import com.selftutor.foundation.view.BaseFragment
import com.selftutor.foundation.view.BaseScreen
import com.selftutor.foundation.view.HasScreenTitle

class StackFragmentNavigator(
	private val activity: AppCompatActivity,
	@IdRes private val containerId: Int,
	@StringRes val defaultTitle: Int,
	private val animations: Animaions,
	private val initialScreenCreator: () -> BaseScreen
): Navigator {

	private var result : Event<Any>? = null

	override fun launch(screen: BaseScreen) {
		launchFragment(screen)
	}

	override fun goBack(result: Any?) {
		if (result != null) {
			this.result = Event(result)
		}

		activity.onBackPressed()
	}

	fun onCreate(savedInstanceState: Bundle?){
		if(savedInstanceState == null){
			launchFragment(
				initialScreenCreator(),
				false)
		}

		activity.supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentCallbacks, false)
	}

	fun onDestroy(){
		activity.supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentCallbacks)
	}

	fun launchFragment(screen: BaseScreen, addToBackStack: Boolean = true) {
		val fragment = screen.javaClass.enclosingClass.newInstance() as Fragment

		fragment.arguments = bundleOf(ARG_SCREEN to screen)

		val transaction = activity.supportFragmentManager.beginTransaction()
		if(addToBackStack) transaction.addToBackStack(null)
		transaction
			.setCustomAnimations(
				animations.enterAnim,
				animations.exitAnim,
				animations.popEnterAnim,
				animations.popExitAnim
			)
			.replace(containerId, fragment)
			.commit()
	}

	fun notifyScreenUpdates(){
		val fragmentManager = activity.supportFragmentManager
		val actionBar = activity.supportActionBar
		val f  = fragmentManager.findFragmentById(containerId)

		if (fragmentManager.backStackEntryCount > 0){
			//more than 1 screen -> show BACK button on toolbar
			actionBar?.setDisplayHomeAsUpEnabled(true)
		} else{
			actionBar?.setDisplayHomeAsUpEnabled(false)
		}

		if(f is HasScreenTitle && f.getScreenTitle() != null){
			actionBar?.title = f.getScreenTitle()
		} else{
			actionBar?.title = activity.getString(defaultTitle)
		}
	}

	private fun publishResults(f: Fragment){
		val result = result?.getValue() ?: return
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
			publishResults(f)
		}
	}

	class Animaions(
		@AnimRes val enterAnim: Int,
		@AnimRes val exitAnim: Int,
		@AnimRes val popEnterAnim: Int,
		@AnimRes val popExitAnim: Int,
	)
}