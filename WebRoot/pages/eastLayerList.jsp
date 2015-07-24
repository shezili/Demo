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
	function setPart1(){
		var part1 = document.getElementById('part1');
		part1.style.display = 'block';
	}
	
	function setPart2(){
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
	
	function showAttributes(feature){
		setPart2();
		document.getElementById("layerName").value =feature.layerName;
		document.getElementById("fid").value =feature.fid;
		document.getElementById("cat").value =feature.cat;
		document.getElementById("objectId").value =feature.objectId;
		document.getElementById("area").value =feature.area;
		document.getElementById("perimeter").value =feature.perimeter;
		document.getElementById("bg_d_town_").value =feature.bg_d_town_;
		document.getElementById("bg_d_town1").value =feature.bg_d_town1;
		document.getElementById("id").value =feature.id;
		document.getElementById("i__g__").value =feature.i__g__;
		document.getElementById("e__i__").value =feature.e__i__;
		document.getElementById("name").value =feature.name;
		document.getElementById("f__d__e__").value =feature.f__d__e__;
		document.getElementById("shape_leng").value =feature.shape_leng;
		document.getElementById("shape_area").value =feature.shape_area;
		document.getElementById("file_name").value =feature.file_name;
	}
	
	function updateAttribute(name,value){
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
	<div id = "part1" style="display:none">
		<p id="layerList"></p>
	</div>
	<div id= "part2" style="position:absolute;display:none;z-index:1; left: 0px; top:0px">
        <table >
            <tr>
                <td>layerName:</td>
                <td><input class="easyui-validatebox textbox"  name="feature.layerName" id="layerName" readonly="true"></td>
            </tr>
            <tr>
                <td>fid:</td>
                <td><input class="easyui-validatebox textbox" name="feature.fid" id="fid" readonly="true"></td>
            </tr>
            <tr>
                <td>cat:</td>
                <td><input class="easyui-validatebox textbox" data-options="required:true" name="feature.cat" id="cat" onchange="updateAttribute('cat',this.value)"></td>
            </tr>
                        <tr>
                <td>OBJECTID:</td>
                <td><input class="easyui-validatebox textbox" data-options="required:true" name="feature.objectId" id="objectId" onchange="updateAttribute('OBJECTID',this.value)"></td>
            </tr>
            <tr>
                <td>AREA:</td>
                <td><input class="easyui-validatebox textbox" data-options="required:true" name="feature.area" id="area" onchange="updateAttribute('AREA',this.value)"></td>
            </tr>
            <tr>
                <td>PERIMETER:</td>
                <td><input class="easyui-validatebox textbox" data-options="required:true" name="feature.perimeter" id="perimeter" onchange="updateAttribute('PERIMETER',this.value)"></td>
            </tr>
                        <tr>
                <td>BG_D_TOWN_:</td>
                <td><input class="easyui-validatebox textbox" data-options="required:true" name="feature.bg_d_town_" id="bg_d_town_" onchange="updateAttribute('BG_D_TOWN_',this.value)"></td>
            </tr>
            <tr>
                <td>BG_D_TOWN1:</td>
                <td><input class="easyui-validatebox textbox" data-options="required:true" name="feature.bg_d_town1" id="bg_d_town1" onchange="updateAttribute('BG_D_TOWN1',this.value)"></td>
            </tr>
            <tr>
                <td>ID:</td>
                <td><input class="easyui-validatebox textbox" data-options="required:true" name="feature.id" id="id" onchange="updateAttribute('ID',this.value)"></td>
            </tr>
            <tr>
                <td>i__g__:</td>
                <td><input class="easyui-validatebox textbox" data-options="required:true" name="feature.i__g__" id="i__g__" onchange="updateAttribute('i__g__',this.value)"></td>
            </tr>
            <tr>
                <td>e__i__:</td>
                <td><input class="easyui-validatebox textbox" data-options="required:true" name="feature.e__i__" id="e__i__" onchange="updateAttribute('e__i__',this.value)"></td>
            </tr>
            <tr>
                <td>NAME:</td>
                <td><input class="easyui-validatebox textbox" data-options="required:true" name="feature.name" id="name" onchange="updateAttribute('NAME',this.value)"></td>
            </tr>
            <tr>
                <td>f__d__e__:</td>
                <td><input class="easyui-validatebox textbox" data-options="required:true" name="feature.f__d__e__" id="f__d__e__" onchange="updateAttribute('f__d__e__',this.value)"></td>
            </tr>
            <tr>
                <td>SHAPE_Leng:</td>
                <td><input class="easyui-validatebox textbox" data-options="required:true" name="feature.shape_leng" id="shape_leng" onchange="updateAttribute('SHAPE_Leng',this.value)"></td>
            </tr>
            <tr>
                <td>SHAPE_Area:</td>
                <td><input class="easyui-validatebox textbox" data-options="required:true" name="feature.shape_area" id="shape_area" onchange="updateAttribute('SHAPE_Area',this.value)" ></td>
            </tr>
                        <tr>
                <td>FILE_NAME:</td>
                <td><input class="easyui-validatebox textbox" data-options="required:true" name="feature.file_name" id="file_name" onchange="updateAttribute('FILE_NAME',this.value)"></td>
            </tr>
            
        </table>
    </div>
</body>
</html>