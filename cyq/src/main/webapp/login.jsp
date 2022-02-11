<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	/* 获取相对路径 */
String path = request.getContextPath();
	/* 这个语句是用来拼装当前网页的相对路径的。 */
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<%--	 <base> 标签为页面上的所有链接规定默认地址或默认目标。--%>
<%--	  个人认为这行代码是为了隐藏路径，只显示大概的路径--%>

<%--    <base href="<%=basePath%>">--%>
    
    <title>My JSP 'login.jsp' starting page</title>
<%--	http-equiv顾名思义，相当于http的文件头作用，它可以向浏览器传回一些有用的信息，以帮助正确和精确地显示网页内容，--%>
<%--	与之对应的属性值为content，content中的内容其实就是各个参数的变量值。--%>
<%--	具体含义参考网址：https://www.cnblogs.com/yumo1627129/p/7198968.html--%>

<%--	Pragma(cache模式)--%>
<%--	说明：禁止浏览器从本地计算机的缓存中访问页面内容。--%>
<%--	用法：<metahttp-equiv="Pragma"content="no-cache">--%>
<%--	注意：这样设定，访问者将无法脱机浏览。--%>

<%--	<meta http-equiv="pragma" content="no-cache">--%>

<%--	清除缓存（再访问这个网站要重新下载！）--%>
	<meta http-equiv="cache-control" content="no-cache">

<%--	Expires(期限)--%>
<%--	说明：可以用于设定网页的到期时间。一旦网页过期，必须到服务器上重新传输。--%>
<%--	用法：<metahttp-equiv="expires"content="Fri,12Jan200118:18:18GMT">--%>
<%--	注意：必须使用GMT的时间格式--%>
	<meta http-equiv="expires" content="0">

<%--	关键字,给搜索引擎用的--%>
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">

<%--	页面描述--%>
	<meta http-equiv="description" content="This is my page">

<%--	导入jq包--%>
	<script src="/js/jquery-3.2.1.min.js"></script>

<%--	导入bootstradp框架--%>
	<script src="/js/bootstrap.js"></script>

<%--	链接一个外部样式表：--%>
	<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">

  </head>
  
  <body>
<%--  导入了bootstrap样式--%>
    <div class="container">
    	<div class="well" style="margin-top:30px;height:200px;">

<%--			from表单传到后台地址：./servlet/Login--%>
    		<form class="form-horizontal" method="post" action="/cyq/logServelt">
    			<div class="control-group">
<%--					输入框左边的字--%>
    				<label class="control-label">用户名</label>
    				<div class="controls">
    					<input type="text" name="userName" style="height:30px;" required placeholder="Input UserName...">
    				</div>
    			</div>
    			<div class="control-group">
    				<label class="control-label"></label>
    				<div class="controls">
    					<button type="submit" class="btn">登录</button>
    				</div>
    			</div>
    		</form>
    	</div>
    </div>
  </body>
</html>
