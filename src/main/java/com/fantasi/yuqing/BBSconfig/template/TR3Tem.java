package com.fantasi.yuqing.BBSconfig.template;

public class TR3Tem {
	public static String listItemURL = 
			"//TBODY[@id='threadlist']/TR[contains(@class,'tr3')]/TD[contains(@class,'subject')]/A[contains(@class,'subject')]/@href";
	public static String replyCount = 
			"//TBODY[@id='threadlist']/TR/TD[contains(@class,'num')]/EM";
	public static String reviewCount = 
			"//TBODY[@id='threadlist']/TR/TD[contains(@class,'num')]/text()";
	public static String lastReplyAuthor = 
			"//TBODY[@id='threadlist']/TR/TD[contains(@class,'author')][2]/A";
	public static String lastReplyTime =
			"(//TBODY[@id='threadlist']/TR/TD[contains(@class,'author')][2]/P[not(SPAN)])|(//TBODY[@id='threadlist']/TR/TD[contains(@class,'author')][2]/P/SPAN/@title)";
	public static String listNextPage = 
			"//DIV[@class='pages']/B/following-sibling::A[1]/@href";
	public static String title =
			"//H1";
	public static String author =
			"//DIV[contains(@class,'readName')]/A";
	public static String content =
			"//DIV[@class='tpc_content']/DIV[contains(@id,'read_')]";
	public static String postTime =
			"//DIV[contains(@class,'tipTop')]/SPAN[@title]";
	//不是这个，但是为空的时候会报错
	public static String threadNextPage =
			"//A[@class='nxt']/@href";
}
