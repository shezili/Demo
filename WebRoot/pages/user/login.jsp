<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" type="text/css" href="../../easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="../../easyui/icon.css">
<link rel="stylesheet" type="text/css" href="../../easyui/demo.css">
<script type="text/javascript" src="../../easyui/jquery.min.js "></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js "></script>
  
</head>
<body>
 
<p>用户名：${session.user}</p>
<s:form action="login">
	<s:textfield name="name" key="user"/>
	<s:textfield name="password" key="pass"/>
	<s:submit key="login"/>
</s:form>
<a href="logout">退出登录</a>
<a href="jump">进入一期系统</a>

</body>
</html>