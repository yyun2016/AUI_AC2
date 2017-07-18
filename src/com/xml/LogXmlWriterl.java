package com.xml;
import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Vector;


public class LogXmlWriterl {
	private Document document;
	private String filename;


	public LogXmlWriterl(String name) throws ParserConfigurationException{
		System.out.println("Towrite:"+name);
		filename=name;
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		document=builder.newDocument();

	}
	public void toWrite(Vector<LogBean> mkVector)
	{
		Element root = document.createElement("testsuite");
		root.setAttribute("name", "uiautomator");
		root.setAttribute("version", "NA");
		document.appendChild(root);
		
		for(int i = 0;i<mkVector.size();i++)
		{
			LogBean monkeyBean = (LogBean) mkVector.get(i);
			String timeseconds = monkeyBean.getRandomid();
			
			Element mkEmt = document.createElement("testcase");
			root.appendChild(mkEmt);

			Element isSelectElement = document.createElement("LogArgument");
			isSelectElement.setAttribute("id", timeseconds);
			mkEmt.appendChild(isSelectElement);
			Text isSelect = document.createTextNode(String.valueOf(monkeyBean.getLogAugument()));
			isSelectElement.appendChild(isSelect);
			
			Element randomidElement = document.createElement("randomid");
			randomidElement.setAttribute("id", timeseconds);
			mkEmt.appendChild(randomidElement);
			Text randomidText = document.createTextNode(timeseconds);
			randomidElement.appendChild(randomidText);
			
			Element suitNameElement = document.createElement("suitName");
			suitNameElement.setAttribute("id", timeseconds);
			mkEmt.appendChild(suitNameElement);
			Text suitNameElementText = document.createTextNode(String.valueOf(monkeyBean.getSuitName()));
			suitNameElement.appendChild(suitNameElementText);
			
			Element caseNameElement = document.createElement("caseName");
			caseNameElement.setAttribute("id", timeseconds);
			mkEmt.appendChild(caseNameElement);
			Text caseNameElementText = document.createTextNode(String.valueOf(monkeyBean.getCaseName()));
			caseNameElement.appendChild(caseNameElementText);
			
			Element summaryElement = document.createElement("summary");
			summaryElement.setAttribute("id", timeseconds);
			mkEmt.appendChild(summaryElement);
			Text summaryElementText = document.createTextNode(String.valueOf(monkeyBean.getSummary()));
			summaryElement.appendChild(summaryElementText);
			
			
		}
	}

	public void toSave(){
		try{
			TransformerFactory tf=TransformerFactory.newInstance();
			Transformer transformer=tf.newTransformer();
			DOMSource source=new DOMSource(document);
			//transformer.setOutputProperty(OutputKeys.ENCODING,"GB2312");
			transformer.setOutputProperty(OutputKeys.INDENT,"yes");
			PrintWriter pw=new PrintWriter(new FileOutputStream(filename));
			StreamResult result=new StreamResult(pw);
			transformer.transform(source,result);
		}
		catch(TransformerException mye){
			mye.printStackTrace();
		}
		catch(IOException exp){
			exp.printStackTrace();
		}
	}
	public static void main(String args[]) throws IOException, InterruptedException{

		Vector<LogBean> A = new Vector<LogBean>();

		LogBean tcBean;
		
		tcBean = new LogBean();
		tcBean.setLogAugument("你好啊");
		tcBean.setRandomid("123456789");
		
		A.add(tcBean);

		try{
			LogXmlWriterl myxml=new LogXmlWriterl("E:\\ATT\\log\\log.xml");
			System.out.println("start swrite xml file:");
			myxml.toWrite(A);
			myxml.toSave();
			System.out.print("Your writing is successful.");
		}
		catch(ParserConfigurationException exp){
			exp.printStackTrace();
			System.out.print("Your writing is failed.");
		}
	}
} 