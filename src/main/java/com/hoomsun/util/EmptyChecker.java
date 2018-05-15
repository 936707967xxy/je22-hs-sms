package com.hoomsun.util;

import java.util.Collection;
import java.util.Date;
import java.util.Dictionary;
import java.util.Map;

/**
 * 
 * 工具类_提供基本对象、集合、数组的空值、非空值检查。
 * 
 * @author 张开
 * 
 */
public class EmptyChecker {
	// 对于Collection、Dictionary、Map，不深入迭代，判断有没有子元素。
	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		if (obj instanceof String) {
			return ((String) obj) == null || "".equals(obj) ? true : false;
		}
		if (obj instanceof Collection) {
			return ((Collection<?>) obj).isEmpty();
		}
		if (obj instanceof Dictionary) {
			return ((Dictionary<?, ?>) obj).isEmpty();
		}
		if (obj instanceof Map) {
			return ((Map<?, ?>) obj).isEmpty();
		}

		return false;
	}
	
	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}

	// 为空的默认值转换
	public static String emptyDefaultValue(String obj, String defaultValue) {
		if (isEmpty(obj)) {
			return defaultValue;
		} else {
			return obj;
		}
	}

	public static int emptyDefaultValue(Integer value) {
		if (isEmpty(value)) {
			return 0;
		} else {
			return value;
		}
	}

	public static long emptyDefaultValue(Long value) {
		if (isEmpty(value)) {
			return 0L;
		} else {
			return value;
		}
	}

	public static int emptyDefaultValue(String obj, Integer defaultValue) {
		if (isEmpty(obj)) {
			return defaultValue;
		} else {
			return Integer.valueOf(obj);
		}
	}

	public static long emptyDefaultValue(String obj, Long defaultValue) {
		if (isEmpty(obj)) {
			return 0L;
		} else {
			return Long.valueOf(obj);
		}
	}

	public static long emptyDefaultValue(Date date) {
		if (isEmpty(date)) {
			return 0L;
		} else {
			return date.getTime();
		}
	}

	public static boolean emptyDefaultValue(Boolean bool) {
		if (isEmpty(bool)) {
			return false;
		} else {
			return bool;
		}
	}
}
