/**
 * 缩略图功能和获取矢量属性的功能都是post一个xml到geoserver然后解析返回的含有feature信息的xml，
 * 缩略图功能通过解析出的THUMBNAIL属性生成与岛屿对应的图片的文件夹路径以取到图片，
 * 而获取属性功能则需要解析出所有属性以显示在右(eastLayers.jsp)的属性列表,
 * 后台的action（thumbnail和getAttributes）可以进行合并，不合并可以保持两个功能模块的独立性(?)
 */

var EditStatus = "no";
var editing = false;
var coord ;
$(document).ready(function() {
	init();
});

function init() {
	var container = document.getElementById('popup');
	var closer = document.getElementById('popup-closer');
	closer.onclick = function() {
		container.style.display = 'none';
		//closer.blur();
		return false;
	};
	var overlay = new ol.Overlay({
		element : container
	});

	var format = 'image/png';
	var mousePositionControl = new ol.control.MousePosition({
		className : 'custom-mouse-position',
		target : document.getElementById('location'),
		coordinateFormat : ol.coordinate.createStringXY(5),
		undefinedHTML : '&nbsp;'
	});	
	var layer1 = new ol.layer.Tile({
		source : new ol.source.TileWMS({
			url : 'http://192.168.1.100:7777/geoserver/gwc/service/wms',
			params : {
				'FORMAT' : format,
				'VERSION' : '1.1.1',
				'SRS' : 'EPSG:4326',
				'tiled' : true,
				'LAYERS' : 'China:China',
				 STYLES : '',
				 serverType:"geoserver"
			},
		})
	});
	
	var layer2 = new ol.layer.Tile({
		source : new ol.source.TileWMS({
			url : 'http://192.168.1.100:7777/geoserver/gwc/service/wms',
			params : {
				'FORMAT' : format,
				'VERSION' : '1.1.1',
				tiled : true,
				LAYERS : 'China:HdaoMian',
				STYLES : '',
				serverType:"geoserver"
			},
		})	
	});
	
	var layer3 = new ol.layer.Tile({
		 visible: false,
		 source: new ol.source.TileWMS({
	          url: 'http://192.168.1.100:7777/geoserver/China/wms',
	          params: {'FORMAT': format, 
	                   'VERSION': '1.1.1',
	                   tiled: true,
	                LAYERS: 'China:HaidaoDian',
	                STYLES: '',
	          }
	        })
	});

	var map = new ol.Map({
		controls : ol.control.defaults({
			attribution : false
		}).extend([ mousePositionControl ]),
		overlays: [overlay],
		target : 'map',
		layers : [layer1,layer2,vector,layer3],
		view: new ol.View({
			projection: 'EPSG:4326',
			center:[113.2,21.9],
			zoom:10
		})
	});
	
	map.on('singleclick', function(evt) {
		coord = evt.coordinate.toString();
		if(editing === false){
	        var view = map.getView();
	        var viewResolution = view.getResolution();
	        var source = layer3.getSource();
	        var url_1 = source.getGetFeatureInfoUrl(
	                evt.coordinate, viewResolution, view.getProjection(),
	                //[text/plain, application/vnd.ogc.gml, application/vnd.ogc.gml/3.1.1, text/html, application/json]
	                {'INFO_FORMAT': 'application/vnd.ogc.gml'}
	        );
	        setUrl1(url_1);
			overlay.setPosition(evt.coordinate);
			getThumbnail();
			getAttributesValue();
			$('#pictureWindow').window('close');
		}else{
			//alert("马上要发送的点的坐标"+evt.coordinate);
			if(EditStatus === "UPDATE"){
				updatePoint(coord);
			}
//			switch(EditStatus)
//			{
//			case "DELETE":
//				deletePoint(coord);
//			    break;
//			case "ADD":
//				addPoint(coord);
//			    break;
//			case "UPDATE":
//				updatePoint(coord);
//				break;
//			default:
//			    alert("没有这种编辑状态");
//			}
		}   
    });
}
/**
 * 获取属性
 */
function getAttributesValue(){
	 $.ajax({
   	 method:'POST',
	   	 url : "getAttributesValue",
	   	 data : {
			xmlUrl:refreashPictureUrl
	   	 },
	   	 success : showAttributes,
	   	 dataType : "json"
  	 });
}


/**
 * 在右边栏显示属性
 */
var selectedFeature;
function showAttributes(json){
	selectedFeature = json;
	switch(EditStatus)
	{
		case "DELETE":
			 if (selectedFeature.fid === 'error') {
				 alertMessager("提示","并未选中任何岛屿","info");
				}else{
					if(selectedFeature.islandId == "error"){
						alertMessager("警告","请先填写该岛屿ID","warning");
						EditStatus = "no";
					}else{
						confirmDeleteMessager(selectedFeature.islandName);
					}
			 }
		    break;
		case "ADD":
			if (selectedFeature.fid !== 'error') {
				//alert("并未选中任何岛屿");
				alertMessager("提示","此处已有其它岛屿","info");
			}else{
				editing = true;
				confirmAddMessager();
		 }
		    break;
		case "UPDATE":
			 if (selectedFeature.fid === 'error') {
					//alert("并未选中任何岛屿");
				 alertMessager("提示","并未选中任何岛屿","info");
				}else{
				 confirmIslandMessager(selectedFeature.islandName);
			 }
			break;
		case "no":
			break;
		default:
		    alert("没有这种编辑状态");
	}
	 parent.window.showAttributes(json);
}

