package com.fantasi.yuqing.BBSconfig.assembly;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fantasi.yuqing.BBSconfig.bean.BroadConfig;
import com.fantasi.yuqing.BBSconfig.bean.Broads;
import com.fantasi.yuqing.BBSconfig.net.CharsetAnalysts;
import com.fantasi.yuqing.BBSconfig.net.Fetcher;
import com.fantasi.yuqing.BBSconfig.net.Verifier;
import com.fantasi.yuqing.BBSconfig.template.BaoliaoTem;
import com.fantasi.yuqing.BBSconfig.template.BtTem;
import com.fantasi.yuqing.BBSconfig.template.CcooTem;
import com.fantasi.yuqing.BBSconfig.template.TR3Tem;
import com.fantasi.yuqing.BBSconfig.template.Td2Tem;
import com.fantasi.yuqing.BBSconfig.template.XstTem;
import com.fantasi.yuqing.BBSconfig.util.AppContext;
import com.fantasi.yuqing.BBSconfig.util.Domain;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
/**
 * Created by wangbing 2017/2/20
 * 配置论坛的流程
 */
public class BBSfactory implements Factory{
	private Configuration configuration =null;
	private Broads broads;
	private Template template=null;
	private String host;
	private String fileName;
	private Map<String,Object> bbsConfig;
	private String path;
	
	public BBSfactory(Broads broads){
		this.broads = broads;
	}
	
	public void assemblyLine(){
		configuration();
		byte[] entityContext = null;
		try{
		entityContext = Fetcher.fetchHtml(broads.getBroadConfigs().get(0).getUrl().replaceAll("&amp;", "&"));
		}catch(Exception e){
			e.printStackTrace();
		}
		if(entityContext == null){
			Auto();
		}
		if(Verifier.verXpath(entityContext, XstTem.listItemURL)){
			XstAuto(entityContext);
		}else if(Verifier.verXpath(entityContext, CcooTem.listItemURL)){
			if(broads.getBroadConfigs().size() == 1){
				CcooAuto(broads.getBroadConfigs());
			}else{
				List<BroadConfig> CcooBroads = broads.getBroadConfigs();
				
				for(int i=0;i<broads.getBroadConfigs().size();i++){
					if(broads.getBroadConfigs().get(i).getName() == "同城爆料"){
						List<BroadConfig> BaoliaoBroad = new ArrayList<BroadConfig>();
						BaoliaoBroad.add(broads.getBroadConfigs().get(i));
						BaoliaoAuto(BaoliaoBroad);
						CcooBroads.remove(i);
					}
				}
				CcooAuto(CcooBroads);
			}
		}else if(Verifier.verXpath(entityContext, BaoliaoTem.listItemURL)){
			if(broads.getBroadConfigs().size() == 1){
				BaoliaoAuto(broads.getBroadConfigs());
			}else{
				List<BroadConfig> CcooBroads = broads.getBroadConfigs();	
			    List<BroadConfig> BaoliaoBroad = new ArrayList<BroadConfig>();
				BaoliaoBroad.add(broads.getBroadConfigs().get(0));
				BaoliaoAuto(BaoliaoBroad);
				CcooBroads.remove(0);
				CcooAuto(CcooBroads);
			}
		}else if(Verifier.verXpath(entityContext, BtTem.listItemURL)){
			BtAuto();
		}else if(Verifier.verXpath(entityContext, TR3Tem.listItemURL)){
			TR3Auto();
		}else if(Verifier.verXpath(entityContext, Td2Tem.listItemURL)){
			TD2Auto();
		}else{
			Auto();
		}
	}
	
