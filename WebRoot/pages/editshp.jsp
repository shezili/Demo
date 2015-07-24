<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" type="text/css" href="../ol3/ol.css">
<link rel="stylesheet" type="text/css" href="../ol3/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../ol3/layout.css">
<link rel="stylesheet" type="text/css"
	href="../ol3/bootstrap-responsive.css">
<link rel="stylesheet" type="text/css" href="../easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="../easyui/icon.css">
<link rel="stylesheet" type="text/css" href="../easyui/demo.css">
<script type="text/javascript" src="../easyui/jquery.min.js "></script>
<script type="text/javascript" src="../easyui/jquery.easyui.min.js "></script>
<script type="text/javascript" src="../ol3/ol-debug.js"></script>
<script type="text/javascript" src="../ol3/base.js"></script>
<script type="text/javascript" src="../js/editshp.js"></script>

</head>
<body onload="init()" background="../ol3/textured_paper.jpeg">


	<div id="map" class="map"></div>

	<div id="nodelist">
		<em>Click on the map to get feature info</em>
	</div>

	<a onclick="showCatalog();" class="easyui-linkbutton" >修改属性</a>
	<div id="catalogwindow" name="paramWindow" class="easyui-window"
		title="属性修改" closed="true"	style="width:300px;height:210px;top:20px;left:500px">
		<form id="ff" action="showShpAttribute" method="post"
			enctype="multipart/form-data">
			<table>
				<tr>
					<td>图层名:</td>
					<td><input name="layerName" class="easyui-textbox"
						data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>Fid:</td>
					<td><input name="fid" class="easyui-textbox"
						data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>属性名:</td>
					<td><input name="attribute" class="easyui-textbox"
						data-options="required:true"></input></td>
				</tr>
				<tr>
					<td>值:</td>
					<td><input name="value" class="easyui-textbox"
						data-options="required:true"></input></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Submit"></input><a
						href="javascript:void(0)" class="easyui-linkbutton"
						onclick="clearForm()">Clear</a></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>