/**
 * ����ͼ���ܺͻ�ȡʸ�����ԵĹ��ܶ���postһ��xml��geoserverȻ��������صĺ���feature��Ϣ��xml��
 * ����ͼ����ͨ����������THUMBNAIL���������뵺���Ӧ��ͼƬ���ļ���·����ȡ��ͼƬ��
 * ����ȡ���Թ�������Ҫ������������������ʾ����(eastLayers.jsp)�������б�,
 * ��̨��action��thumbnail��getAttributes�����Խ��кϲ������ϲ����Ա�����������ģ��Ķ�����(?)
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
			//alert("����Ҫ���͵ĵ������"+evt.coordinate);
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
//			    alert("û�����ֱ༭״̬");
//			}
		}   
    });
}
/**
 * ��ȡ����
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
 * ���ұ�����ʾ����
 */
var selectedFeature;
function showAttributes(json){
	selectedFeature = json;
	switch(EditStatus)
	{
		case "DELETE":
			 if (selectedFeature.fid === 'error') {
				 alertMessager("��ʾ","��δѡ���κε���","info");
				}else{
					if(selectedFeature.islandId == "error"){
						alertMessager("����","������д�õ���ID","warning");
						EditStatus = "no";
					}else{
						confirmDeleteMessager(selectedFeature.islandName);
					}
			 }
		    break;
		case "ADD":
			if (selectedFeature.fid !== 'error') {
				//alert("��δѡ���κε���");
				alertMessager("��ʾ","�˴�������������","info");
			}else{
				editing = true;
				confirmAddMessager();
		 }
		    break;
		case "UPDATE":
			 if (selectedFeature.fid === 'error') {
					//alert("��δѡ���κε���");
				 alertMessager("��ʾ","��δѡ���κε���","info");
				}else{
				 confirmIslandMessager(selectedFeature.islandName);
			 }
			break;
		case "no":
			break;
		default:
		    alert("û�����ֱ༭״̬");
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
	$.messager.confirm('ȷ��ɾ��', 'ȷ��ɾ��������'+name+'?', function(r){
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
	$.messager.confirm('ȷ�����', 'ȷ����Ӻ���?', function(r){
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
 * �������µ�ͼƬurl
 */
var refreashPictureUrl;
function setUrl1(url){
	refreashPictureUrl = url;
}

function confirmIslandMessager(name) {
	$.messager.confirm('ȷ�ϵ���', 'ȷ���޸ĵ��죺'+name+'?', function(r) {
		if (r) {
			editing = true;
			//alert( "���϶��õ㵽��ָ��λ�ú󵥻�ȷ��" );
			alertMessager("��ʾ","����ȷ�ϵ�������λ��","info");
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
//			$('#popup,#picture,#popup-closer,#popup-more').css('display','none');//������jqueryѡ����
//			//closer.blur();
//		} else {
				pictures = json.pictures;
				document.getElementById('picture').src = json.mainPictureUrl;
				$('#popup,#picture,#popup-closer,#popup-more').css('display','block');
				refreash();//���¼��ء����ࡱ������ݣ��뵱ǰ���챣��һ��
//		}
	}
}
function showEditResult(json){
	
	location.reload() ;
	//alertMessager("��ʾ","�༭�ɹ�","info");
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
				+ "\")';" + ">" + "����" + "</a>";
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

//�����е�ͼ��Ϊ������ͼ
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

//����������ͼ�ɹ����ٴ����̨��������ͼ������ͼ�滻Ϊ�����趨������ͼ���ﵽˢ�µ�Ч��
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

