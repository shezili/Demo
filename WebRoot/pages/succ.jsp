
<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>上传成功</title>
</head>
<body>
	上传成功!<br/>
	文件标题:<s:property value=" + uploadFileName"/><br/>
	<a href="upload.jsp">继续上传文件</a>
<form  action = "testMetaData">
    <input type="text" name="mapName">
    <button type="submit">Submit</button>
</form>
</body>
</html>
