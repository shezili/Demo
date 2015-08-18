<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>时间轴</title>
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
<script type="text/javascript" src="../js/multidate2.js"></script>
<script type="text/javascript">
	var flag = 0;
	function showDlg() {
		if (flag == 0) {
			$('#dlg').dialog('close');
			flag = 1;
		} else {
			$('#dlg').dialog('open');
			flag = 0;
		}
	}
</script>
</head>
<body onload="init1('test:work1');init2('test:work2')" background="../ol3/textured_paper.jpeg">

	<div style="width:50%;height:490px;float:left;">

		<div id="map1" style="width:100%;height:100%"></div>
		<center>
			<input class="easyui-slider" style="width:300px"
				data-options="
				showTip: true,
				tipFormatter: function(value){
					return date1+name1;
				},
				onChange: function(value){
					showDate1(value);}">

			<!-- 					<input class="easyui-slider" value="1" style="width:300px" min="1" max="12" data-options=" -->
			<!-- 							showTip: true, -->
			<!-- 							tipFormatter: function(value){ -->
			<!-- 							return value+'月'; -->
			<!-- 						}, -->
			<!-- 							onChange: function(value){ -->
			<!-- 								checkmap1Slider(value); -->
			<!-- 					}"> -->
		</center>
	</div>

	<div style="width:50%;height:490px;float:left;">
		<div id="map2" style="width:100%;height:100%"></div>
		<center>
			<input class="easyui-slider" style="width:300px"
				data-options="
				showTip: true,
				tipFormatter: function(value){
					return date2+name2;
				},
				onChange: function(value){
					showDate2(value);}">
			<!-- 					<input class="easyui-slider" value="2" style="width:300px" min="1" max="12" data-options=" -->
			<!-- 							showTip: true, -->
			<!-- 							tipFormatter: function(value){ -->
			<!-- 							return value+'月'; -->
			<!-- 						}, -->
			<!-- 							onChange: function(value){ -->
			<!-- 								checkmap2Slider(value); -->
			<!-- 					}"> -->
		</center>
	</div>
	<a onclick="showDlg();" class="easyui-linkbutton">日期选择器</a>
	<div id="dlg" class="easyui-dialog" title="Select Date"
		style="width:350px;height:150px;padding:10px">
		<p>
			StartTime:<input class="easyui-datebox"
				data-options="onSelect:SelectStartDate"></input>
		</p>
		<p>
			End Time:<input class="easyui-datebox"
				data-options="onSelect:SelectEndDate"></input>
		</p>
		<p>
			<a onclick="getDate();" class="easyui-linkbutton"
				data-options="iconCls:'icon-search'" style="width:80px">Search</a> <b
				id="layerName"></b>
		</p>
	</div>


</body>
</html>