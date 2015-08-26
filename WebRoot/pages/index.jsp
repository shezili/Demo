<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html  style="height: 100%;">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>主页</title>
		<link rel="stylesheet" type="text/css"  href="../easyui/easyui.css">
		<link rel="stylesheet" type="text/css"  href="../easyui/icon.css">
		<link rel="stylesheet" type="text/css"  href="../easyui/demo.css">
		<script type="text/javascript" src="../easyui/jquery.min.js "></script>
		<script type="text/javascript" src="../easyui/jquery.easyui.min.js "></script>
		<script type="text/javascript" src="../js/index.js "></script>	
		<script type="text/javascript" >
			var value = 345;
			function testindex(){
				 alert("123");
			}
	
		</script>
		<style type="text/css">
			[data-options],[title]{
				background-color:#E2EDF3;
			}
				[data-options],[title]{
				background-color:#E2EDF3;
			}

		</style>
</head>
<body  style="height: 100%">
	<div class="easyui-layout" style="width:100%;height:100%">
		<div data-options="region:'north'" style="height:7%"><img src="../img/3.jpg" style="width:100%;height:100%"></div>
		<div data-options="region:'south',split:true" style="height:7%;background-color:#0595DE"></div>
		<div data-options="region:'east',split:true,iconCls:'icon-chart'" title="  " style="width:15%;height:86%">
<!-- 			<p id="layerList"></p> -->
			<iframe id="innerpageEast" name="childPageEast" src="eastLayerList.jsp"  height = "99%" width = "98%"></iframe>
		</div>
		<div data-options="region:'west',split:true,iconCls:'icon-smartart'" data-options="selected:true" title="功能选项" style="width:15%;height:86%">
			<div class="easyui-accordion" data-options="fit:true,border:false">
				<div  title="成果浏览" style="padding:10px;">
						<table style="width:170px;height:220px" border="0" >
							<tr>
								<td>									
									<a class="easyui-linkbutton c5" style="width:70px" onclick = "changesrc('download.jsp')">数据下载</a>
								</td>
								<td>
								    <a class="easyui-linkbutton c6" style="width:70px" onclick = "changesrc('swipelayer.jsp');">卷帘显示</a>
								</td>
							</tr>
							<tr>
								<td>
									<a class="easyui-linkbutton c7" style="width:70px" onclick = "changesrc('addlayer.jsp');">图层叠加</a>
								</td>
								<td>
									<a class="easyui-linkbutton c8" style="width:70px" onclick = "changesrc('multidate2.jsp');">时相选择</a>
								</td>
							</tr>
							<tr>
								<td>
									<a class="easyui-linkbutton c9" style="width:70px" onclick = "changesrc('China.jsp');">矢量编辑</a>
								</td>						
							</tr>
					</table>
				</div>
				<div title="变化检测"  style="padding:10px;">
					content2</div>
				<div title="海岛数据" style="padding:10px">
					<table style="width:170px;height:220px" border="0" >
							<tr>
								<td>									
									<a class="easyui-linkbutton c1" style="width:70px" onclick="changesrc('osfile.jsp');">添加数据</a>
								</td>
								<td>
								    <a class="easyui-linkbutton c2" style="width:70px" onclick="changesrc('metadata.jsp');">数据查询</a>
								</td>
							</tr>
					</table>
				</div>
				<div title="报告报表" style="padding:10px">
						<table style="width:170px;height:220px" border="0" >
							<tr>
								<td>									
									<a class="easyui-linkbutton c1" style="width:70px" href="template2.jsp" target="_blank">报表</a>
								</td>
								<td>
								    <a class="easyui-linkbutton c2" style="width:70px" onclick="">报表2</a>
								</td>
							</tr>
					</table>
				</div>
			</div>
		</div>
		<div data-options="region:'center',title:'数据显示',iconCls:'icon-search'">
<!-- 			<div class="easyui-tabs" style="width:100%;height:550px"> -->
<!-- 				<div title="Image Display" style="padding:10px"> -->
					<iframe id="innerpage" name="childPage" src="" scrolling="no" height = "99%" width = "99%"></iframe>
<!-- 				</div> -->
<!-- 				<div title="Catalog" style="padding:10px"> -->
<!-- 					<iframe src="Catalog!displayCatalog?pageNo=1"  scrolling="no" height = "100%" width = "100%"></iframe> -->
<!-- 				</div> -->
<!-- 			</div> -->
		</div>
	</div>
</body>
</html>