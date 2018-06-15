package com.fantasi.yuqing.BBSconfig.template;

public class BaoliaoTem {
	public static String listItemURL = 
			"//DIV[@class='baol_biaoti']/A/@href";
	public static String replyCount = 
			"//DIV[@class='baol_sj']/DIV[@class='baol_gz']/text()[1]";
	public static String reviewCount = 
			"//DIV[@class='baol_sj']/DIV[@class='baol_gz']/SPAN";
	public static String lastReplyAuthor = 
			"//DIV[@class='baol_sj']/DIV[@class='baol_by']/A";
	public static String lastReplyTime =
			"//DIV[@class='baol_sj']/DIV[@class='baol_by']/text()[1]";
	public static String listNextPage = 
			"//DIV[@class='fy']/DIV[@class='fy']/A[contains(text(),'下一页')]/@href";
	public static String title =
			"//DIV[@class='topic_title_l']/H1/text()";
	public static String author =
			"//DIV[@class='topic_name']";
	public static String content =
			"//TD[@class='topic_c']";
	public static String postTime =
			"//DIV[contains(@class,'menber')]/UL/LI[contains(text(),'发表于')]";
	public static String threadNextPage =
			"//DIV[@class='fy']/DIV[@class='fy']/A[contains(text(),'下一页')]/@href";
}
