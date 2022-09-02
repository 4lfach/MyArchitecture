package com.selftutor.foundation

import com.selftutor.foundation.model.Repository

interface BaseApplication {
	val repositories: List<Repository>
}