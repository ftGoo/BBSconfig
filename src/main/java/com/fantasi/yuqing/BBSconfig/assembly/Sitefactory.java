package com.fantasi.yuqing.BBSconfig.assembly;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.fantasi.yuqing.BBSconfig.bean.Broads;
import com.fantasi.yuqing.BBSconfig.net.CharsetAnalysts;
import com.fantasi.yuqing.BBSconfig.util.AppContext;
import com.fantasi.yuqing.BBSconfig.util.Domain;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Created by Wangbing 2017/3/8
 * 固定站点和人民网留言版的配置
 */
public class Sitefactory implements Factory{
	private Configuration configuration =null;
	private Broads broads;
	private Template template=null;
	private String fileName;
	private String host;
	private Map<String,Object> bbsConfig;
	private String path;
	
	public Sitefactory(Broads broads){
		this.broads = broads;
	}
	
	public void assemblyLine(){
		
		if(broads.isPeople()){
			configuration("liuyan_people.ftl");
			peopleAuto();
		}else{
			configuration("site.ftl");
			auto();
		}
	}
	public void auto(){
		bbsConfig = new HashMap<String,Object>();
		String url = broads.getBroadConfigs().get(0).getUrl().replaceAll("&amp;", "&");
		host = Domain.getHost(url);
		bbsConfig.put("interval", broads.getInteraval());
		bbsConfig.put("host", host);
		bbsConfig.put("name", broads.getSiteName());
		bbsConfig.put("charset", CharsetAnalysts.getCharset(url));
		bbsConfig.put("maxFetchThreads", broads.getBroadConfigs().size());
		bbsConfig.put("interval", broads.getInteraval());
		bbsConfig.put("boardConfigs", broads.getBroadConfigs());
		path = AppContext.path + "site/";
		
		fileName = broads.getDefaultGenus() + "_" + host.replace(".", "_") + ".xml";
		writeFile();
	}
	
	public void peopleAuto(){
		bbsConfig = new HashMap<String,Object>();
		bbsConfig.put("interval", broads.getInteraval());
		bbsConfig.put("maxFetchThreads", broads.getBroadConfigs().size());
		bbsConfig.put("boardConfigs", broads.getBroadConfigs());
		path = AppContext.path + "site/";
		
		fileName = broads.getDefaultGenus() + "_" + "liuyan_peole" + ".xml";
		writeFile();
	}
	
	public void configuration(String file){
		configuration = new Configuration();
		configuration.setDefaultEncoding("utf8");
		configuration.setClassForTemplateLoading(this.getClass(), "/");
		try {
			template = configuration.getTemplate(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
					  + "_" 
		              + broads.getDefaultGenus() 
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
}
