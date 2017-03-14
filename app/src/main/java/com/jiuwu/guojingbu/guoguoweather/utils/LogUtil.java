package com.jiuwu.guojingbu.guoguoweather.utils;

import android.util.Log;

/**
 * log 输出管理类
 * 
 * @author guojingbu
 *
 */
public class LogUtil {
	/**
	 * tag
	 */
	private static final String Tag = "YESWAY";
	/**
	 * LOG输出开关，true=输出，false=禁止
	 */
	private static boolean isDebug = true;
	
	
	/**
	 * debug级别日志
	 * 
	 * @param msg
	 */
	public static void d(String msg) {
		if (isDebug) {
			d(Tag, msg);
		}
	}

	/**
	 * debug级别日志
	 * 
	 * @param tag
	 * @param msg
	 */
	public static void d(String tag, String msg) {
		if (isDebug) {
			Log.d(tag, "------>" + msg);
		}
	}

	/**
	 * warn级别日志输出
	 * 
	 * @param tag
	 * @param msg
	 */
	public static void w(String tag, String msg) {
		if (isDebug) {
			Log.w(tag, "------>" + msg);
		}
	}

	/**
	 * warn 级别日志输出
	 * 
	 * @param msg
	 */
	public static void w(String msg) {
		if (isDebug) {
			w(Tag, msg);
		}
	}

	/**
	 * 异常输出
	 * 
	 * @param ex
	 */
	public static void e(String tag,String msg) {
		if (isDebug) {
			Log.e(tag,"------>"+msg);
		}
	}

	/**
	 * info 级别的日志输出
	 * 
	 * @param tag
	 * @param msg
	 */
	public static void i(String tag, String msg) {
		if (isDebug) {
			Log.i(tag, "------>" + msg);
		}
	}

	/**
	 * info 级别的日志输出
	 * 
	 * @param msg
	 */
	public static void i(String msg) {
		if (isDebug) {
			i(Tag, msg);
		}
	}

	/**
	 * verbose 级别日志打印
	 * 
	 * @param tag
	 * @param msg
	 */
	public static void v(String tag, String msg) {
		if (isDebug) {
			Log.v(tag, "------>" + msg);
		}
	}

	/**
	 * verbose 级别日志打印
	 * 
	 * @param tag
	 */
	public static void v(String msg) {
		if (isDebug) {
			v(Tag, msg);
		}
	}

	/**
	 * 控制台输出日志打印
	 * 
	 * @param msg
	 */
	public static void systemOutPrintln(String msg) {
		if (isDebug) {
			System.out.println("------>" + msg);
		}
	}

}