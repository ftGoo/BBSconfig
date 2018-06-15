package com.fantasi.yuqing.BBSconfig.template;
/**
 * Created by wangbing 2017/2/17
 * Discuz的s xst大类的模板
 */
public class XstTem {
	public static String listItemURL = 
			"//TBODY[contains(@id,'normalthread_')]/TR/TH/A[contains(@class,'xst')]/@href";
	public static String replyCount = 
			"//TBODY[contains(@id,'normalthread_')]/TR/TD[@class='num']/A";
	public static String reviewCount = 
			"//TBODY[@id='threadlist']/TR/TD[@class='num']/text()";
	public static String lastReplyAuthor = 
			"//TBODY[contains(@id,'normalthread_')]/TR/TD[contains(@class,'by')][2]/CITE";
	public static String lastReplyTime =
			"(//TBODY[contains(@id,'normalthread_')]/TR/TD[contains(@class,'by')][2]/EM/A/SPAN/@title)|(//TBODY[contains(@id,'normalthread_')]/TR/TD[contains(@class,'by')][2]/EM/A[not (SPAN)])";
	public static String listNextPage = 
			"//A[@class='nxt']/@href";
	public static String title =
			"//H1/*[contains(@id,'thread_subject')]";
	public static String titleH2 =
			"//H2/*[contains(@id,'thread_subject')]";
	public static String author =
			"//DIV[@class='authi']/A[1]/text()";
	public static String authorxi2 =
			"DIV[@class='authi']/A[@class='xi2']/text()";
	public static String content =
			"(//TD[contains(@id,'postmessage')])|(//DIV[@class='locked'])";
	public static String postTime =
			"(//DIV[@class='pti']/DIV[@class='authi']/EM[not(SPAN)])|(//DIV[@class='pti']/DIV[@class='authi']/EM/SPAN/@title)";
	public static String threadNextPage =
			"//A[@class='nxt']/@href";
}
