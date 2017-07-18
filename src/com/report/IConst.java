package com.report;
public interface IConst {
	public static final String TESTRESULT_HTML_HEAD="<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01//EN' 'http://www.w3.org/TR/html4/strict.dtd'>" +
	"<html>" +
	"<head>" +
	"<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />" +
	"<style type=\"text/css\">" +
	"body{font:normal 12px Verdana}" +
	"a#tip {position:relative;left:75px; font-weight:bold;}" +
	"a#tip:link,a#tip:hover {text-decoration:none;color:#000;display:block}" +
	"a#tip span {display:none;text-decoration:none;}" +
	"a#tip:visited {color:#000;text-decoration:underline;}" +
	"a#tip:hover #tip_info {display:block;border:1px solid #F96;background:#FFEFEF;padding:10px 20px;position:absolute;top:0px;left:90px;color:#009933}" +
	"a:link,a:visited,a:hover{font-size:12px;color:#000000}"+ 
	".casename{overflow:hidden;text-overflow:ellipsis;}"+
	"</style>" +
	"<title>CKT Auto Test Result</title>" +
	"<link rel='stylesheet' type='text/css' href='./dependency/css/fonts-min.css' />" +
	"<link rel='stylesheet' type='text/css' href='./dependency/css/container.css' />" +
	"<link rel='stylesheet' type='text/css' href='./dependency/css/alcatelPanel.css' />" +
	"<script type='text/javascript' src='./dependency/js/yahoo-dom-event.js'></script>" +
	"<script type='text/javascript' src='./dependency/js/dragdrop.js'></script>" +
	"<script type='text/javascript' src='./dependency/js/container.js'></script>" +
	"<script type='text/javascript' src='./dependency/js/alcatelPanel.js'></script>" +
	"<script type='text/javascript'>" +
	"function showFirstResult(e){" +
	"document.getElementById('firstTitle').innerHTML=e.titleVal;" +
	"document.getElementById('firstContent').innerHTML=e.contentVal;" +
	"oPanel1.render();oPanel1.show();" +
	"}" +
	"function showSecondResult(e){" +
	"document.getElementById('secondTitle').innerHTML=e.titleVal;" +
	"document.getElementById('secondContent').innerHTML=e.contentVal;" +
	"oPanel2.render();" +
	"oPanel2.show();" +
	"}" +
	"function showThirdResult(e){" +
	"document.getElementById('thirdTitle').innerHTML=e.titleVal;" +
	"document.getElementById('thirdContent').innerHTML='<IMG id=thirdImage SRC='+e.contentVal+' />';" +
	"document.getElementById('thirdImage').src=e.contentVal;"+
	"oPanel3.render();" +
	"oPanel3.show();" +
	"}" +
	"function showFourthResult(e){" +
	"document.getElementById('fourthTitle').innerHTML=e.titleVal;" +
	"document.getElementById('fourthContent').innerHTML=e.contentVal;" +
	"oPanel4.render();" +
	"oPanel4.show();" +
	"}" +
	"function showFifthResult(e){" +
	"document.getElementById('fifthTitle').innerHTML=e.titleVal;" +
	"document.getElementById('fifthContent').innerHTML='<IMG id=fifthImage SRC='+e.contentVal+' />'+' '+'<IMG id=fifthImage SRC='+e.contentVal2+' />';" +
	"document.getElementById('fifthImage').src=e.contentVal;"+
	"oPanel5.render();" +
	"oPanel5.show();" +
	"}" +
	"function change(eleDivName,eleTdName){"+
	"var eleDivObj;"+
	"var eleTdObj;"+
	"if(document.getElementById){"+
	"eleDivObj=document.getElementById(eleDivName);"+
	"eleTdObj=document.getElementById(eleTdName);"+
	"if(eleDivObj!=null && eleDivObj!= undefined){"+
	"if(eleDivObj.currentStyle.display == \"block\"){"+
	"eleDivObj.style.display = \"none\";}"+
	"else{"+
	"eleDivObj.style.display = \"block\";}}}}"+
	"</script>" +
	"</head>" +
	"<body class=' yui-skin-sam'>" +
	"<div id='panel1'>" +
	"<div class='hd' id='firstTitle'></div>" +
	"<div class='bd' id='firstContent'></div>" +
	"</div>" +
	"<div id='panel2'>" +
	"<div class='hd' id='secondTitle'></div>" +
	"<div class='bd' id='secondContent'></div>" +
	"</div>"+
	"<div id='panel3'>" +
	"<div class='hd' id='thirdTitle'></div>" +
	"<div class='bd' id='thirdContent'></div>" +
	"</div>"+
	"<div id='panel4'>" +
	"<div class='hd' id='fourthTitle'></div>" +
	"<div class='bd' id='fourthContent'></div>" +
	"</div>"+
	"<div id='panel5'>" +
	"<div class='hd' id='fifthTitle'></div>" +
	"<div class='bd' id='fifthContent'></div>" +
	"</div>";
	
