package com.asiainfo.activiti.generate.util.paranamer;

import java.lang.reflect.AccessibleObject;

public class NullParanamer implements Paranamer {

	public String[] lookupParameterNames(AccessibleObject methodOrConstructor) {
		return new String[0];
	}

	public String[] lookupParameterNames(AccessibleObject methodOrConstructor, boolean throwExceptionIfMissing) {
		if (throwExceptionIfMissing) {
			throw new ParameterNamesNotFoundException(
					"NullParanamer implementation predictably finds no parameter names");
		}
		return new String[0];
	}
}