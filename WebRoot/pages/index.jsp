<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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

</head>
<body>
	<div class="easyui-layout" style="width:100%;height:680px;">
		<div data-options="region:'north'" style="height:60px"></div>
		<div data-options="region:'south',split:true" style="height:50px;"></div>
		<div data-options="region:'east',split:true" title="图层选择" style="width:220px;">
<!-- 			<p id="layerList"></p> -->
			<iframe id="innerpageEast" name="childPageEast" src="eastLayerList.jsp"  height = "99%" width = "98%"></iframe>
		</div>
		<div data-options="region:'west',split:true" data-options="selected:true" title="功能选项" style="width:200px;">
			<div class="easyui-accordion" data-options="fit:true,border:false">
				<div title="成果浏览" style="padding:10px;">
					<table class="easyui-datagrid" title=" " style="width:170px;height:220px">
						<thead>
							<tr>
								<th data-options="field:'itemid',width:80"></th>
								<th data-options="field:'productid',width:80"></th>
							</tr>
							<tr>
								<th data-options="field:'itemid',width:80">
									<a class="easyui-linkbutton c5" style="width:70px" onclick = "changesrc('download.jsp')">数据下载</a>
								</th>
								<th data-options="field:'productid',width:80">
								    <a class="easyui-linkbutton c6" style="width:70px" onclick = "changesrc('swipelayer.jsp');">卷帘显示</a>
								</th>
							</tr>
							<tr>
								<th data-options="field:'itemid',width:80"></th>
								<th data-options="field:'productid',width:80"></th>
							</tr>
							<tr>
								<th data-options="field:'itemid',width:80">
									<a class="easyui-linkbutton c7" style="width:70px" onclick = "changesrc('addlayer.jsp');">图层叠加</a>
								</th>
								<th data-options="field:'productid',width:80">
									<a class="easyui-linkbutton c8" style="width:70px" onclick = "changesrc('multidate2.jsp');">时相选择</a>
								</th>
							</tr>
							<tr>
								<th data-options="field:'itemid',width:80"></th>
								<th data-options="field:'productid',width:80"></th>
							</tr>
							<tr>
								<th data-options="field:'itemid',width:80">
									<a class="easyui-linkbutton c9" style="width:70px" onclick = "changesrc('popup.jsp');">弹窗显示</a>
								</th>
								<th data-options="field:'productid',width:80">
									<a class="easyui-linkbutton c8" style="width:70px" onclick="testindex();">矢量编辑</a>
								</th>
							</tr>
							<tr>
								<th data-options="field:'itemid',width:80"></th>
								<th data-options="field:'productid',width:80"></th>
							</tr>
						</thead>
					</table>		
				</div>
				<div title="变化检测"  style="padding:10px;">
					content2</div>
				<div title="海岛数据" style="padding:10px">
					<a class="easyui-linkbutton c8" style="width:70px" onclick="changesrc('osfile.jsp');">添加数据</a>
				</div>
				<div title="报告报表" style="padding:10px">content3</div>
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