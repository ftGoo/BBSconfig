package com.fantasi.yuqing.BBSconfig.net;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;

import com.fantasi.yuqing.BBSconfig.template.XstTem;
import com.fantasi.yuqing.BBSconfig.util.AppContext;

/**
 * Created by Wangbing 2017/2/16
 * 分析目标网页的编码标准
 */
public class CharsetAnalysts {
	public static String getCharset(String url){
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = null;
		try {
			response = AppContext.getClient().execute(httpGet);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Header headers[] = response.getAllHeaders();
		
		//鉴于大部分论坛都是gbk编码，少部分为utf8
        for(int i=0;i<headers.length;i++){

        	if(headers[i].getName().equals("Content-Type")){
        		if(headers[i].getValue().contains("UTF-8")
        			||headers[i].getValue().contains("utf-8")){
        			return "utf8";
        		}
        	}
        }
		return "gbk";
	}
	
	public static void main(String[] args) {
		AppContext.init();
		System.out.println(CharsetAnalysts.getCharset("http://bbs.365jia.cn/forum-1-1.html"));
		System.out.println(CharsetAnalysts.getCharset("http://bbs.zhiyoo.com/"));
		byte [] context = Fetcher.fetchHtml("http://bbs.365jia.cn/forum-1-1.html");
		System.out.println(Verifier.verXpath(context, XstTem.author));
	}
}
