<%@ page language="java" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'tijiao.jsp' starting page</title>

<script type="text/javascript" src="../js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="../js/template2.js"></script>
</head>
<body>


	<p>&nbsp;</p>
	<p>&nbsp;</p>


	<h2 align="center">
		<img src="imgs/tubiao.jpg" alt="logo" width="79" height="78"
			hspace="12" align="absmiddle" />南海区<strong>“空-地”行政执法情况通报和反馈表</strong>
	</h2>
	<div align="center">编号： 20141231GX020</div>
	<div align="center">
		<form action="" method=post name=form>
			<table width="742" height="772" border="1" cellpadding="0"
				cellspacing="0">

				<tr>
					<td width="94"><div align="center">日期</div></td>
					<td width="169" id="date"><div align="center"></div></td>
					<td width="59"><div align="center">机号</div></td>
					<td colspan="2" id="flight"><div align="center"></div></td>
					<td width="54"><div align="center">机场</div></td>
					<td width="167" id="airport"><div align="center"></div></td>
				</tr>
				<tr>
					<td><div align="center">航  线</div></td>
					<td colspan="6" id="route"><div align="center"></div></td>
				</tr>
				<tr>
					<td><div align="center">项目名称</div></td>
					<td colspan="6" id="name"><div align="center"></div></td>
				</tr>
				<tr>
					<td><div align="center">项目位置</div></td>
					<td colspan="3" id="location"><div align="center"></div></td>
					<td width="94"><div align="center">经纬度坐标</div></td>
					<td colspan="2" id="jingwei"><div align="center"></div></td>
				</tr>
				<tr>
					<td height="60"><div align="center">情况描述</div></td>
					<td colspan="6" id="situation"><div align="center"></div></td>
				</tr>
				<tr>
					<td height="235"><div align="center">附图</div></td>
					<%--上传且显示图片 --%>
					<td colspan="6"><div align="center"></div>
						<P id="df">
							<input type="file" name="file" id="file0"
								onchange="preview(this.files[0],0)" multiple="multiple">
							<br> <img src=""  width="99%" id="img0">
						</P>
				</tr>
				<tr>
					<td rowspan="3"><div align="center">
							<strong>情况反馈</strong>
						</div></td>
					<td><div align="center">项目名称</div></td>
					<td colspan="5" id="mc"><div align="center"></div></td>
				</tr>
				<tr>
					<td><div align="center">用海单位</div></td>
					<td><div align="center" id="danwei"></div></td>
					<td width="59"><div align="center">所属市县</div></td>
					<td><div align="center" id="suoshu"></div></td>
					<td><div align="center">是否合法</div></td>
					<td><div align="center" id="sfhf"></div></td>
				</tr>
				<tr>
					<td height="211" colspan="6" id="fankui"><div align="center"></div>
					</td>
				</tr>

			</table>
			<input type="button" value="打 印" name="butt"
				onClick="javascript:hide()">

		</form>

	</div>

</body>
</html>