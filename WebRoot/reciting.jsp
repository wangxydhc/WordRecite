<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>


<body>

	<script>
		var xmlhttp;
		function loadXMLDoc(url, cfunc) {
			if (window.XMLHttpRequest) {// IE7+, Firefox, Chrome, Opera, Safari 代码
				xmlhttp = new XMLHttpRequest();
			} else {// IE6, IE5 代码
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlhttp.onreadystatechange = cfunc;
			xmlhttp.open("GET", url, true);
			xmlhttp.send();
		}
		function myFunction() {
			
		}
		function showIFrame(s) {
			document.getElementById('youdao').src = 'http://www.youdao.com/w/eng/'
					+ s;
		}
		function commit(int, handle,button) {			
			for(i=0;i<handle.length;i++)
				if(handle[i].name=="radioName"&&handle[i].checked==true)
					v=handle[i].value;
			url = "ConfirmWord?" + "wordId=" + int + "&handle=" + v;
			loadXMLDoc(url, myFunction);
			button.type="hidden"
		}
	</script>


	<iframe id="youdao" align=right width=800 frameborder=0 height=1200
		marginheight=300 scrolling=auto
		src="http://dict.youdao.com/?keyfrom=dict2.top"></iframe>
	<br />
	<h1>开始背单词吧</h1>
	<br />
	<br />
	<br />
	<br />

	<s:iterator value="res" id="list">
		<form name="formname" id='#list.getId()'>
			<input type="hidden" name="wordId"
				value="<s:property value='#list.getId()'/>" /> 单词：
			<s:property value="#list.getWord()" />
			&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="查字典"
				onClick="showIFrame('<s:property value="#list.getWord()" />')" /> 
				<br />
				<br/>				
			释义：
			<s:property value="#list.getParaphrase()" />
			<br />
			<br/>
			<s:radio name="radioName" id='#list.getId()' list="#{'1':'完全不会','2':'有点儿印象','3':'基本掌握','4':'太简单了'}" value="" />
			<input type="button" value="提交" id="inputHH"
				onClick="commit('<s:property value="#list.getId()" />',this.parentNode.childNodes,this)" />
			<br />
			
		</form>
		<br/>
	</s:iterator>



	<!--<s:property value="res.{word}"/>   -->
	<s:debug></s:debug>
</body>
</html>
