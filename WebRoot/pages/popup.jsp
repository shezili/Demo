<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
	<link rel="stylesheet" type="text/css" href="../ol3/ol.css">
	<link rel="stylesheet" type="text/css" href="../ol3/bootstrap.min.css" >
	<link rel="stylesheet" type="text/css" href="../ol3/layout.css" >
	<link rel="stylesheet" type="text/css" href="../ol3/bootstrap-responsive.css">
	<link rel="stylesheet" type="text/css" href="../easyui/easyui.css">
	<link rel="stylesheet" type="text/css" href="../easyui/icon.css">
	<link rel="stylesheet" type="text/css" href="../easyui/demo.css">
	<script type="text/javascript" src="../easyui/jquery.min.js "></script>
	<script type="text/javascript" src="../easyui/jquery.easyui.min.js "></script>
	<script type="text/javascript" src="../ol3/ol-debug.js"></script>
	<script type="text/javascript" src="../ol3/base.js"></script>
	<script type="text/javascript" src="../js/popup.js" charset="gb2312"></script>
    <style type="text/css">
      .ol-popup {
        display: none;
        position: absolute;
        background-color: white;
        -moz-box-shadow: 0 1px 4px rgba(0,0,0,0.2);
        -webkit-filter: drop-shadow(0 1px 4px rgba(0,0,0,0.2));
        filter: drop-shadow(0 1px 4px rgba(0,0,0,0.2));
        padding: 15px;
        border-radius: 10px;
        border: 1px solid #cccccc;
        bottom: 12px;
        left: -50px;
      }
      .ol-popup:after, .ol-popup:before {
        top: 100%;
        border: solid transparent;
        content: " ";
        height: 0;
        width: 0;
        position: absolute;
        pointer-events: none;
      }
      .ol-popup:after {
        border-top-color: white;
        border-width: 10px;
        left: 48px;
        margin-left: -10px;
      }
      .ol-popup:before {
        border-top-color: #cccccc;
        border-width: 11px;
        left: 48px;
        margin-left: -11px;
      }
      .ol-popup-closer {
        text-decoration: none;
        position: absolute;
        top: 2px;
        right: 8px;
      }
      .ol-popup-closer:after {
        content: "✖";
      }
      .ol-popup-more {
        text-decoration: none;
        position: absolute;
        top: 2px;
        left: 8px;
      }
      .ol-popup-more:after {
        content: "更多";
      }
/*       .ol-popup-edit { */
/*         text-decoration: none; */
/*         position: absolute; */
/*         bottom: 2px; */
/*         left: 8px; */
/*       } */
/*       .ol-popup-edit:after { */
/*         content: "edit"; */
/*       } */
/*      body { */
/*      	background-image:url(../ol3/textured_paper.jpeg); */
/*      } */
    </style>
</head>
<body  onload="init()" >


	<div id="map" class="map">
		<div id="popup" class="ol-popup">
			<a href="#" id="popup-closer" class="ol-popup-closer"></a>
			<a href="#" onclick="openPictureWindow()" id="popup-more" class="ol-popup-more"></a>
<!-- 			<a id="getAttributesValue"  onclick = "getAttributesValue()"class="ol-popup-edit"></a> -->
				<img id="picture" src="" width='128' height='128' />

		</div>
	</div>


<!-- 	<div id="nodelist"> -->
<!-- 		<em>Click on the map to get feature info</em> -->
<!-- 	</div> -->



	<div id="pictureWindow" name="pictureWindow" class="easyui-window"
		title="图片一览" closed="true"
		style="width:400px;height:auto;top:20px;left: 5px; top: 5px;">
		<table id="dg" style="width:380px;height:500px" fitColumns = "true"
			singleSelect="true" pagination="true" >
			<thead>
				<tr>
					<th field="picture">图片</th>
					<th field="time" width="200">时间</th>
					<th field="os1" width="200">设为主缩略图</th>
				</tr>
			</thead>
		</table>
	</div>





</body>
</html>