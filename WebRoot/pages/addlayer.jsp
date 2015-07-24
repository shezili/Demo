<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" href="../ol3/ol.css">
<link rel="stylesheet" href="../ol3/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="../ol3/layout.css" type="text/css">
<link rel="stylesheet" href="../ol3/bootstrap-responsive.css" type="text/css">
<link rel="stylesheet" type="text/css" href="../easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="../easyui/icon.css">
<link rel="stylesheet" type="text/css" href="../easyui/demo.css">
<script type="text/javascript" src="../easyui/jquery.min.js "></script>
<script type="text/javascript" src="../easyui/jquery.easyui.min.js "></script>
<script type="text/javascript" src="../js/addlayer.js"></script>
<script type="text/javascript" src="../ol3/ol-debug.js"></script>
<script type="text/javascript" src="../ol3/base.js"></script>
<script type="text/javascript">
	function formatInputs_1(val, row) {
		return "<a href='javascript:void(0);'  onclick='setLayerSource_1(\""
				+ row.name + "\")';" + ">" + "加载" + "</a>";
	}

	var flag = 0;
	function showCatalog() {
		if (flag == 0) {
			$('#catalogwindow').window('open');
			flag = 1;
		} else {
			$('#catalogwindow').window('close');
			flag = 0;
		}
	}
</script>
</head>
<body onload="init('test:work1')" background="../ol3/textured_paper.jpeg">
	<a onclick="showCatalog();" class="easyui-linkbutton">目录</a>
	<div id="map" class="map"></div>
	<div id="catalogwindow" name="paramWindow" class="easyui-window"
		title="图层列表" closed="true"
		style="width:410px;height:290px;top:20px;left:500px">
		<table class="easyui-datagrid" id="tableCal" url="easyuUIGetCal"
			style="width:400px;height:260px" rownumbers="true" singleSelect=true
			pagination="true" nowrap="false" striped="true">
			<thead>
				<tr>
					<th data-options="field:'name',width:100">图层名称</th>
					<th data-options="field:'dateStr',width:100">日期</th>
					<th data-options="field:'inp',width:100,formatter:formatInputs_1"></th>

				</tr>
			</thead>
		</table>
	</div>


</body>
</html>