package com.jiuwu.guojingbu.guoguoweather.utils;


import okhttp3.MediaType;

public class HttpConfig {
	/**
	 * 连接超时
	 */
	public static final int DEFAULT_CONNECTION_TIMEOUT = 10;
	/**
	 * 读取超时
	 */
	public static final int DEFAULT_READ_TIMEOUT = 10;
	/**
	 * 写入超时
	 */
	public static final int DEFAULT_WRITE_TIMEOUT = 10;
	/**
	 * 字符集的请求
	 */
	public static final String PROTOCOL_CHARSET = "utf-8";
	/**
	 * 字符集的请求
	 */
	public static final String HEADER_ACCEPT = "Accept";
	/**
	 * HTTP响应的内容类型
	 */
	public static final String HEADER_CONTENT_TYPE = "Content-Type";
	/**
	 * HTTP授权
	 */
	public static final String HEADER_AUTHORIZATION = "Authorization";
	/**
	 * 内容类型的请求
	 */
	public static final String PROTOCOL_CONTENT_TYPE = String.format(
			"application/json; charset=%s", PROTOCOL_CHARSET);
	/**
	 * okHttp格式
	 */
	public static final MediaType mMediaType = MediaType
			.parse(PROTOCOL_CONTENT_TYPE);
}
