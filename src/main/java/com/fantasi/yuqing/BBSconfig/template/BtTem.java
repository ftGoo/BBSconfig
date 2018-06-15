package com.fantasi.yuqing.BBSconfig.template;

public class BtTem {
    public static String listItemURL = "//A[@class='bt']/@href";
    public static String replyCount = "//DD[@class='tj']/STRONG</replyCount";
    public static String lastReplyAuthor = "(//DD[@class='hf']/A)|(//DD[@class='hf']/SPAN)";
    public static String lastReplyTime = "//DD[@class='hf']/EM";
    public static String listNextPage = "//A[contains(text(),'下一页')]/@href</listNextPage>";


    public static String title = "//H1/text()";
    public static String author = "(//A[@class='cBlack'])|(//DIV[@class='l'])";
    public static String content = "(//DIV[@class='nr'])|(//DIV[@class='locked'])";
    public static String postTime = "//DIV[@class='sj']";
    public static String threadNextPage = "//A[contains(text(),'>')]/@href";

}
