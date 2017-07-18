package com.junit;
import org.w3c.dom.*;

import com.helper.JarHelper;
import com.helper.ServiceHelper;
import com.log.Log;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.*;
import java.util.ArrayList;
import java.util.jar.Attributes.Name;


public class JunitWriter {
	private Document document;
	private String xml;
	public JunitWriter() throws ParserConfigurationException{
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		document=builder.newDocument();

	}
	public void toWrite(Testsuite testsuite)
	{
		xml = JarHelper.getProjectPath()+"junit\\"+testsuite.getName()+".xml";
		Element root = document.createElement("testsuite");
		String errors = testsuite.getErrors();
		String failures=testsuite.getFailures();
		String hostname=testsuite.getHostname();
		String id=testsuite.getId();
		String name=testsuite.getName().split("_")[0];
		//String name=testsuite.getName();
		String t_package=testsuite.getT_package();
		String tests=testsuite.getTests();
		String time=testsuite.getTime();
		String timestamp=testsuite.getTimestamp();
		ArrayList<Testcase> testcases=testsuite.getTestcases();

		root.setAttribute("errors", errors);
		root.setAttribute("failures", failures);
		root.setAttribute("hostname", hostname);
		root.setAttribute("id", id);
		root.setAttribute("name", name);
		root.setAttribute("package", t_package);
		root.setAttribute("tests", tests);
		root.setAttribute("time", time);
		root.setAttribute("errors", errors);
		root.setAttribute("timestamp", timestamp);
		document.appendChild(root);

		for (Testcase testcase : testcases) {
			Element testcaseElement = document.createElement("testcase");
			root.appendChild(testcaseElement);

			testcaseElement.setAttribute("classname", testcase.getClassname());
			testcaseElement.setAttribute("name", testcase.getName());
			testcaseElement.setAttribute("time", testcase.getTime());
			
			Failure failure = testcase.getFailureInfo();
			String failstr = failure.getFailInfo();
			if (failstr!=null&&failstr.contains("FAILURES!!!")||failstr!=null&&ServiceHelper.isFail(failstr)) {
				String message = failure.getMessage();
				String type = failure.getType();
				String finfo = failure.getFailInfo();
				Element failElement = document.createElement("failure");
				testcaseElement.appendChild(failElement);
				failElement.setAttribute("message", message);
				failElement.setAttribute("type", type);
				Text isFailure = document.createTextNode(finfo);
				failElement.appendChild(isFailure);
			}
		}
	}

	public void toSave(){
		try{
			TransformerFactory tf=TransformerFactory.newInstance();
			Transformer transformer=tf.newTransformer();
			DOMSource source=new DOMSource(document);
			if (JarHelper.TAG==1) {
				transformer.setOutputProperty(OutputKeys.ENCODING,"gb2312");
			}else {
				transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
			}
			transformer.setOutputProperty(OutputKeys.INDENT,"yes");
			PrintWriter pw=new PrintWriter(new FileOutputStream(xml));
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
		Failure failure = new Failure();
		failure.setMessage("UiSelector[CLASS=android.widget.EditText, INDEX=1]");
		failure.setType("com.android.uiautomator.core.UiObjectNotFoundException");
		failure.setFailInfo("1212121212+FAILURES!!!");

		Testcase testcase = new Testcase();
		testcase.setClassname("Stability.ReportTesting.ClickTest");
		testcase.setName("testOpenDial");
		testcase.setTime("100");
		testcase.setFailureInfo(failure);

		Testsuite testsuite = new Testsuite();

		ArrayList<Testcase> testcases = new ArrayList<Testcase>();
		testcases.add(testcase);

		testsuite.setErrors("0");
		testsuite.setFailures("0");
		testsuite.setHostname("N12210");
		testsuite.setId("1");
		testsuite.setName("test_file_with_one_exception");
		testsuite.setT_package("");
		testsuite.setTestcases(testcases);
		testsuite.setTests("8");
		testsuite.setTime("5");
		testsuite.setTimestamp("2014-09-04T06:14:30");

		try{
			JunitWriter myxml=new JunitWriter();
			System.out.println("start swrite xml file:");
			myxml.toWrite(testsuite);
			myxml.toSave();
			System.out.print("Your writing is successful.");
		}
		catch(ParserConfigurationException exp){
			exp.printStackTrace();
			System.out.print("Your writing is failed.");
		}
	}
} 