package com.fantasi.yuqing.BBSconfig.template;

public class CcooTem {
	public static String listItemURL = 
			"//DIV[@class='topic_show_tit']/A/@href";
	public static String replyCount = 
			"//DIV[@class='topic_show_num']/SPAN";
	public static String lastReplyAuthor = 
			"//DIV[@class='topic_show_relpay']";
	public static String lastReplyTime =
			"//DIV[@class='topic_relpay_time'][2]";
	public static String listNextPage = 
			"//A[contains(text(),'下一页')]/@href";
	public static String title =
			"//H1";
	public static String author =
			"//DIV[@class='topic_name']";
	public static String content =
			"//TD[@class='topic_c']";
	public static String postTime =
			"//DIV[contains(@class,'menber')]/UL/LI[last()]";
	public static String threadNextPage =
			"//A[contains(text(),'下一页')]/@href";
}