	public static final String TESTRESULT_MONKEYHTML_HEAD="<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01//EN' 'http://www.w3.org/TR/html4/strict.dtd'>" +
	"<html>" +
	"<head>" +
	"<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />" +
	"<style type=\"text/css\">\n" +
	"table {width:500px;table-layout:fixed;}\n"+
	".col1 {width:100px;}\n"+
	".col2 {width:200px;}\n"+
	".col3 {width:200px;}\n"+
	"td {white-space:nowrap;overflow:hidden;}\n"+
	"body{font:normal 12px Verdana}\n" +
	"a#tip {position:relative;left:75px; font-weight:bold;}\n" +
	"a#tip:link,a#tip:hover {text-decoration:none;color:#000;display:block}\n" +
	"a#tip span {display:none;text-decoration:none;}\n" +
	"a#tip:visited {color:#000;text-decoration:underline;}\n" +
	"a#tip:hover #tip_info {display:block;border:1px solid #F96;background:#FFEFEF;padding:10px 20px;position:absolute;top:0px;left:90px;color:#009933}\n" +
	"a:link,a:visited,a:hover{font-size:12px;color:#000000}\n"+ 
	".casename{overflow:hidden;text-overflow:ellipsis;}\n"+
	"</style>\n" +
	"<title>CKT Auto Test Result</title>\n" +
	"<link rel='stylesheet' type='text/css' href='./dependency/css/fonts-min.css' />\n" +
	"<link rel='stylesheet' type='text/css' href='./dependency/css/container.css' />\n" +
	"<link rel='stylesheet' type='text/css' href='./dependency/css/alcatelPanel.css' />\n" +
	"<script type='text/javascript' src='./dependency/js/yahoo-dom-event.js'></script>\n" +
	"<script type='text/javascript' src='./dependency/js/dragdrop.js'></script>\n" +
	"<script type='text/javascript' src='./dependency/js/container.js'></script>\n" +
	"<script type='text/javascript' src='./dependency/js/alcatelPanel.js'></script>\n" +
	"<script type='text/javascript'>\n" +
	"function showFirstResult(e){\n" +
	"document.getElementById('firstTitle').innerHTML=e.titleVal;\n" +
	"document.getElementById('firstContent').innerHTML=e.contentVal;\n" +
	"oPanel1.render();oPanel1.show();\n" +
	"}\n" +
	"function showSecondResult(e){\n" +
	"document.getElementById('secondTitle').innerHTML=e.titleVal;\n" +
	"document.getElementById('secondContent').innerHTML=e.contentVal;\n" +
	"oPanel2.render();\n" +
	"oPanel2.show();\n" +
	"}\n" +
	"function showThirdResult(e){\n" +
	"document.getElementById('thirdTitle').innerHTML=e.titleVal;\n" +
	"document.getElementById('thirdContent').innerHTML='<IMG id=thirdImage SRC='+e.contentVal+' />';\n" +
	"document.getElementById('thirdImage').src=e.contentVal;\n"+
	"oPanel3.render();\n" +
	"oPanel3.show();\n" +
	"}\n" +
	"function showFourthResult(e){\n" +
	"document.getElementById('fourthTitle').innerHTML=e.titleVal;\n" +
	"document.getElementById('fourthContent').innerHTML=e.contentVal;\n" +
	"oPanel4.render();\n" +
	"oPanel4.show();\n" +
	"}\n" +
	"function showFifthResult(e){\n" +
	"document.getElementById('fifthTitle').innerHTML=e.titleVal;\n" +
	"document.getElementById('fifthContent').innerHTML='<IMG id=fifthImage SRC='+e.contentVal+' />'+' '+'<IMG id=fifthImage SRC='+e.contentVal2+' />';\n" +
	"document.getElementById('fifthImage').src=e.contentVal;\n"+
	"oPanel5.render();\n" +
	"oPanel5.show();\n" +
	"}\n" +
	"function change(eleDivName,eleTdName){\n"+
	"var eleDivObj;\n"+
	"var eleTdObj;\n"+
	"if(document.getElementById){\n"+
	"eleDivObj=document.getElementById(eleDivName);\n"+
	"eleTdObj=document.getElementById(eleTdName);\n"+
	"if(eleDivObj!=null && eleDivObj!= undefined){\n"+
	"if(eleDivObj.style.display == \"block\"){\n"+
	"eleDivObj.style.display = \"none\";}\n"+
	"else{\n"+
	"eleDivObj.style.display = \"block\";}}}}\n"+
	"</script>\n" +
	"</head>\n" +
	"<body class=' yui-skin-sam'>\n";
}