	//freemarker的初始设置
	public void configuration(){
		configuration = new Configuration();
		configuration.setDefaultEncoding("utf8");
		configuration.setClassForTemplateLoading(this.getClass(), "/");
		try {
			template = configuration.getTemplate("bbs.ftl");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//DISCUZ论坛的配置
	public void XstAuto(byte []entityContext){
		bbsConfig = new HashMap<String,Object>();
		String url = broads.getBroadConfigs().get(0).getUrl().replaceAll("&amp;", "&");
		host = Domain.getHost(url);
		bbsConfig.put("interval", broads.getInteraval());
		bbsConfig.put("host", host);
		bbsConfig.put("name", broads.getSiteName());
		bbsConfig.put("charset", CharsetAnalysts.getCharset(url));
		bbsConfig.put("maxFetchThreads", broads.getBroadConfigs().size());
		bbsConfig.put("listitemurl", XstTem.listItemURL);
		bbsConfig.put("replycount", XstTem.replyCount);
		bbsConfig.put("reviewcount", XstTem.reviewCount);
		bbsConfig.put("lastRepelyAuthor", XstTem.lastReplyAuthor);
		bbsConfig.put("lastrepelytime", XstTem.lastReplyTime);
		bbsConfig.put("listnextpage", XstTem.listNextPage);
		//标题的多种情形做判断
		if(Verifier.articleVer(entityContext, XstTem.listItemURL ,XstTem.title, url)){
			bbsConfig.put("title", XstTem.title);
			bbsConfig.put("isSure", true);
		}else if(Verifier.articleVer(entityContext, XstTem.listItemURL ,XstTem.titleH2, url)){
			bbsConfig.put("title", XstTem.titleH2);
			bbsConfig.put("isSure", true);
		}else{
			bbsConfig.put("title", "需要手工确认");
			bbsConfig.put("isSure", false);	
		}
		//还是可能遇到author无法匹配的问题，低概率暂不考虑
		bbsConfig.put("author", XstTem.author);
		bbsConfig.put("content", XstTem.content);
		bbsConfig.put("posttime", XstTem.postTime);
		bbsConfig.put("threadnextpage", XstTem.threadNextPage);
		bbsConfig.put("boardConfigs", broads.getBroadConfigs());
		
		//文件处理的部分
		if((Boolean) bbsConfig.get("isSure")){
			path = AppContext.path + "finished/";
		}else{
			path = AppContext.path + "unfinished/";
		}
		fileName = broads.getDefaultGenus() + "_" + host.replace(".", "_") + ".xml";
		writeFile();
		
	}
	//城市在线的配置流程
	public void CcooAuto(List<BroadConfig> broadConfigs){
		bbsConfig = new HashMap<String,Object>();
		String url = broadConfigs.get(0).getUrl().replaceAll("&amp;", "&");
		host = Domain.getHost(url);
		bbsConfig.put("interval", broads.getInteraval());
		bbsConfig.put("host", host);
		bbsConfig.put("name", broads.getSiteName());
		bbsConfig.put("charset", CharsetAnalysts.getCharset(url));
		bbsConfig.put("maxFetchThreads", broadConfigs.size());
		bbsConfig.put("listitemurl", CcooTem.listItemURL);
		bbsConfig.put("replycount", CcooTem.replyCount);
		bbsConfig.put("reviewcount", CcooTem.replyCount);
		bbsConfig.put("lastRepelyAuthor", CcooTem.lastReplyAuthor);
		bbsConfig.put("lastrepelytime", CcooTem.lastReplyTime);
		bbsConfig.put("listnextpage", CcooTem.listNextPage);
		bbsConfig.put("title", CcooTem.title);
		bbsConfig.put("isSure", true);

		//还是可能遇到author无法匹配的问题，低概率暂不考虑
		bbsConfig.put("author", CcooTem.author);
		bbsConfig.put("content", CcooTem.content);
		bbsConfig.put("posttime", CcooTem.postTime);
		bbsConfig.put("threadnextpage", CcooTem.threadNextPage);
		bbsConfig.put("boardConfigs", broadConfigs);
		
		//文件处理的部分
		if((Boolean) bbsConfig.get("isSure")){
			path = AppContext.path + "finished/";
		}else{
			path = AppContext.path + "unfinished/";
		}
		fileName = broads.getDefaultGenus() + "_" + host.replace(".", "_") + ".xml";
		writeFile();
	}
	
	public void BtAuto(){
		bbsConfig = new HashMap<String,Object>();
		String url = broads.getBroadConfigs().get(0).getUrl().replaceAll("&amp;", "&");
		host = Domain.getHost(url);
		bbsConfig.put("interval", broads.getInteraval());
		bbsConfig.put("host", host);
		bbsConfig.put("name", broads.getSiteName());
		bbsConfig.put("charset", CharsetAnalysts.getCharset(url));
		bbsConfig.put("maxFetchThreads", broads.getBroadConfigs().size());
		bbsConfig.put("listitemurl", BtTem.listItemURL);
		bbsConfig.put("replycount", BtTem.replyCount);
		bbsConfig.put("reviewcount", " ");
		bbsConfig.put("lastRepelyAuthor", BtTem.lastReplyAuthor);
		bbsConfig.put("lastrepelytime", BtTem.lastReplyTime);
		bbsConfig.put("listnextpage", BtTem.listNextPage);
		bbsConfig.put("title", XstTem.title);
		bbsConfig.put("isSure", false);

		//还是可能遇到author无法匹配的问题，低概率暂不考虑
		bbsConfig.put("author", BtTem.author);
		bbsConfig.put("content", BtTem.content);
		bbsConfig.put("posttime", BtTem.postTime);
		bbsConfig.put("threadnextpage", BtTem.threadNextPage);
		bbsConfig.put("boardConfigs", broads.getBroadConfigs());
		
		//文件处理的部分
		if((Boolean) bbsConfig.get("isSure")){
			path = AppContext.path + "finished/";
		}else{
			path = AppContext.path + "unfinished/";
		}
		fileName = broads.getDefaultGenus() + "_" + host.replace(".", "_") + ".xml";
		writeFile();
		
	}
	
	public void BaoliaoAuto(List<BroadConfig> broadConfigs){
		bbsConfig = new HashMap<String,Object>();
		String url = broadConfigs.get(0).getUrl().replaceAll("&amp;", "&");
		host = Domain.getHost(url);
		bbsConfig.put("interval", broads.getInteraval());
		bbsConfig.put("host", host);
		bbsConfig.put("name", broads.getSiteName());
		bbsConfig.put("charset", CharsetAnalysts.getCharset(url));
		bbsConfig.put("maxFetchThreads", broadConfigs.size());
		bbsConfig.put("listitemurl", BaoliaoTem.listItemURL);
		bbsConfig.put("replycount", BaoliaoTem.replyCount);
		bbsConfig.put("reviewcount", BaoliaoTem.replyCount);
		bbsConfig.put("lastRepelyAuthor", BaoliaoTem.lastReplyAuthor);
		bbsConfig.put("lastrepelytime", BaoliaoTem.lastReplyTime);
		bbsConfig.put("listnextpage", BaoliaoTem.listNextPage);
		bbsConfig.put("title", BaoliaoTem.title);
		bbsConfig.put("isSure", true);

		//还是可能遇到author无法匹配的问题，低概率暂不考虑
		bbsConfig.put("author", BaoliaoTem.author);
		bbsConfig.put("content", BaoliaoTem.content);
		bbsConfig.put("posttime", BaoliaoTem.postTime);
		bbsConfig.put("threadnextpage", BaoliaoTem.threadNextPage);
		bbsConfig.put("boardConfigs", broadConfigs);
		
		//文件处理的部分
		if((Boolean) bbsConfig.get("isSure")){
			path = AppContext.path + "finished/";
		}else{
			path = AppContext.path + "unfinished/";
		}
		fileName = broads.getDefaultGenus() + "_" + host.replace(".", "_") + ".xml";
		writeFile();
	}
	
	public void TD2Auto(){
		bbsConfig = new HashMap<String,Object>();
		String url = broads.getBroadConfigs().get(0).getUrl().replaceAll("&amp;", "&");
		host = Domain.getHost(url);
		bbsConfig.put("interval", broads.getInteraval());
		bbsConfig.put("host", host);
		bbsConfig.put("name", broads.getSiteName());
		bbsConfig.put("charset", CharsetAnalysts.getCharset(url));
		bbsConfig.put("maxFetchThreads", broads.getBroadConfigs().size());
		bbsConfig.put("listitemurl", Td2Tem.listItemURL);
		bbsConfig.put("replycount", Td2Tem.replyCount);
		bbsConfig.put("reviewcount", Td2Tem.reviewCount);
		bbsConfig.put("lastRepelyAuthor", Td2Tem.lastReplyAuthor);
		bbsConfig.put("lastrepelytime", Td2Tem.lastReplyTime);
		bbsConfig.put("listnextpage", Td2Tem.listNextPage);
		bbsConfig.put("title", Td2Tem.title);
		bbsConfig.put("isSure", false);

		//还是可能遇到author无法匹配的问题，低概率暂不考虑
		bbsConfig.put("author", Td2Tem.author);
		bbsConfig.put("content", Td2Tem.content);
		bbsConfig.put("posttime", Td2Tem.postTime);
		bbsConfig.put("threadnextpage", Td2Tem.threadNextPage);
		bbsConfig.put("boardConfigs", broads.getBroadConfigs());
		
		//文件处理的部分
		if((Boolean) bbsConfig.get("isSure")){
			path = AppContext.path + "finished/";
		}else{
			path = AppContext.path + "unfinished/";
		}
		fileName = broads.getDefaultGenus() + "_" + host.replace(".", "_") + ".xml";
		writeFile();
	}
	
	public void TR3Auto(){
		bbsConfig = new HashMap<String,Object>();
		String url = broads.getBroadConfigs().get(0).getUrl().replaceAll("&amp;", "&");
		host = Domain.getHost(url);
		bbsConfig.put("interval", broads.getInteraval());
		bbsConfig.put("host", host);
		bbsConfig.put("name", broads.getSiteName());
		bbsConfig.put("charset", CharsetAnalysts.getCharset(url));
		bbsConfig.put("maxFetchThreads", broads.getBroadConfigs().size());
		bbsConfig.put("listitemurl", TR3Tem.listItemURL);
		bbsConfig.put("replycount", TR3Tem.replyCount);
		bbsConfig.put("reviewcount", TR3Tem.reviewCount);
		bbsConfig.put("lastRepelyAuthor", TR3Tem.lastReplyAuthor);
		bbsConfig.put("lastrepelytime", TR3Tem.lastReplyTime);
		bbsConfig.put("listnextpage", TR3Tem.listNextPage);
		bbsConfig.put("title", TR3Tem.title);
		bbsConfig.put("isSure", false);

		//还是可能遇到author无法匹配的问题，低概率暂不考虑
		bbsConfig.put("author", TR3Tem.author);
		bbsConfig.put("content", TR3Tem.content);
		bbsConfig.put("posttime", TR3Tem.postTime);
		bbsConfig.put("threadnextpage", TR3Tem.threadNextPage);
		bbsConfig.put("boardConfigs", broads.getBroadConfigs());
		
		//文件处理的部分
		if((Boolean) bbsConfig.get("isSure")){
			path = AppContext.path + "finished/";
		}else{
			path = AppContext.path + "unfinished/";
		}
		fileName = broads.getDefaultGenus() + "_" + host.replace(".", "_") + ".xml";
		writeFile();
	}
	
	//以上模板都不可用的情况
	public void Auto(){
		bbsConfig = new HashMap<String,Object>();
		String url = broads.getBroadConfigs().get(0).getUrl().replaceAll("&amp;", "&");
		host = Domain.getHost(url);
		bbsConfig.put("interval", broads.getInteraval());
		bbsConfig.put("host", host);
		bbsConfig.put("name", broads.getSiteName());
		bbsConfig.put("charset", CharsetAnalysts.getCharset(url));
		bbsConfig.put("maxFetchThreads", broads.getBroadConfigs().size());
		bbsConfig.put("listitemurl", " ");
		bbsConfig.put("replycount", " ");
		bbsConfig.put("reviewcount", " ");
		bbsConfig.put("lastRepelyAuthor", " ");
		bbsConfig.put("lastrepelytime", " ");
		bbsConfig.put("listnextpage", " ");
		bbsConfig.put("title", " ");
		bbsConfig.put("isSure", false);

		//还是可能遇到author无法匹配的问题，低概率暂不考虑
		bbsConfig.put("author", " ");
		bbsConfig.put("content", " ");
		bbsConfig.put("posttime", " ");
		bbsConfig.put("threadnextpage", " ");
		bbsConfig.put("boardConfigs", broads.getBroadConfigs());
		
		//文件处理的部分
		if((Boolean) bbsConfig.get("isSure")){
			path = AppContext.path + "finished/";
		}else{
			path = AppContext.path + "unfinished/";
		}
		fileName = broads.getDefaultGenus() + "_" + host.replace(".", "_") + ".xml";
		writeFile();
	}
	
	public void writeFile(){
		
		String dirPath = path 
						+ broads.getCityName()
						+ "/"
						+ broads.getCityLevel_2Name()
						+ "/";
		File dir = new File(dirPath);
		dir.mkdirs();
		File file = new File(dirPath + fileName);		
		if(file.exists()){
			 fileName = broads.getDefaultGenus() 
					  + "_" + host.replace(".", "_") 
					  + (int)(Math.random()*100) 
					  + ".xml";
			 
			 writeFile();
		}
		Writer writer;

		try {
			writer = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
			template.process(bbsConfig, writer);
			writer.close();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		AppContext.init();
		Broads broads = new Broads();
		broads.setSiteName("测试站点");
		broads.setCityName("无锡");
		broads.setCityLevel_2Name("柳州");
		broads.setDefaultGenus("c520");
		//broads.setInteraval(20);
		broads.setPeople(true);
		BroadConfig broadConfig = new BroadConfig();
		broadConfig.setUrl("http://www.05348.com/forum-79-1.html");
		broadConfig.setName("测试");
		broadConfig.setGenus("c510");
		List<BroadConfig> b = new ArrayList<BroadConfig>();
		b.add(broadConfig);
		broads.setBroadConfigs(b);
		Sitefactory bbsFactory = new Sitefactory(broads);
		bbsFactory.assemblyLine();
	}
}
