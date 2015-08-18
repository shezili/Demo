<%@ page language="java" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>

<script type="text/javascript" src="../easyui/jquery.min.js "></script>
<script type="text/javascript" src="../easyui/jquery.easyui.min.js "></script>
<script type="text/javascript" src="../js/template3.js"></script>
<style type="text/css">
tr {
	text-align: center;
}
</style>
</head>
<body>
	<p>&nbsp;</p>
	<h2 align="center">
		<strong>海岛信息报告</strong>
	</h2>
	<div align="center">
		<table width="742" height="772" border="1" cellpadding="0"
			cellspacing="0">
			<tr>
				<th width="18%">海岛名称</th>
				<td id="hdmc" width="30%" ></td>
				<th  width="22%" >最新航摄日期</th>
				<td id="hprq" width="30%" ></td>
			</tr>
			<tr>
				<th>行政隶属</th>
				<td colspan="3" id="xzls"></td>
			</tr>
			<tr>
				<th>海岛分类</th>
				<td id="hdfl"></td>
				<th>经纬度</th>
				<td id="jwdu"></td>
			</tr>
			<tr>
				<th>面积</th>
				<td id="ianji"></td>
				<th>岸线长度</th>
				<td id="anxiancd"></td>
			</tr>
			<tr>
				<th>概况描述</th>
				<td colspan="3" id="gkms"></td>
			</tr>
			<tr>
				<th height="235">最新航空遥感正射影像缩略图</th>
				<%--上传且显示图片 --%>
				<th colspan="3">
						<input type="file" name="file" id="file0"
							onchange="preview(this.files[0],0)" multiple="multiple">
						<br> <img src="" width="99%" id="img0">
				</th>
			</tr>
			<tr>
				<th>历次航拍时间</th>
				<td colspan="3" id="lchpsj"></td>
			</tr>
			<tr>
				<th>海岛变化情况</th>
				<td colspan="3" id="bhqk"></td>
			</tr>
			<tr>
				<th height="200">侧方照片</th>
				<%--上传且显示图片 --%>
				<th colspan="3">
						<input type="file" name="file" id="file1"
							onchange="preview(this.files[0],1)" multiple="multiple">
						<br> <img src="" width="99%" id="img1">
				</th>
			</tr>
			<tr>
			    <th height="200">&nbsp;</th>
				<th colspan="3">
						<input type="file" name="file" id="file2"
							onchange="preview(this.files[0],2)" multiple="multiple">
						<br> <img src="" width="99%" id="img2">
				</th>
			</tr>
			<tr>
			    <th height="200">&nbsp;</th>
				<th colspan="3">
		  				   <input type="file" name="file" id="file3"
							onchange="preview(this.files[0],3)" multiple="multiple">
						<br> <img src="" width="99%" id="img3">
				</th>
			</tr>
			<tr>
				<th>其他</th>
				<td colspan="3" id="qt"></td>
			</tr>

		</table>
		<input type="button" value="打 印" name="butt"
			onClick="javascript:hide()">


	</div>

</body>
</html>