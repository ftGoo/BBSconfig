package com.fantasi.yuqing.BBSconfig.net;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import com.fantasi.yuqing.BBSconfig.util.AppContext;

/**
 * Created by wangbing 2017/2/20
 * 用于抓取网页字节流
 */
public class Fetcher {
	public static byte[] fetchHtml(String url){
		byte[] entityContext = null;
		HttpGet httpget = new HttpGet(url);
		CloseableHttpResponse response = null;
		try {
			response = AppContext
						.getClient()
						.execute(httpget);
			HttpEntity entity = response.getEntity();
			entityContext = EntityUtils.toByteArray(entity);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return entityContext;
	}
}
