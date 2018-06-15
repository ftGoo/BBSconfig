package com.fantasi.yuqing.BBSconfig.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Wangbing 2017/2/16
 * 截取域名
 */
public class Domain {
	public static String getHost(String url){
		String host = "";
		//第一步:截取http 或 https 与 第一个 / 之间的字符串;
		Pattern patA = Pattern.compile("^(http://|https://)[^/]*/?");
		Matcher matA = patA.matcher(url);
		if(matA.find()){
			host = matA.group();
			if(host.endsWith("/")){
				host = host.substring(host.indexOf("//") + 2, host.length() - 1);
			} else {
				host = host.substring(host.indexOf("//") + 2);
			}
		}
		//第二步:去掉www.
		if(host.startsWith("www.")){
			host = host.substring(4);
		}
		return host;
	}
	/**
	 * 
	 * @param srcURL
	 *            需要转换的URL
	 * @param baseURL
	 *            srcURL所在的URL
	 * @return
	 */
	public static String getAbsoluteURL(String srcURL, String baseURL) {
		if (baseURL == null || srcURL == null) {
			return srcURL;
		}
		if (srcURL.matches("(?i)mailto:.*"))
			return null;

		srcURL = srcURL.replaceAll("^(?:\"|\\./|\"\\./)|\"$", "");

		if (srcURL.startsWith("http://") || srcURL.startsWith("https://"))
			return srcURL.trim();
		else if (srcURL.matches("(?i)javascript:.*"))
			return null;

		String absoluteURL = null;

		String base_host = baseURL.replaceAll("(https?://[^/]+).*", "$1");
		String base_app = baseURL.replaceAll("https?://[^/]+(/(?:[^/]+/)*)?.*",
				"$1");
		if (!base_app.startsWith("/")) {
			base_app = "/" + base_app;
		}

		if (srcURL.startsWith("/")) {
			absoluteURL = base_host + srcURL;
		} else if (srcURL.startsWith("../")) {
			while (srcURL.startsWith("../")) {
				srcURL = srcURL.substring(3);
				base_app = base_app.replaceAll("[^/]+/$", "");
			}

			absoluteURL = base_host + base_app + srcURL;
		} else if (srcURL.startsWith("?")) {
			if (baseURL.indexOf("?") > 0) {
				absoluteURL = baseURL.substring(0, baseURL.indexOf("?"))
						+ srcURL;
			} else {
				absoluteURL = baseURL + srcURL;
			}
		} else {
			absoluteURL = base_host + base_app + srcURL;
		}
		return absoluteURL;
	}
}
