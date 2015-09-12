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

<script type="text/javascript">
	
function locateLayer( name ){
	$.ajax({
	   	 method:'POST',
		   	 url : "getMapBounds",
		   	 data : {
				layerName:name
		   	 },
		   	 success : locateLayerCallBack,
		   	 dataType : "json"
	  	 });
}

function locateLayerCallBack( json ){
	parent.window.addCovergae( json.fullName, json.bounds.map(Number));
	
}
	
</script>
</head>
<body>

<div class="easyui-panel" title="Register" style="width:260px">
        <a onclick = "locateLayer('JingZhou01')">ttt</a>
        <a onclick = "locateLayer('JingZhou02')">ttt2</a>
</div>
</body>
</html>