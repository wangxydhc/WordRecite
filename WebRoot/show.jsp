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
  
<div id="graph">Loading graph...</div>
<script src="sources/jscharts.js"></script>
<script type="text/javascript">
	
	var myData = new Array(['2005', 2], ['2006', 1], ['2007', 3], ['2008', 6]);
	var myChart = new JSChart('graph', 'line');
	myChart.setDataArray(myData);
	myChart.setBarColor('#42aBdB');
	myChart.setBarOpacity(0.8);
	myChart.setBarBorderColor('#D9EDF7');
	myChart.setBarValues(false);
	myChart.setTitleColor('#8C8383');
	myChart.setAxisColor('#777E81');
	myChart.setAxisValuesColor('#777E81');
	myChart.draw();
	
</script>





</html>
