package com.xml;
import org.w3c.dom.*;

import com.helper.JarHelper;
import com.panel.scriptPanel;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Vector;


public class XmlInsert {
	private Document document;
	private String filename;
	Vector<UiBean> addVector;


	public XmlInsert(String writefile,Vector<UiBean> addVector) throws ParserConfigurationException{
		//System.out.println("Towrite:"+writefile);
		this.addVector = addVector;
		this.filename=writefile;
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		document=builder.newDocument();

	}
	public void toWrite()
	{
		
		Vector<UiBean> mkVector = null;
		XmlReader my = new XmlReader();
		mkVector = my.toRead(filename);
		
		mkVector.addAll(addVector);
		
		Element root = document.createElement("testsuite");
		root.setAttribute("name", "uiautomator");
		root.setAttribute("version", "NA");
		root.setAttribute("jar", scriptPanel.tf_file_path.getText());
		
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
			//transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
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
	public static void main(String args[]) throws IOException, InterruptedException, ParserConfigurationException{
		Vector<UiBean> A = new Vector<UiBean>();

		UiBean tcBean;
		
		tcBean = new UiBean();
		tcBean.setSelected("false");
		tcBean.setDevice("HIKE555");
		tcBean.setScript("E:\\ATT\\scripts\\1Telephony.mhl");
		tcBean.setSummary("拨号");
		//tcBean.setE1("e1");
		//tcBean.setE2("e2");
		//tcBean.setE3("e3");
		tcBean.setRandomid(System.currentTimeMillis()+"");
		
		A.add(tcBean);

		
		//mk2WriteXmls.WritePmList();
		String xml = JarHelper.getProjectPath()+"config\\config1.xml";
		XmlInsert Insert = new XmlInsert(xml, A);
		Insert.toWrite();
		Insert.toSave();
		//mk2WriteXml2.WritepkgFile();
	}
} 