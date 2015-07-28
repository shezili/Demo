<%@ page language="java" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'haidao.jsp' starting page</title>

<script type="text/javascript" src="../js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="../js/template3.js"></script>
</head>
<body>


	<p>&nbsp;</p>
	<p>&nbsp;</p>


	<h2 align="center">
		<strong>海岛信息报告</strong>
	</h2>
	<div align="center">
		<form action="" method=post name=form>
			<table width="1000" height="1200" border="1" cellpadding="0"
				cellspacing="0" >
				<tr>
					<th width="250"><div align="center">海岛名称</div></th>
					<td width="250" id="hdmc"><div align="center"></div></td>
					<th width="250"><div align="center">最新航摄日期</div></th>
					<td width="250" id="hprq"><div align="center"></div></td>
				</tr>
				<tr>
					<th><div align="center">行政隶属</div></th>
					<td colspan="3" id="xzls"><div align="center"></div></td>
				</tr>
				<tr>
					<th><div align="center">海岛分类</div></th>
					<td colspan="" id="hdfl"><div align="center"></div></td>
					<th><div align="center">经纬度</div></th>
					<td colspan="" id="jwdu"><div align="center"></div></td>
				</tr>
				<tr>
					<th><div align="center">面积</div></th>
					<td colspan="" id="ianji"><div align="center"></div></td>
					<th><div align="center">岸线长度</div></th>
					<td colspan="" id="anxiancd"><div align="center"></div></td>
				</tr>
				<tr>
					<th><div align="center">概况描述</div></th>
					<td colspan="3" id="gkms"><div align="center"></div></td>
				</tr>
				<tr>
					<th height="235"><div align="center">最新航空遥感正射影像缩略图</div></th>
					<%--上传且显示图片 --%>
					<th colspan="3"><div align="center"></div>
						<P id="df">
							<input type="file" name="file" id="file0"
								onchange="preview(this.files[0],0)" multiple="multiple">
							<br> <img src="" width="99%" id="img0">
						</P></th>
				</tr>
				<tr>
					<th><div align="center">历次航拍时间</div></th>
					<td colspan="3" id="lchpsj"><div align="center"></div></td>
				</tr>
				<tr>
					<th><div align="center">海岛变化情况</div></th>
					<td colspan="3" id="bhqk"><div align="center"></div></td>
				</tr>
				<tr style="width: 700px; ">
					<th height="200"><div align="center">侧方照片</div></th>
					<%--上传且显示图片 --%>
					<th colspan=""><div align="center"></div>
						<P id="df">
							<input type="file" name="file" id="file1"
								onchange="preview(this.files[0],1)" multiple="multiple">
							<br> <img src="" width="99%" id="img1">
						</P></th>
					<th colspan=""><div align="center"></div>
						<P id="df">
							<input type="file" name="file" id="file2"
								onchange="preview(this.files[0],2)" multiple="multiple">
							<br> <img src="" width="99%" id="img2">
						</P></th>
					<th colspan=""><div align="center"></div>
						<P id="df">
							<input type="file" name="file" id="file3"
								onchange="preview(this.files[0],3)" multiple="multiple">
							<br> <img src="" width="99%" id="img3">
						</P></th>
				</tr>
				<tr>
					<th><div align="center">其他</div></th>
					<td colspan="3" id="qt"><div align="center"></div></td>
				</tr>

			</table>
			<input type="button" value="打 印" name="butt"
				onClick="javascript:hide()">

		</form>

	</div>

</body>
</html>