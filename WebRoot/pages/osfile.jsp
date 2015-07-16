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
<!-- 加上charset="gb2312"或者charset="big5"解决js中文乱码  -->
<script type="text/javascript" src=../js/upload.js charset="gb2312"></script>
</head>
<body onload="getDatagridData('')">
	<div id="uploadDlg" class="easyui-dialog" title="上传" closed="true"
		style="width:360px;height:220px;padding:10px">
		<iframe src="upload.jsp" scrolling="no" height="100%" width="100%"></iframe>
	</div>

	<div id="pictureDlg" class="easyui-dialog" title="图像预览" closed="true"
		style="width:190px;height:180px;padding:10px">
		<img id="picture" src="" width='128' height='128' />
	</div>

<!-- 	表格的url被取出写在了upload.js里便于更加自由地调用 ，可以自由编写其返回函数-->
	<div style="position:absolute;z-index:1; left: 5px; top: 5px;">
		<table class="easyui-datagrid" id="tableCal" 
			style="width:900px;height:520px" rownumbers="true" singleSelect=true
			pagination="true" nowrap="false" striped="true" data-options="toolbar:toolbar">
			<thead>
				<tr>
					<th data-options="field:'simpleName',width:400">子文件</th>
					<th data-options="field:'inp1',width:200,formatter:formatInputs_1">预览</th>
					<th data-options="field:'inp2',width:200,formatter:formatInputs_2">进入</th>
					<th data-options="field:'inp3',width:200,formatter:formatInputs_3">删除</th>
				</tr>
			</thead>
		</table>
	
	</div>

</body>
</html>