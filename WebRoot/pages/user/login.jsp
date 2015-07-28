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
<script type="text/javascript">

	function submitForm() {
		$('#ff').form('submit', {
			onSubmit : function() {
				return $(this).form('enableValidation').form('validate');
			},
			success:function(data){
			alert(data);
		       switch(data){
					case 'login success':
					alert("successaa");
	  					window.location.href='../index.jsp';
	 					break;
					case 'login failed':
  
  						break;
					default:
 						;
		    	}
			}
		});
		
	}
	
	function clearForm() {
		$('#ff').form('clear');
	}
</script>
</head>
<body>
 
<p>用户名：${session.user}</p>
<%-- <s:form action="login"> --%>
<%-- 	<s:textfield name="name" key="user"/> --%>
<%-- 	<s:textfield name="password" key="pass"/> --%>
<%-- 	<s:submit key="login"/> --%>
<%-- </s:form> --%>
<%-- <a href="logout">退出登录</a> --%>
<%-- <a href="jump">进入一期系统</a> --%>

    <div style="margin:20px 0;"></div>
    <div class="easyui-panel" title="New Topic" style="width:400px">
        <div style="padding:10px 60px 20px 60px">
        <form id="ff" class="easyui-form" method="post" data-options="novalidate:true" action="login">
            <table cellpadding="5">
                <tr>
                    <td>Name:</td>
                    <td><input class="easyui-textbox" type="text" name="name" data-options="required:true"></input></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input class="easyui-textbox" type="text" name="password" data-options="required:true"></input></td>
                </tr>
            </table>
        </form>
        <div style="text-align:center;padding:5px">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">Submit</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">Clear</a>
        </div>
        </div>
    </div>


</body>
</html>