function menuHandler(item){
	EditStatus = "no";
	editing = false;
	if(item.name !== "exit"){
		EditStatus = item.name;
	}
}
$(function(){
	$(document).bind('contextmenu',function(e){
		e.preventDefault();
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});
	});
});
function updatePoint(coord){
	$.ajax({
		method : 'POST',
		url : "updateShpGeom",
		data : {
			layerName : selectedFeature.layerName,
			fid : selectedFeature.fid,
			coordinate : coord,
		},
		success : showEditResult,
		dataType : "json"
	});
}

function confirmDeleteMessager(name){
	$.messager.confirm('确认删除', '确认删除海岛：'+name+'?', function(r){
		if (r){
			deletePoint();
		}
	});
}
function deletePoint(){
	$.ajax({
		method : 'POST',
		url : "delPoint",
		data : {
			layerName : selectedFeature.layerName,
			attribute : "ISLANDID",
			value : selectedFeature.islandId,
		},
		success : showEditResult,
		dataType : "json"
	});
}
function confirmAddMessager(){
	$.messager.confirm('确认添加', '确认添加海岛?', function(r){
		if (r){
			addPoint(coord);
		}
	});
}
function addPoint(coord){
	$.ajax({
		method : 'POST',
		url : "addPoint",
		data : {
			layerName : "China:HaidaoDian",
			coordinate : coord,
		},
		success : showEditResult,
		dataType : "json"
	});
}

/**
 * 设置最新的图片url
 */
var refreashPictureUrl;
function setUrl1(url){
	refreashPictureUrl = url;
}

function confirmIslandMessager(name) {
	$.messager.confirm('确认岛屿', '确定修改岛屿：'+name+'?', function(r) {
		if (r) {
			editing = true;
			//alert( "请拖动该点到达指定位置后单击确认" );
			alertMessager("提示","单击确认岛屿最终位置","info");
		}
	});
}

var pictures;
function getThumbnail(){
	$.ajax({
   	 method:'POST',
	   	 url : "thumbnail",
	   	 data : {
			xmlUrl:refreashPictureUrl
	   	 },
	   	 success : thumbnailCallBack,
	   	 dataType : "json"
  	 });
}
function thumbnailCallBack(json) {
	// alert(JSON.stringify(json));	
	if((EditStatus !== "no")||(json.islandName === 'error')){
		$('#popup,#picture,#popup-closer,#popup-more').css('display','none');
		//closer.blur();
	}else{
//		if (json.islandName === 'error') {
//			$('#popup,#picture,#popup-closer,#popup-more').css('display','none');//利用了jquery选择器
//			//closer.blur();
//		} else {
				pictures = json.pictures;
				document.getElementById('picture').src = json.mainPictureUrl;
				$('#popup,#picture,#popup-closer,#popup-more').css('display','block');
				refreash();//重新加载“更多”里的内容，与当前岛屿保持一致
//		}
	}
}
function showEditResult(json){
	
	location.reload() ;
	//alertMessager("提示","编辑成功","info");
}



function openPictureWindow() {
	refreash();
	$('#pictureWindow').window('open');
}

function getData() {
	var rows = [];
	for (var i = 0; i < pictures.length; i++) {

		var picture = '<img  src=\"' + pictures[i].url
				+ '\" width=\'128\' height=\'128\' />';
		var time = pictures[i].time;
		var os1 = "<a onclick='setMainPicture(\"" + pictures[i].fullPath
				+ "\")';" + ">" + "设置" + "</a>";
		rows.push({
			picture : picture,
			time : time,
			os1 : os1,
		});
	}
	return rows;
}

function pagerFilter(data) {
	if (typeof data.length == 'number' && typeof data.splice == 'function') { // is
		// array
		data = {
			total : data.length,
			rows : data
		};
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

function refreash() {
	$('#dg').datagrid({
		loadFilter : pagerFilter
	}).datagrid('loadData', getData());
};

//将该行的图设为主缩略图
function setMainPicture(path) {
	$.ajax({
		method : 'POST',
		url : "setMainPicture",
		data : {
			picturePath : path
		},
		success : setMainPictureCallBack,
		dataType : "json"
	});
}

//设置主缩略图成功后再次向后台请求缩略图，缩略图替换为最新设定的缩略图，达到刷新的效果
function setMainPictureCallBack(json) {
	if (json === "ok") {
		getThumbnail();
	}
}

function slideMessager(title,msg){
    $.messager.show({
        title:title,
        msg:msg,
        timeout:2000,
        showType:'slide'
    });
}
function alertMessager(title,msg,type){
    $.messager.alert(title,msg,type);
}

