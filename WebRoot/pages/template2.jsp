<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>&nbsp;</title>
<script type="text/javascript" src="../easyui/jquery.min.js "></script>
<script type="text/javascript" src="../easyui/jquery.easyui.min.js "></script>
<script type="text/javascript" src="../js/template3.js"></script>
<script type="text/javascript">
	function test() {
		alert(document.getElementById("datestr").innerText);
	}
	function getClazz() {
		//alert(document.getElementById("reportnumber").innerText);
		var clazz = JSON
				.stringify({
					"reportnumber" : document.getElementById("reportnumber").innerText,
					"datestr" : document.getElementById("datestr").innerText,
					"machinenumber" : document.getElementById("machinenumber").innerText,
					"airport" : document.getElementById("airport").innerText,
					"projectname" : document.getElementById("projectname").innerText,
					"projectlocation" : document
							.getElementById("projectlocation").innerText,
					"latlon" : document.getElementById("latlon").innerText,
					"description" : document.getElementById("description").innerText,
					"pictureUrl" : 'xxx',
					"feedback" : document.getElementById("feedback").innerText,
					"backprojectname" : document
							.getElementById("backprojectname").innerText,
					"marineunit" : document.getElementById("marineunit").innerText,
					"city" : document.getElementById("city").innerText,
					"islegal" : document.getElementById("islegal").innerText,
					"route" : document.getElementById("route").innerText
				});
		
		$.ajax({
			method : 'POST',
			url : "updateReport",
			data : {jsonString : clazz},
			success : callBackFunc,
			dataType : "json"
		});
		function callBackFunc() {
			alert("update success");
		}

	}
</script>
<style type="text/css">
tr {
	text-align: center;
}
</style>
</head>
<body>
	<a onclick="getClazz()">xxx</a>
	<p>&nbsp;</p>
	<h2 align="center">
		<img src="../island_data/logo.jpg" alt="logo" width="79" height="78"
			hspace="12" align="absmiddle" />南海区<strong>“空-地”行政执法情况通报和反馈表</strong>
	</h2>
	<div align="center">
		<table>
			<tr>
				<th>编号：
				</td>
				<td id="reportnumber">${requestScope.lReport.reportnumber}</td>
			</tr>
		</table>
	</div>
	<div align="center">
		<table width="742" height="772" border="1" cellpadding="0"
			cellspacing="0">
			<tr>
				<th width="15%">日期</th>
				<td width="20%" id="datestr">${requestScope.lReport.datestr}</td>
				<th width="15%">机号</th>
				<td width="20%" id="machinenumber">
					${requestScope.lReport.machinenumber}</td>
				<th width="15%">机场</th>
				<td width="15%" colspan="2" id="airport">${requestScope.lReport.airport}</td>
			</tr>
			<tr>
				<th>航  线</th>
				<td colspan="6" id="route">${requestScope.lReport.route}</td>
			</tr>
			<tr>
				<th>项目名称</th>
				<td colspan="6" id="projectname">
					${requestScope.lReport.projectname}</td>
			</tr>
			<tr>
				<th>项目位置</th>
				<td colspan="2" id="projectlocation">
					${requestScope.lReport.projectlocation}</td>
				<th width="94">经纬度坐标</th>
				<td colspan="3" id="latlon">${requestScope.lReport.latlon}</td>
			</tr>
			<tr>
				<th height="60">情况描述</th>
				<td colspan="6" id="description">${requestScope.lReport.description}</td>
			</tr>
			<tr>
				<th height="235">附图</th>
				<%--上传且显示图片 --%>
				<th colspan="6"><input type="file" name="file" id="file0"
					onchange="preview(this.files[0],0)" multiple="multiple"> <br>
					<img src="" width="99%" id="img0"></th>

			</tr>
			<tr>
				<th rowspan="3"><strong>情况反馈</strong></th>
				<th>项目名称</th>
				<td colspan="5" id="backprojectname">
					${requestScope.lReport.backprojectname}</td>
			</tr>
			<tr>
				<th>用海单位</th>
				<td id="marineunit">${requestScope.lReport.marineunit}</td>
				<th>所属市县</th>
				<td id="city">${requestScope.lReport.city}</td>
				<th>是否合法</th>
				<td id="islegal">${requestScope.lReport.islegal}</td>
			</tr>
			<tr>
				<td height="211" colspan="6" id="feedback" align="left">
					${requestScope.lReport.feedback}</td>
			</tr>

		</table>
		<input type="button" value="打 印" name="butt"
			onClick="javascript:hide()">
	</div>
	<a href="getReport?number=20141231GX020">点击</a>
</body>
</html>