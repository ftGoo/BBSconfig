package com.fantasi.yuqing.BBSconfig.template;

public class Td2Tem {
	    public static String listItemURL = "//TD[@class='td2']/A/@href";
	    public static String replyCount = "//TD[@class='td4']/DIV[1]";
	    public static String reviewCount = "//TD[@class='td4']/DIV[2]";
	    public static String lastReplyAuthor = "//TD[@class='td5']/DIV[1]";
	    public static String lastReplyTime = "//TD[@class='td5']/DIV[2]";
	    public static String listNextPage = "//A[contains(text(),'下一页')]/@href";

	    public static String title = "//TH[@class='th2']";
	    public static String author = "//TD[@class='td1']/DIV[@class='con2']";
	    public static String content = "//TD[@class='td2']/DIV[@class='con2']";
	    public static String postTime = "//TD[@class='td2']/DIV/DIV[@class='fl']";
	    public static String threadNextPage = "//A[contains(text(),'下一页')]/@href";

}
