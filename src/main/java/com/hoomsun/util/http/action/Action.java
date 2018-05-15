package com.hoomsun.util.http.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hoomsun.util.message.ResBody;

public abstract interface Action {
	public abstract ResBody perform(HttpServletRequest request,
			HttpServletResponse response);
}
