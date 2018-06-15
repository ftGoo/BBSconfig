package com.fantasi.yuqing.BBSconfig.assembly;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.fantasi.yuqing.BBSconfig.bean.Broads;
import com.fantasi.yuqing.BBSconfig.util.AppContext;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class Xicifactory implements Factory{
	private Configuration configuration =null;
	private Broads broads;
	private Template template=null;
	private String fileName;
	private Map<String,Object> bbsConfig;
	private String path;
	
	public Xicifactory(Broads broads){
		this.broads = broads;
	}
	
	public void assemblyLine(){
			configuration("xici.ftl");
			Auto();
	}
	public void Auto(){
		bbsConfig = new HashMap<String,Object>();
		bbsConfig.put("maxFetchThreads", broads.getBroadConfigs().size());
		bbsConfig.put("boardConfigs", broads.getBroadConfigs());
		bbsConfig.put("interval", broads.getInteraval());
		bbsConfig.put("isSure", true);
		
		path = AppContext.path 
				          + "tiebah/";
		
		fileName = broads.getDefaultGenus() + "_" + "xici" + ".xml";
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
					  + "_" + "xici"
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
