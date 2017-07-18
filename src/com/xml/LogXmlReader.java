package com.xml;
import java.io.File;
import java.io.FileInputStream;
import java.util.Vector;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.helper.JarHelper;

public class LogXmlReader {
	static Document document;
	static boolean validating;

	public LogXmlReader() {
	}
	public static String toReadRootAttr(String filename,String attrKey){
		String attrValue="";
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringElementContentWhitespace(true);
			factory.setValidating(validating);
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(new FileInputStream(filename));
			Element root = document.getDocumentElement();
			attrValue = root.getAttribute(attrKey);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return attrValue;
	}
	public Vector<LogBean> toRead(String filename)
	{
		System.out.println("start Read xml: "+filename);
		Vector<LogBean> mkVector=new Vector<LogBean>();
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringElementContentWhitespace(true);
			factory.setValidating(validating);
			DocumentBuilder builder = factory.newDocumentBuilder();

			document = builder.parse(new File(filename));
			//document = builder.parse(new FileInputStream(filename),"GB2312");
			
			Element root = document.getDocumentElement();
			NamedNodeMap attr;
			NodeList children = root.getElementsByTagName("testcase");
			attr = root.getAttributes();

			if(attr!=null)
			{
				for(int i = 0;i<children.getLength();i++)
				{
					LogBean mkbean = new LogBean();
					Element element= (Element) children.item(i);
					NodeList list = element.getChildNodes();
					Node child = list.item(i);
					mkbean.setLogAugument(list.item(1).getTextContent());
					mkbean.setRandomid(list.item(3).getTextContent());
					mkbean.setSuitName(list.item(5).getTextContent());
					mkbean.setCaseName(list.item(7).getTextContent());
					mkbean.setSummary(list.item(9).getTextContent());
					mkVector.add(mkbean);
					mkbean.toString();
				}
			}
		}
		catch (Exception exp)
		{
			exp.printStackTrace();
			return null;
		}
		return mkVector;
	}

	public static void main(String[] args) {
		/*Vector<CaseBean> A = null;
		XmlReader my = new XmlReader();
		A = my.toRead(Constant.writexml);
		for (CaseBean monkeyBean : A) {
			monkeyBean.toString();
		}
		System.out.println("================:"+toReadRootAttr(Constant.writexml,"name"));
		System.out.println("================:"+toReadRootAttr(Constant.writexml,"version"));
	*/
		String xml = "E:\\ATT\\log\\log.xml";
		LogXmlReader my = new LogXmlReader();
		my.toRead(xml);
	}
} 