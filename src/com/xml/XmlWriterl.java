package com.xml;
import org.w3c.dom.*;

import com.panel.scriptPanel;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Vector;


public class XmlWriterl {
	private Document document;
	private String filename;

	public XmlWriterl(String name) throws ParserConfigurationException{
		//System.out.println("Towrite:"+name);
		filename=name;
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		document=builder.newDocument();

	}
	public void toWrite(Vector<UiBean> mkVector)
	{
		Element root = document.createElement("testsuite");
		root.setAttribute("name", "uiautomator");
		root.setAttribute("version", "NA");
		if (scriptPanel.tf_file_path==null) {
			root.setAttribute("jar", "");
		}else {
			root.setAttribute("jar", scriptPanel.tf_file_path.getText());
		}
		
		
		document.appendChild(root);
		
		for(int i = 0;i<mkVector.size();i++)
		{
			UiBean monkeyBean = (UiBean) mkVector.get(i);
			String timeseconds = monkeyBean.getRandomid();
			
			Element mkEmt = document.createElement("testcase");
			root.appendChild(mkEmt);

			Element isSelectElement = document.createElement("isSelect");
			isSelectElement.setAttribute("id", timeseconds);
			mkEmt.appendChild(isSelectElement);
			Text isSelect = document.createTextNode(String.valueOf(monkeyBean.getSelected()));
			isSelectElement.appendChild(isSelect);
			
			Element scriptElement = document.createElement("script");
			scriptElement.setAttribute("id", timeseconds);
			mkEmt.appendChild(scriptElement);
			Text script = document.createTextNode(String.valueOf(monkeyBean.getScript()));
			scriptElement.appendChild(script);
			
			
			Element summaryElement = document.createElement("summary");
			summaryElement.setAttribute("id", timeseconds);
			mkEmt.appendChild(summaryElement);
			Text summary = document.createTextNode(String.valueOf(monkeyBean.getSummary()));
			summaryElement.appendChild(summary);
			
			Element deviceElement = document.createElement("device");
			deviceElement.setAttribute("id", timeseconds);
			mkEmt.appendChild(deviceElement);
			Text device = document.createTextNode(String.valueOf(monkeyBean.getDevice()));
			deviceElement.appendChild(device);
			
			
			/*Element e1Element = document.createElement("e1");
			e1Element.setAttribute("id", timeseconds);
			mkEmt.appendChild(e1Element);
			Text e1 = document.createTextNode(String.valueOf(monkeyBean.getE1()));
			e1Element.appendChild(e1);
			
			
			Element e2Element = document.createElement("e2");
			e2Element.setAttribute("id", timeseconds);
			mkEmt.appendChild(e2Element);
			Text e2 = document.createTextNode(String.valueOf(monkeyBean.getE2()));
			e2Element.appendChild(e2);
			
			Element e3Element = document.createElement("e3");
			e3Element.setAttribute("id", timeseconds);
			mkEmt.appendChild(e3Element);
			Text e3 = document.createTextNode(String.valueOf(monkeyBean.getE3()));
			e3Element.appendChild(e3);*/
			
			Element randomidElement = document.createElement("randomid");
			randomidElement.setAttribute("id", timeseconds);
			mkEmt.appendChild(randomidElement);
			Text randomidText = document.createTextNode(timeseconds);
			randomidElement.appendChild(randomidText);
			
			Element esElement = document.createElement("es");
			esElement.setAttribute("id", timeseconds);
			mkEmt.appendChild(esElement);
			Text esText = document.createTextNode(String.valueOf(monkeyBean.getEs()));
			esElement.appendChild(esText);
			
			Element suitElement = document.createElement("suitName");
			suitElement.setAttribute("id", timeseconds);
			mkEmt.appendChild(suitElement);
			Text suitText = document.createTextNode(String.valueOf(monkeyBean.getSuitName()));
			suitElement.appendChild(suitText);
			
		}
	}

	public void toSave(){
		try{
			TransformerFactory tf=TransformerFactory.newInstance();
			Transformer transformer=tf.newTransformer();
			DOMSource source=new DOMSource(document);
			//transformer.setOutputProperty(OutputKeys.ENCODING,"utf-8");
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

		Vector<UiBean> A = new Vector<UiBean>();

		UiBean tcBean;
		
		tcBean = new UiBean();
		tcBean.setSelected("false");
		tcBean.setDevice("HIKE");
		tcBean.setScript("E:\\ATT\\scripts\\Telephony.mhl");
		tcBean.setSummary("是是是");
		//tcBean.setE1("e1");
		//tcBean.setE2("e2");
		//tcBean.setE3("e3");
		tcBean.setRandomid("123456789");
		
		A.add(tcBean);

		try{
			XmlWriterl myxml=new XmlWriterl("f:\\ATT\\config\\config1.xml");
			//System.out.println("start swrite xml file:");
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