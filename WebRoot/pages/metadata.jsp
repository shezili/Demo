<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" type="text/css" href="../easyui/easyui.css" />
<link rel="stylesheet" type="text/css" href="../easyui/icon.css" />
<link rel="stylesheet" type="text/css" href="../easyui/demo.css" />
<script type="text/javascript" src="../easyui/jquery.min.js "></script>
<script type="text/javascript" src="../easyui/jquery.easyui.min.js "></script>
<script type="text/javascript">
function queryMetaData(){
	var mapName = document.getElementById('mapName').value;
	$("#tableCal").datagrid({
		url : "findMetaDataByMapName?mapName="+mapName,
		onLoadSuccess:function(data){
			
		}
	});
}

</script>
</head>
<body onload="queryMetaData()">


<!-- 	表格的url被取出写在了upload.js里便于更加自由地调用 ，可以自由编写其返回函数-->
	<div style="position:absolute;z-index:1; left: 5px; top: 5px;">
		<table class="easyui-datagrid" id="tableCal"
			style="width:900px;height:520px" rownumbers="true" singleSelect=true
			pagination="true" nowrap="false" striped="true" data-options="toolbar:'#toolbar'"
			>
			<thead>
				<tr>
					<th data-options="field:'mapName',width:200">mapName</th>
					<th data-options="field:'creationDate',width:200">creationDate</th>
					<th data-options="field:'inputDate',width:200">inputDate</th>
					<th data-options="field:'latestmodiDate',width:200">latestmodiDate</th>
				</tr>
			</thead>
		</table>

	</div>
	<div id="toolbar" style="padding:5px;height:auto">
<!-- 			<input class="easyui-textbox" style="width:10%;height:32px" id="mapName" onChange="queryMetaData()"> -->
			<input type="text" id="mapName" onchange="queryMetaData()">
	        <a href="#" onclick="queryMetaData()" class="easyui-linkbutton" iconCls="icon-search">Search</a>
	</div>
</body>
</html>