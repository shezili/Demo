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
</head>
<body>
	<!--startprint1-->
	<table id="dg" class="easyui-datagrid" title="变化检测报告"
		style="width:1300px;height:auto" url="getReport1"
		data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				method: 'get',
				nowrap:false,
				onClickRow: onClickRow
			">
		<thead>
			<tr>
				<th data-options="field:'number',editor:'text',styler:style_1" width="5%">编号</th>
				<th data-options="field:'area',editor:'text',styler:style_2" width="10%">区域</th>
				<th data-options="field:'location',editor:'text',styler:style_3" width="15%">位置</th>
				<th data-options="field:'description',editor:'text',styler:style_4" width="20%">变化描述</th>
				<th data-options="field:'image1',editor:'text',styler:style_5" width="25%">2010年9月影像</th>
				<th data-options="field:'image2',editor:'text',styler:style_6" width="25%">2012年4月影像</th>
			</tr>
		</thead>
	</table>
	<!--endprint1-->
	<div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">Append</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">Remove</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">Accept</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject()">Reject</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="getChanges()">GetChanges</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-print',plain:true" onclick="preview(1)">Print</a>
	</div>
	
	
	<script type="text/javascript">

		function style_1(value, row, index) {
			return 'font-size: 20px;';
		}
		function style_2(value, row, index) {
			return 'font-size: 20px;';
		}
		function style_3(value, row, index) {
			return 'font-size: 20px;';
		}
		function style_4(value, row, index) {
			return 'font-size: 20px;';
		}
		function style_5(value, row, index) {
			return 'font-size: 20px;';
		}
		function style_6(value, row, index) {
			return 'font-size: 20px;';
		}

		var editIndex = undefined;
		function endEditing() {
			if (editIndex == undefined) {
				return true;
			}
			if ($('#dg').datagrid('validateRow', editIndex)) {
				$('#dg').datagrid('endEdit', editIndex);
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
		}
		function onClickRow(index) {
			if (editIndex != index) {
				if (endEditing()) {
					$('#dg').datagrid('selectRow', index).datagrid('beginEdit',
							index);
					editIndex = index;
				} else {
					$('#dg').datagrid('selectRow', editIndex);
				}
			}
		}
		function append() {
			if (endEditing()) {
				$('#dg').datagrid('appendRow', {
					status : 'P'
				});
				editIndex = $('#dg').datagrid('getRows').length - 1;
				$('#dg').datagrid('selectRow', editIndex).datagrid('beginEdit',
						editIndex);
			}
		}
		function removeit() {
			if (editIndex == undefined) {
				return true;
			}
			$('#dg').datagrid('cancelEdit', editIndex).datagrid('deleteRow',
					editIndex);
			editIndex = undefined;
		}
		function accept() {
			if (endEditing()) {
				$('#dg').datagrid('acceptChanges');
			}
		}
		function reject() {
			$('#dg').datagrid('rejectChanges');
			editIndex = undefined;
		}
		function getChanges() {
			var rows = $('#dg').datagrid('getChanges');
			alert(rows.length + ' rows are changed!');
		}
		function preview(oper) {
			var part1 = document.getElementById('tb');
			part1.style.display = 'none'; //隐藏工具条
			if (oper < 10) {
				bdhtml = window.document.body.innerHTML;//获取当前页的html代码
				sprnstr = "<!--startprint" + oper + "-->";//设置打印开始区域
				eprnstr = "<!--endprint" + oper + "-->";//设置打印结束区域
				prnhtml = bdhtml.substring(bdhtml.indexOf(sprnstr) + 18); //从开始代码向后取html

				prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr));//从结束代码向前取html
				window.document.body.innerHTML = prnhtml;
				window.print();
				window.document.body.innerHTML = bdhtml;

			} else {
				window.print();
			}
		}
	</script>
</body>
</html>