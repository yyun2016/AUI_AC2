package com.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlUpdate {
	public static boolean doc2XmlFile(Document document, String filename) {
		boolean flag = true;
		try {

			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();

			transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT,"yes");

			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(filename));
			transformer.transform(source, result);
		} catch (Exception ex) {
			flag = false;
			ex.printStackTrace();
		}
		return flag;
	}

	public static Document load(String filename) {
		Document document = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(new FileInputStream(filename),"GB2312");
			document.normalize();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return document;
	}


	public static void xmlUpdateKey(String filename,String node,String value) {
		Document document = load(filename);
		Node root = document.getDocumentElement();
		boolean nodeExist = false;
		if (root.hasChildNodes()) {
			//System.out.println(root.getAttributes().item(0).getNodeValue());
			NodeList ftpnodes = root.getChildNodes();

			for (int i = 0; i < ftpnodes.getLength(); i++) {
				NodeList ftplist = ftpnodes.item(i).getChildNodes();

				for (int k = 0; k < ftplist.getLength(); k++) {
					Node subnode = ftplist.item(k);

					if (subnode.getNodeType() == Node.ELEMENT_NODE
							&& subnode.getNodeName() == node) {
						subnode.getFirstChild().setNodeValue(value);
						nodeExist = true;
					}
				}
			}
		}
		doc2XmlFile(document,filename );

		if (nodeExist==false) {
			addNode(filename, node, value);
		}

	}
	public static void addNode(String xmlfile,String nodename,String nodevalue){
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(xmlfile);
			Element brandElement = doc.createElement(nodename);

			brandElement.setTextContent(nodevalue);
			Element phoneElement=(Element)doc.getElementsByTagName("testcase").item(0);
			phoneElement.appendChild(brandElement);
			TransformerFactory transformerFactory=TransformerFactory.newInstance();
			Transformer transformer=transformerFactory.newTransformer();
			DOMSource domSource=new DOMSource(doc);
			StreamResult result=new StreamResult(new FileOutputStream(xmlfile));
			transformer.transform(domSource, result);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	public static void testCaseUpdateXml(String filename,UiBean testCase,String item,String value) {
		Document document = load(filename);
		Node root = document.getDocumentElement();
		//System.out.println("start Update xml: "+filename+" with "+item+"="+value);
		if (root.hasChildNodes()) {
			NodeList ftpnodes = root.getChildNodes();
			for (int i = 0; i < ftpnodes.getLength(); i++) {
				NodeList ftplist = ftpnodes.item(i).getChildNodes();
				for (int k = 0; k < ftplist.getLength(); k++) {
					Node subnode = ftplist.item(k);

					if (subnode.getNodeType() == Node.ELEMENT_NODE
							&& subnode.getNodeName() == item) {
						String itemid= subnode.getAttributes().item(0).getNodeValue();
						if (itemid.equals(testCase.getRandomid())) {
							subnode.getFirstChild().setNodeValue(value);
						}
					}
				}
			}
		}
		doc2XmlFile(document,filename );
	}

	public static void testUpdateJar(String filename,String value){
		Document document = load(filename);
		Element root = document.getDocumentElement();
		//System.out.println("start Update xml jar value: "+filename+"="+value);
		root.setAttribute("jar", value);
		//root.getAttributes().getNamedItem("jar").setNodeValue(value);
		doc2XmlFile(document,filename );

	}
	public static void testUpdateAttribute(String filename,String key,String value){
		Document document = load(filename);
		Element root = document.getDocumentElement();
		//System.out.println("start Update xml jar value: "+key+"="+value);
		root.setAttribute(key, value);
		//root.getAttributes().getNamedItem("jar").setNodeValue(value);
		doc2XmlFile(document,filename );
	}

	public static void testCaseUpdateXml(String filename,String randomid,String item,String value) {
		value=value.replaceAll("<", "").replaceAll(">", "").replaceAll("/>", "").replaceAll("/<", "");
		Document document = load(filename);
		Node root = document.getDocumentElement();
		//System.out.println("start Update xml: "+filename+" with "+item+"="+value);
		if (root.hasChildNodes()) {
			NodeList ftpnodes = root.getChildNodes();
			for (int i = 0; i < ftpnodes.getLength(); i++) {
				NodeList ftplist = ftpnodes.item(i).getChildNodes();
				for (int k = 0; k < ftplist.getLength(); k++) {
					Node subnode = ftplist.item(k);

					// if (subnode.getNodeType()==Node.ELEMENT_NODE&&subnode.
							// getNodeName()=="ftp-chn")
					// {
					// ftpnodes.item(i).removeChild(subnode);
					// }

					if (subnode.getNodeType() == Node.ELEMENT_NODE
							&& subnode.getNodeName() == item) {
						String itemid= subnode.getAttributes().item(0).getNodeValue();
						if (itemid.equals(randomid)) {
							subnode.getFirstChild().setNodeValue(value);
						}
					}
				}
			}
		}
		doc2XmlFile(document,filename );
	}
	public static void main(String args[]) throws Exception {
		//s = new String(s.getBytes("ISO8859_1"), "GBK");
		String fileName= "f:\\ATT\\config\\config.xml";
		XmlUpdate.testUpdateAttribute(fileName, "tests","1212");

		//XmlUpdate.testUpdateJar(fileName, "E:\\Android\\eclipse\\svndemo\\ATT_UIAutomator\\bin\\ATT_UIAutomator.jar");
	}
}