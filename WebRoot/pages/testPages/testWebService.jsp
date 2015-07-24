<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>web service 测试页面</title>

<link rel="stylesheet" type="text/css" href="../../easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="../../easyui/icon.css">
<link rel="stylesheet" type="text/css" href="../../easyui/demo.css">
<script type="text/javascript" src="../../easyui/jquery.min.js "></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js "></script>
</head>

<body>
	<div class="easyui-panel" title="发布栅格数据"
		style="width:400px;padding:10px 60px 20px 60px">
		<table cellpadding="5">
			<tr>
				<td>Url:</td>
				<td><input class="easyui-validatebox textbox"
					onblur="setUrl(this.value)" data-options="required:true,validType:'url'"></td>
			</tr>
			<tr>
				<td>User Name:</td>
				<td><input class="easyui-validatebox textbox"
					onblur="setUserName(this.value)" data-options="required:true"></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input class="easyui-validatebox textbox"
					onblur="setPassword(this.value)" data-options="required:true"></td>
			</tr>

			<tr>
				<td>PathName:</td>
				<td><input class="easyui-validatebox textbox"
					onblur="setPathName(this.value)" data-options="required:true"></td>
			</tr>
			<tr>
				<td>Workspace:</td>
				<td><input class="easyui-validatebox textbox"
					onblur="setWorkspace(this.value)" data-options="required:true"></td>
			</tr>
			<tr>
				<td>StoreName:</td>
				<td><input class="easyui-validatebox textbox"
					onblur="setStoreName(this.value)" data-options="required:true"></td>
			</tr>

			<tr>
				<td>CoverageName:</td>
				<td><input class="easyui-validatebox textbox"
					onblur="setCoverageName(this.value)" data-options="required:true"></td>
			</tr>
			<tr>
				<td>Format:</td>
				<td><input class="easyui-validatebox textbox"
					onblur="setFormat(this.value)" data-options="required:true"></td>
			</tr>
			<tr>
				<td>IsRepublish:</td>
				<td><input class="easyui-validatebox textbox"
					onblur="setIsRepublish(this.value)" data-options="required:true"></td>
			</tr>
		</table>
		 <a href="#" onclick="publishCoverage()" class="easyui-linkbutton" style="width:80px">发布服务</a>
		 <a href="#" onclick="disPublishCoverage()" class="easyui-linkbutton" style="width:80px">删除服务</a>
	</div>
	
	<div class="easyui-panel" title="发布矢量数据"
		style="width:400px;padding:10px 60px 20px 60px">
		<table cellpadding="5">
			<tr>
				<td>Url:</td>
				<td><input class="easyui-validatebox textbox"
					onblur="setUrl1(this.value)" data-options="required:true,validType:'url'"></td>
			</tr>
			<tr>
				<td>User Name:</td>
				<td><input class="easyui-validatebox textbox"
					onblur="setUserName1(this.value)" data-options="required:true"></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input class="easyui-validatebox textbox"
					onblur="setPassword1(this.value)" data-options="required:true"></td>
			</tr>

			<tr>
				<td>ShpPath:</td>
				<td><input class="easyui-validatebox textbox"
					onblur="setShpPath1(this.value)" data-options="required:true"></td>
			</tr>
			
			<tr>
				<td>FileName:</td>
				<td><input class="easyui-validatebox textbox"
					onblur="setFileName1(this.value)" data-options="required:true"></td>
			</tr>
			
			<tr>
				<td>Workspace:</td>
				<td><input class="easyui-validatebox textbox"
					onblur="setWorkspace1(this.value)" data-options="required:true"></td>
			</tr>
			<tr>
				<td>StoreName:</td>
				<td><input class="easyui-validatebox textbox"
					onblur="setStoreName1(this.value)" data-options="required:true"></td>
			</tr>

			<tr>
				<td>LayerName:</td>
				<td><input class="easyui-validatebox textbox"
					onblur="setLayerName1(this.value)" data-options="required:true"></td>
			</tr>
			<tr>
				<td>Srs:</td>
				<td><input class="easyui-validatebox textbox"
					onblur="setSrs1(this.value)" data-options="required:true"></td>
			</tr>
			<tr>
				<td>IsRepublish:</td>
				<td><input class="easyui-validatebox textbox"
					onblur="setIsRepublish1(this.value)" data-options="required:true"></td>
			</tr>
		</table>
		 <a href="#" onclick="publishShapefile()" class="easyui-linkbutton" style="width:80px">发布服务</a>
		 <a href="#" onclick="disPublishShapefile()" class="easyui-linkbutton" style="width:80px">删除服务</a>
	</div>
	
	<script type="text/javascript">
		function test(a){
			alert(a);
		}
		
		var Url;
		function setUrl(value){
			Url = value;
		}
		
		var UserName;
		function setUserName(value){
			UserName = value;
		}
		
		var Password;
		function setPassword(value){
			Password = value;
		}
	
		var PathName;
		function setPathName(value){
			PathName = value;
		}
		
		var Workspace;
		function setWorkspace(value){
			Workspace = value;
		}
		
		var StoreName;
		function setStoreName(value){
			StoreName = value;
		}
		
		var CoverageName;
		function setCoverageName(value){
			CoverageName = value;
		}
		
		var Format;
		function setFormat(value){
			Format = value;
		}
		
		var IsRepublish;
		function setIsRepublish(value){
			IsRepublish = value;
		}
		
		
      	function publishCoverage(){
      		$(document).ready(function(){
				$.ajax({
					type:"POST",
					url:"../../jaxrs/osmap/json/publish-map",
					dataType: "json",
					contentType: 'application/json',
					data:JSON.stringify({"url":Url,"userName":UserName,"password":Password,"pathName":PathName,
					"workspace":Workspace,"storeName":StoreName,"coverageName":CoverageName,"format":Format,"isRepublish":IsRepublish}),
					success: function(data, textStatus, jqXHR){
			            alert("以下是从服务器返回的info对象:\n\n" + JSON.stringify(data));
			        },
			        error: function(jqXHR, textStatus, errorThrown){
			            alert('error: ' + textStatus);
			        }
				});
			});
      	}
      	
      	function disPublishCoverage(){
      	    $(document).ready(function(){
				$.ajax({
					type:"POST",
					url:"../../jaxrs/osmap/json/disPublish-map",
					dataType: "json",
					contentType: 'application/json',
					data:JSON.stringify({"url":Url,"userName":UserName,"password":Password,
					"workspace":Workspace,"storeName":StoreName,"coverageName":CoverageName}),
					success: function(data, textStatus, jqXHR){
			            alert("以下是从服务器返回的info对象:\n\n" + JSON.stringify(data));
			        },
			        error: function(jqXHR, textStatus, errorThrown){
			            alert('error: ' + textStatus);
			        }
				});
			});
      	}
      	
      	var Url1;
		function setUrl1(value){
			Url1 = value;
		}
		
		var UserName1;
		function setUserName1(value){
			UserName1 = value;
		}
		
		var Password1;
		function setPassword1(value){
			Password1 = value;
		}
	
		var ShpPath1;
		function setShpPath1(value){
			ShpPath1 = value;
		}
		
		var FileName1;
		function setFileName1(value){
			FileName1 = value;
		}
		
		var Workspace1;
		function setWorkspace1(value){
			Workspace1 = value;
		}
		
		var StoreName1;
		function setStoreName1(value){
			StoreName1 = value;
		}
		
		var LayerName1;
		function setLayerName1(value){
			LayerName1 = value;
		}
		
		var Srs1;
		function setSrs1(value){
			Srs1 = value;
		}
		
		var IsRepublish1;
		function setIsRepublish1(value){
			IsRepublish1 = value;
		}
		
		
	    function publishShapefile(){
      		$(document).ready(function(){
				$.ajax({
					type:"POST",
					url:"../../jaxrs/osmap/json/publish-shapefile",
					dataType: "json",
					contentType: 'application/json',
					data:JSON.stringify({"url":Url1,"userName":UserName1,"password":Password1,"shpPath":ShpPath1,"fileName":FileName1,
					"workspace":Workspace1,"storeName":StoreName1,"layerName":LayerName1,"srs":Srs1,"isRepublish":IsRepublish1}),
					success: function(data, textStatus, jqXHR){
			            alert("以下是从服务器返回的info对象:\n\n" + JSON.stringify(data));
			        },
			        error: function(jqXHR, textStatus, errorThrown){
			            alert('error: ' + textStatus);
			        }
				});
			});
      	}
      	
      	function disPublishShapefile(){
      		$(document).ready(function(){
				$.ajax({
					type:"POST",
					url:"../../jaxrs/osmap/json/disPublish-shapefile",
					dataType: "json",
					contentType: 'application/json',
					data:JSON.stringify({"url":Url1,"userName":UserName1,"password":Password1,
					"workspace":Workspace1,"storeName":StoreName1,"layerName":LayerName1}),
					success: function(data, textStatus, jqXHR){
			            alert("以下是从服务器返回的info对象:\n\n" + JSON.stringify(data));
			        },
			        error: function(jqXHR, textStatus, errorThrown){
			            alert('error: ' + textStatus);
			        }
				});
			});
      	}
    </script>

</body>
</html>
