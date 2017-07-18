package com.xml;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Vector;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.helper.JarHelper;

public class XmlReader {
	static Document document;
	static boolean validating;

	public XmlReader() {
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
	public String readJar(String filename){
		String jar="";
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringElementContentWhitespace(true);
			factory.setValidating(validating);
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(new File(filename));
			Element root = document.getDocumentElement();
			jar = root.getAttribute("jar");
			System.out.println(jar);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jar;
	}
	public static String readAttribute(String filename,String key) throws ParserConfigurationException{
		String value = "";
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);
		factory.setValidating(validating);
		DocumentBuilder builder = factory.newDocumentBuilder();

		try {
			document = builder.parse(new File(filename));
			Element root = document.getDocumentElement();
			value = root.getAttribute(key);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
	public Vector<UiBean> toReadIsSelected(String filename){
		Vector<UiBean> beans = toRead(filename);
		Vector<UiBean> select_beans = new Vector<UiBean>();
		for (UiBean uiBean : beans) {
			if (Boolean.parseBoolean(uiBean.getSelected().trim())){
				select_beans.add(uiBean);
			}
		}
		return select_beans;
	}
	public Vector<UiBean> toRead(String filename)
	{
		//System.out.println("start Read xml: "+filename);
		Vector<UiBean> mkVector=new Vector<UiBean>();
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setIgnoringElementContentWhitespace(true);
			factory.setValidating(validating);
			DocumentBuilder builder = factory.newDocumentBuilder();

			document = builder.parse(new File(filename));

			Element root = document.getDocumentElement();
			//System.out.println(root.getAttribute("name"));
			//System.out.println(root.getAttribute("version"));
			//System.out.println(root.getAttribute("jar"));
			NamedNodeMap attr;
			NodeList children = root.getElementsByTagName("testcase");
			attr = root.getAttributes();

			if(attr!=null)
			{
				for(int i = 0;i<children.getLength();i++)
				{
					UiBean mkbean = new UiBean();
					Element element= (Element) children.item(i);
					NodeList list = element.getChildNodes();
					Node child = list.item(i);
					mkbean.setSelected(list.item(1).getTextContent());
					mkbean.setScript(list.item(3).getTextContent());
					mkbean.setSummary(list.item(5).getTextContent());
					mkbean.setDevice(list.item(7).getTextContent());
					mkbean.setRandomid(list.item(9).getTextContent());
					mkbean.setEs(list.item(11).getTextContent());
					mkbean.setSuitName(list.item(13).getTextContent());
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
		String xml = "f:\\ATT\\config\\config1.xml";
		XmlReader my = new XmlReader();
		my.toRead(xml);
		
		XmlReader mys = new XmlReader();
		mys.readJar(xml);
	}
} 