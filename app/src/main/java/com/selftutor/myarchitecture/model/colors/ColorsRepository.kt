package com.selftutor.myarchitecture.model.colors

import com.selftutor.foundation.model.Repository

typealias ColorListener = (NamedColor) -> Unit

/**
 * Provides access to all available colors and selected color
 */
interface ColorsRepository: Repository {

	var currentColor: NamedColor

	fun getAvailableColors(): List<NamedColor>

	fun getById(id: Long): NamedColor

	fun addListener(listener: ColorListener)

	fun removeListener(listener: ColorListener)
}