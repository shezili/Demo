<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
<script type="text/javascript" src="../js/index.js "></script>

</head>
<body>
	<h2>Client Side Pagination in DataGrid</h2>
	<p>This sample shows how to implement client side pagination in
		DataGrid.</p>
	<div style="margin:20px 0;"></div>

	<table id="dg" title="Client Side Pagination"
		style="width:450px;height:300px"
		data-options="
				rownumbers:true,
				singleSelect:true,
				autoRowHeight:false,
				pagination:true,
				pageSize:10">
		<thead>
			<tr>
				<th field="inv" width="80">Inv No</th>
				<th field="date" width="100">Date</th>
				<th field="name" width="80">Name</th>
				<th field="os" width="80">test</th>
			</tr>
		</thead>
	</table>
	<a onclick="getDate();">测试</a>
	<script>
		var records;
		function getDate() {
			//	var date=document.getElementById("date");
			//	date.innerHTML = startDate + endDate + (startDate<endDate);

			$.ajax({
				method : 'POST',
				url : "dateInterval",
				data : {
					startTime : '2013-01-01',
					endTime : '2017-01-01'
				},
				success : dateIntervalCallBack,
				dataType : "json"
			});

		}

		function dateIntervalCallBack(jsons) {
			//	for(var i=0;i<jsons.length;i++){
			//		$("#date").append(jsons[i].id+"\t"+jsons[i].name+"\t"+jsons[i].dateStr+"<br/>");
			//	}
			records = jsons;
			refreash();

		}
		function getData() {
			var rows = [];
			for (var i = 0; i < records.length; i++) {
				var id = records[i].id;
				var name = records[i].name;
				var date = records[i].dateStr;
				var os = "<a onclick='test();'>" + "测试" + "</a>";
				rows.push({
					inv : id,
					date : date,
					name : name,
					os : os,
				});
			}
			return rows;
		}

		function pagerFilter(data) {
			if (typeof data.length == 'number'
					&& typeof data.splice == 'function') { // is array
				data = {
					total : data.length,
					rows : data
				}
			}
			var dg = $(this);
			var opts = dg.datagrid('options');
			var pager = dg.datagrid('getPager');
			pager.pagination({
				onSelectPage : function(pageNum, pageSize) {
					opts.pageNumber = pageNum;
					opts.pageSize = pageSize;
					pager.pagination('refresh', {
						pageNumber : pageNum,
						pageSize : pageSize
					});
					dg.datagrid('loadData', data);
				}
			});
			if (!data.originalRows) {
				data.originalRows = (data.rows);
			}
			var start = (opts.pageNumber - 1) * parseInt(opts.pageSize);
			var end = start + parseInt(opts.pageSize);
			data.rows = (data.originalRows.slice(start, end));
			return data;
		}

		$(function() {
			$('#dg').datagrid({
				loadFilter : pagerFilter
			}).datagrid('loadData', getData());
		});

		function refreash() {
			$('#dg').datagrid({
				loadFilter : pagerFilter
			}).datagrid('loadData', getData());
		};
		function test() {
			alert("hello");
		}
	</script>

</body>
</html>