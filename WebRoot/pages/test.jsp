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
	function updateAttribute(name,value){
	alert("kkk1");
		var layerName = document.getElementById("layerName").value;
		var fid = document.getElementById("fid").value;
		
		$.ajax({
				method : 'POST',
				url : "updateShpAttribute",
				data : {
					layerName : 'test:CW_BG_TOWN',
					fid : 'CW_BG_TOWN.4',
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
</script>
</head>
<body>
<div class="easyui-panel" title="Register" style="width:260px">
        <table cellpadding="5">
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
                <td><input class="easyui-validatebox textbox" data-options="required:true" name="feature.cat" id="cat"></td>
            </tr>
                        <tr>
                <td>OBJECTID:</td>
                <td><input class="easyui-validatebox textbox" data-options="required:true" name="feature.objectId" id="objectId"></td>
            </tr>
            <tr>
                <td>AREA:</td>
                <td><input class="easyui-validatebox textbox" data-options="required:true" name="feature.area" id="area"></td>
            </tr>
            <tr>
                <td>PERIMETER:</td>
                <td><input class="easyui-validatebox textbox" data-options="required:true" name="feature.perimeter" id="perimeter"></td>
            </tr>
                        <tr>
                <td>BG_D_TOWN_:</td>
                <td><input class="easyui-validatebox textbox" data-options="required:true" name="feature.bg_d_town_" id="bg_d_town_"></td>
            </tr>
            <tr>
                <td>BG_D_TOWN1:</td>
                <td><input class="easyui-validatebox textbox" data-options="required:true" name="feature.bg_d_town1" id="bg_d_town1"></td>
            </tr>
            <tr>
                <td>ID:</td>
                <td><input class="easyui-validatebox textbox" data-options="required:true" name="feature.id" id="id"></td>
            </tr>
            <tr>
                <td>i__g__:</td>
                <td><input class="easyui-validatebox textbox" data-options="required:true" name="feature.i__g__" id="i__g__"></td>
            </tr>
            <tr>
                <td>e__i__:</td>
                <td><input class="easyui-validatebox textbox" data-options="required:true" name="feature.e__i__" id="e__i__"></td>
            </tr>
            <tr>
                <td>NAME:</td>
                <td><input class="easyui-validatebox textbox" data-options="required:true" name="feature.name" id="name"></td>
            </tr>
            <tr>
                <td>f__d__e__:</td>
                <td><input class="easyui-validatebox textbox" data-options="required:true" name="feature.f__d__e__" id="f__d__e__"></td>
            </tr>
            <tr>
                <td>SHAPE_Leng:</td>
                <td><input class="easyui-validatebox textbox" data-options="required:true" name="feature.shape_leng" id="shape_leng"></td>
            </tr>
            <tr>
                <td>SHAPE_Area:</td>
                <td><input class="easyui-validatebox textbox" data-options="required:true" name="feature.shape_area" id="shape_area" ></td>
            </tr>
                        <tr>
                <td>FILE_NAME:</td>
                <td><input class="easyui-validatebox textbox" data-options="required:true" name="feature.file_name" id="file_name"></td>
            </tr>
            <a onclick="updateAttribute('OBJECTID',10)">测试</a>
        </table>
    </div>
</body>
</html>