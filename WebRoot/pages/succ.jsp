
<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>�ϴ��ɹ�</title>
</head>
<body>
	�ϴ��ɹ�!<br/>
	�ļ�����:<s:property value=" + uploadFileName"/><br/>
	<a href="upload.jsp">�����ϴ��ļ�</a>
<form  action = "testMetaData">
    <input type="text" name="mapName">
    <button type="submit">Submit</button>
</form>
</body>
</html>
