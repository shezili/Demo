<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" type="text/css" href="../easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="../easyui/icon.css">
<link rel="stylesheet" type="text/css" href="../easyui/demo.css">
<script type="text/javascript" src="../easyui/jquery.min.js "></script>
<script type="text/javascript" src="../easyui/jquery.easyui.min.js "></script>
<script type="text/javascript">
	function changeMap_1(name) {
		//alert(name);
		parent.window.setLayerSourceOfChild_1(name);
		//alert("layer1 of the first map changed success");
	}
	function changeMap_2(name) {
		//alert(name);

		parent.window.setLayerSourceOfChild_2(name);
		//alert("layer2 of the first map changed success");
	}
	function formatInputs_1(val, row) {
		return "<a href='javascript:void(0);'  onclick='changeMap_1(\""
				+ row.name + "\")';" + ">" + "加载" + "</a>";
	}
	function formatInputs_2(val, row) {
		return "<a href='javascript:void(0);'  onclick='changeMap_2(\""
				+ row.name + "\")';" + ">" + "加载" + "</a>";
	}
	function test(a) {
		alert(a);
	}
</script>
</head>
<body>
	<div style="position:absolute;z-index:1; left: 0px; top: 0px;">
		<table class="easyui-datagrid" id="tableCal" url="easyuUIGetCal"
			style="width:510px;height:280px" rownumbers="true" singleSelect=true
			pagination="true" nowrap="false" striped="true">
			<thead>
				<tr>
					<th data-options="field:'id',width:80">ID</th>
					<th data-options="field:'name',width:100">图层名称</th>
					<th data-options="field:'dateStr',width:100">日期</th>
					<th data-options="field:'inp',width:100,formatter:formatInputs_1"></th>

				</tr>
			</thead>
		</table>
		<div style="margin:20px 0;">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="$('#dlg').dialog('open')">Open</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				onclick="$('#dlg').dialog('close')">Close</a>
		</div>
		<div id="dlg" class="easyui-dialog" title="Basic Dialog"
			data-options="iconCls:'icon-save'"
			style="width:400px;height:200px;padding:10px">
			<table class="easyui-datagrid" id="tableCal" url="easyuUIGetCal"
				style="width:510px;height:280px" rownumbers="true" singleSelect=true
				pagination="true" nowrap="false" striped="true">
				<thead>
					<tr>
						<th data-options="field:'id',width:80">ID</th>
						<th data-options="field:'name',width:100">图层名称</th>
						<th data-options="field:'dateStr',width:100">日期</th>
						<th data-options="field:'inp',width:100,formatter:formatInputs_1"></th>

					</tr>
				</thead>
			</table>
		</div>
	</div>
</body>
</html>