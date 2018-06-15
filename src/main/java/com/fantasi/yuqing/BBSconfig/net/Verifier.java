package com.fantasi.yuqing.BBSconfig.net;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.transform.TransformerException;

import org.apache.xpath.XPathAPI;
import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

import com.fantasi.yuqing.BBSconfig.util.Domain;

/**
 * Created by Wangbing 2017/2/16
 * 验证xpath模板的正确性
 */
public class Verifier {
	public static Boolean verXpath(byte[] entityContext,String xpathStr){
		//配置DOMParser
		DOMParser parser = new DOMParser();
		try {
			parser.setProperty(
					"http://cyberneko.org/html/properties/names/elems", "upper");
			parser.setProperty(
					"http://cyberneko.org/html/properties/names/attrs", "lower");
			parser.setFeature(
					"http://cyberneko.org/html/features/balance-tags", true);

			parser.setFeature("http://xml.org/sax/features/namespaces", false);
		} catch (SAXNotRecognizedException e) {
			e.printStackTrace();
		} catch (SAXNotSupportedException e) {
			e.printStackTrace();
		}		
		//将网页打印成DOM
		InputSource inputSource = new InputSource(
				new ByteArrayInputStream(entityContext));
		try {
			parser.parse(inputSource);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Document dom = null;
		dom = parser.getDocument();
		if (dom != null) {
			dom.normalize();
		}
		
		//判断是否存在该节点，如果存在则返回true
		Node domtree = dom.getDocumentElement();
		try {
			NodeList nodes = XPathAPI.selectNodeList(domtree,xpathStr);
			if(nodes.item(0)!=null){
				return true;
			}else{
				return false;
			}
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		return false;
	}
	//检测帖子的内容是否和对应的xpath相匹配
	/**
	 * 因为设计的时候没有细想，导致这里的处理繁琐
	 * @param entityContext
	 * @param xpathList url列表xpath表达式
	 * @param xpathStrarticle 打开帖子后的xpath表达式
	 * @param baseurl 版块的url
	 * @return
	 */
	public static boolean articleVer(byte[] entityContext,String xpathList,String xpathStrarticle,String baseurl){
		//配置DOMParser
		DOMParser parser = new DOMParser();
		try {
			parser.setProperty(
					"http://cyberneko.org/html/properties/names/elems", "upper");
			parser.setProperty(
					"http://cyberneko.org/html/properties/names/attrs", "lower");
			parser.setFeature(
					"http://cyberneko.org/html/features/balance-tags", true);

			parser.setFeature("http://xml.org/sax/features/namespaces", false);
		} catch (SAXNotRecognizedException e) {
			e.printStackTrace();
		} catch (SAXNotSupportedException e) {
			e.printStackTrace();
		}		
		//将网页打印成DOM
		InputSource inputSource = new InputSource(
				new ByteArrayInputStream(entityContext));
		try {
			parser.parse(inputSource);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Document dom = null;
		dom = parser.getDocument();
		if (dom != null) {
			dom.normalize();
		}
		
		//判断是否存在该节点，如果存在则返回true
		Node domtree = dom.getDocumentElement();
		try {
			NodeList nodes = XPathAPI.selectNodeList(domtree,xpathList);
			if(nodes.item(0)!=null){
				String url = Domain.
						getAbsoluteURL(nodes.item(0)
								.getTextContent(), baseurl);
				url = url.replaceAll("&amp;", "&");
				byte [] articleContext = Fetcher.fetchHtml(url);
				return Verifier.verXpath(articleContext, xpathStrarticle);
			}else{
				return false;
			}
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		return false;
	}
}
