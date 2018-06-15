package com.fantasi.yuqing.BBSconfig.net;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * Created by Wangbing 2017/2/16
 * HttpClient
 */
public class BBSclient {
	private CloseableHttpClient client;
	private static final String USER_AGENT=
			"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; Foxy/1; .NET CLR 2.0.50727; MEGAUPLOAD 1.0)";
	private static final int POOL_SIZE=200;
	private static final int MAX_REDIRECTS=10;
	
	public CloseableHttpClient getClient() {
		return client;
	}

	public void setClient(CloseableHttpClient client) {
		this.client = client;
	}

	public  CloseableHttpClient initClient(){
		RequestConfig config=RequestConfig.custom()
				.setMaxRedirects(MAX_REDIRECTS)
				.setCircularRedirectsAllowed(false)
				.setConnectTimeout(300000)
				.setSocketTimeout(300000)
				.build();
		
		 client = HttpClients.custom()
				 	.setUserAgent(USER_AGENT)
				 	.setMaxConnTotal(POOL_SIZE)
			        .setMaxConnPerRoute(POOL_SIZE)
			        .setDefaultRequestConfig(config).build();
		return client;
	}
}
