<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>主页</title>
<link rel="stylesheet" type="text/css" href="../easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="../easyui/icon.css">
<link rel="stylesheet" type="text/css" href="../easyui/demo.css">
<script type="text/javascript" src="../easyui/jquery.min.js "></script>
<script type="text/javascript" src="../easyui/jquery.easyui.min.js "></script>
<script type="text/javascript">
	/*  
		设置part1、part2的可见性
	 */
	function setPart1() {
		var part1 = document.getElementById('part1');
		part1.style.display = 'block';
	}

	function setPart2() {
		var part2 = document.getElementById('part2');
		part2.style.display = 'block';
	}

	function addVisConOfAddLayer(layerName) {
		setPart1();
		$("#layerList").append(
				"<input class='visible' type='checkbox' checked='checked' "
						+ "value=" + layerName + " onchange="
						+ "parent.window.frames[" + "'childPage'"
						+ "].selectLayer(this.value)" + " />" + layerName
						+ "<br/>");
	}

	function showAttributes(feature) {
		setPart2();
		document.getElementById("layerName").value = (feature.layerName === "error" ? "未选中"
				: feature.layerName );
		document.getElementById("fid").value = (feature.fid === "error" ? "未选中"
				: feature.fid );
		document.getElementById("islandId").value = (feature.islandId === "error" ? "未选中"
				: feature.islandId );
		document.getElementById("islandName").value = (feature.islandName === "error" ? "未选中"
				: feature.islandName );
		document.getElementById("thumbnail").value = (feature.thumbnail === "error" ? "未选中"
				: feature.thumbnail );
	}

	function updateAttribute(name, value) {
		var layerName = document.getElementById("layerName").value;
		var fid = document.getElementById("fid").value;
		//alert(layerName+fid+name+value);
		$.ajax({
			method : 'POST',
			url : "updateShpAttribute",
			data : {
				layerName : layerName,
				fid : fid,
				attribute : name,
				value : value
			},
			success : callBackInfo,
			dataType : "json"
		});
	}

	function callBackInfo(json) {
		topLeft("更新结果：" + json);
	}
	function topLeft(msg) {
		$.messager.show({
			title : 'message',
			msg : msg,
			showType : 'show',
			style : {
				right : '',
				left : 0,
				top : document.body.scrollTop
						+ document.documentElement.scrollTop,
				bottom : ''
			}
		});
	}
	//  此函数已用刷新子页面代替
	// 	function clearLayerList(){
	// 		document.getElementById("layerList").innerHTML="";
	// 	}
</script>
</head>
<body>
	<div id="part1" style="display:none">
		<p id="layerList"></p>
	</div>
	<div id="part2"
		style="position:absolute;display:none;z-index:1; left: 0px; top:0px">
		<table>
			<tr>
				<td >图层:</td>
				<td ><input class="easyui-validatebox textbox"
					name="feature.layerName" id="layerName" readonly="true"></td>
			</tr>
			<tr>
				<td>要素:</td>
				<td><input class="easyui-validatebox textbox"
					name="feature.fid" id="fid" readonly="true"></td>
			</tr>
			<tr>
				<td>编号:</td>
				<td><input class="easyui-validatebox textbox"
					data-options="required:true" name="feature.cat" id="islandId"
					onchange="updateAttribute('ISLANDID',this.value)"></td>
			</tr>
			<tr>
				<td>海岛:</td>
				<td><input class="easyui-validatebox textbox"
					data-options="required:true" name="feature.objectId"
					id="islandName" onchange="updateAttribute('ISLANDNAME',this.value)"></td>
			</tr>
			<tr>
				<td>图片:</td>
				<td><input class="easyui-validatebox textbox"
					data-options="required:true" name="feature.area" id="thumbnail"
					onchange="updateAttribute('THUMBNAIL',this.value)"></td>
			</tr>
		</table>
	</div>
</body>
</html>