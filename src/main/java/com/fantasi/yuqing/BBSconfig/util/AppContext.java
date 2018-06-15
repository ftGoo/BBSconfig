package com.fantasi.yuqing.BBSconfig.util;

import org.apache.http.impl.client.CloseableHttpClient;
import com.fantasi.yuqing.BBSconfig.net.BBSclient;

/**
 * Created by Wangbing 2017/2/16
 * 环境配置
 */
public class AppContext {
	
	private static CloseableHttpClient client;
	//这里设置个filepath,若为null的话将启用默认的一个路径作为保存xml文档
	public static String path ;
	public static String defaultPath = "C:/XmlFile/";
	public static void init(){
		BBSclient bbsClient = new BBSclient();
		setClient(bbsClient.initClient());
		if(AppContext.path == null){
			AppContext.path = AppContext.defaultPath;
		}
	}

	public static CloseableHttpClient getClient() {
		return client;
	}

	public static void setClient(CloseableHttpClient client) {
		AppContext.client = client;
	}
}
