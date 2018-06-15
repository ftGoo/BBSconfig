package com.fantasi.yuqing.BBSconfig.bean;

import java.util.List;

public class Broads {
	private List<BroadConfig> broadConfigs;
	//采集周期
	private String interaval;
	//城市名
	private String cityName;
	//县城、县级市名
	private String cityLevel_2Name;
	//默认地区码
	private String defaultGenus;
	//网站名称
	private String siteName;
	//这个布尔值只针对贴吧
	private boolean isH1;
	private boolean isPeople;
	
	public String getCityLevel_2Name() {
		return cityLevel_2Name;
	}

	public void setCityLevel_2Name(String cityLevel_2Name) {
		this.cityLevel_2Name = cityLevel_2Name;
	}

	public String getDefaultGenus() {
		return defaultGenus;
	}

	public void setDefaultGenus(String defaultGenus) {
		this.defaultGenus = defaultGenus;
	}

	public List<BroadConfig> getBroadConfigs() {
		return broadConfigs;
	}

	public void setBroadConfigs(List<BroadConfig> broadConfigs) {
		this.broadConfigs = broadConfigs;
	}

	public String getInteraval() {
		return interaval;
	}

	public void setInteraval(String interaval) {
		this.interaval = interaval;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public boolean isH1() {
		return isH1;
	}

	public void setH1(boolean isH1) {
		this.isH1 = isH1;
	}

	public boolean isPeople() {
		return isPeople;
	}

	public void setPeople(boolean isPeople) {
		this.isPeople = isPeople;
	}
	
}
