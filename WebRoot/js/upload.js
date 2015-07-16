var flag1 = 0;
function showUploadDlg() {
	if (flag1 == 0) {
		$('#uploadDlg').dialog('open');
		flag1 = 1;
	} else {
		$('#uploadDlg').dialog('close');
		flag1 = 0;
	}
};



function showPictureDlg(picturesrc) {
	//判断picturesrc是否未定义
	if ((picturesrc == null) || (picturesrc == "undefined") 
			|| (picturesrc == "")) {
		topLeft('该文件不是图像');
	} else {
		document.getElementById('picture').src = picturesrc;
		$('#pictureDlg').dialog('open');
	}

};


function deleteFile(path) {
	$.ajax({
		method : 'POST',
		url : "file!deleteFile",
		data : {
			deleteDirectory : path
		},
		success : deleteFileCallBack,
		dataType : "json"
	});
}

function deleteFileCallBack(json) {
	getDatagridData(json.parent);
}

function topLeft(msg) {
	$.messager.show({
		title : 'Message',
		msg : msg,
		showType : 'show',
		style : {
			right : '',
			left : 0,
			top : document.body.scrollTop + document.documentElement.scrollTop,
			bottom : ''
		}
	});
}

function formatInputs_1(val, row) {
	return "<a href='javascript:void(0);'  onclick='showPictureDlg(\"" + row.previewInfo + "\")';"
			+ ">" + "预览" + "</a>";
}
	function formatInputs_2(val, row) {
	return "<a href='javascript:void(0);'  onclick='getDatagridData(\"" + row.fullName + "\")';" + ">" + "进入"
			+ "</a>";
}
	function formatInputs_3(val, row) {
	return "<a href='javascript:void(0);'  onclick='deleteFile(\"" + row.fullName + "\")';" + ">"
			+ "删除" + "</a>";
}
	
var parent;
var current;
function getDatagridData(url){
	$("#tableCal").datagrid({
		url : "file!browseFile?currentDirectory="+url,
		onLoadSuccess:function(data){
			parent = data.parent;
			current = data.current;
		}
	});
}
var toolbar = [ {
	text : '上一层',
	iconCls : 'icon-high',
	handler : function() {
		getDatagridData(parent);
	}
}, '-', {
	text : '当前绝对路径',
	iconCls : 'icon-search',
	handler : function() {
		info(current);
	}
}, '-', {
	text : '上传文件',
	iconCls : 'icon-add',
	handler : function() {
		showUploadDlg();
	}
} ];
function info(name) {
	$.messager.alert('当前路径：', current, 'info');
}