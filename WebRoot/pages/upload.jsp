<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<s:head />
<script type="text/javascript" src="../easyui/jquery.min.js "></script>
<script>
function getIslandNo(){

	var x=document.getElementById("nameField");
	var islandName = x.value;

	$.ajax({
		method : 'POST',
		url : "getIslandNumber",
		data : {
			islandName : islandName
		},
		success : getIslandNoCallBack,
		dataType : "json"
	});
}

function getIslandNoCallBack(json){
	var x=document.getElementById("noField");
	if ((json == null) || (json == "undefined") 
			|| (json == "")) {
		x.value = '找不到对应的编号';
	} else{
		x.value = json;
	}
}

function getIslandName(){
	var y=document.getElementById("noField");
	var islandNo = y.value;
	$.ajax({
		method : 'POST',
		url : "getIslandName",
		data : {
			islandNo : islandNo
		},
		success : getIslandNameCallBack,
		dataType : "json"
	});
}

function getIslandNameCallBack(json){
	var x=document.getElementById("nameField");
	if ((json == null) || (json == "undefined") 
			|| (json == "")) {
		x.value = '找不到对应的名称';
	}else{
			x.value = json;
	}
}
</script>
</head>
<body>
<div style="position:absolute;z-index:1; left: 0px; top: -60px">
	<s:form action="uploadPro" method="post" enctype="multipart/form-data"  target="aa">
		<s:textfield name="islandName" label="海岛名称" id="nameField" onchange="getIslandNo()"/>
		<br />
		<s:textfield name="islandNo" label="海岛编号" id="noField" onchange="getIslandName()"/>
		<br />
		<s:file name="upload" label="选择文件" />
		<br />
		<s:textfield name="time" label="输入时间" />
		<br />
		<s:submit value="上传" />
	</s:form>
	<iframe name="aa" src="" style="display:none"></iframe>
</div>

</body>
</html>
