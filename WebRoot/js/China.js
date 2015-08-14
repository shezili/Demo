/**
 * ����ͼ���ܺͻ�ȡʸ�����ԵĹ��ܶ���postһ��xml��geoserverȻ��������صĺ���feature��Ϣ��xml��
 * ����ͼ����ͨ����������THUMBNAIL���������뵺���Ӧ��ͼƬ���ļ���·����ȡ��ͼƬ��
 * ����ȡ���Թ�������Ҫ������������������ʾ����(eastLayers.jsp)�������б�,
 * ��̨��action��thumbnail��getAttributes�����Խ��кϲ������ϲ����Ա�����������ģ��Ķ�����(?)
 */
var isEdit = false;
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
		if(editing === false){
			document.getElementById('nodelist').innerHTML = "Loading... please wait...";
	        var view = map.getView();
	        var viewResolution = view.getResolution();
	        var source = layer3.getSource();
	        var url_1 = source.getGetFeatureInfoUrl(
	                evt.coordinate, viewResolution, view.getProjection(),
	                //[text/plain, application/vnd.ogc.gml, application/vnd.ogc.gml/3.1.1, text/html, application/json]
	                {'INFO_FORMAT': 'application/vnd.ogc.gml'}
	        );
	        setUrl1(url_1);
	      	var url_2 = source.getGetFeatureInfoUrl(
	                evt.coordinate, viewResolution, view.getProjection(),
	                {'INFO_FORMAT': 'text/html'}
	        );
	        if (url_2) {
	                document.getElementById('nodelist').innerHTML = '<iframe seamless src="' + url_2 + '"></iframe>';
	        }
			$.ajax({
		    	 method:'POST',
			   	 url : "thumbnail",
			   	 data : {
					xmlUrl:url_1
			   	 },
			   	 success : thumbnailCallBack,
			   	 dataType : "json"
		   	 });
			overlay.setPosition(evt.coordinate);
			getAttributesValue();
			$('#pictureWindow').window('close');
		}else{
			alert("����Ҫ���͵ĵ������"+evt.coordinate);
			var coord = evt.coordinate.toString();
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
    });
	

	var featureOverlay = new ol.FeatureOverlay({
		style : new ol.style.Style({
			fill : new ol.style.Fill({
				color : 'rgba(255, 255, 255, 0.2)'
			}),
			stroke : new ol.style.Stroke({
				color : '#ffcc33',
				width : 2
			}),
			image : new ol.style.Circle({
				radius : 7,
				fill : new ol.style.Fill({
					color : '#ffcc33'
				})
			})
		})
	});
	featureOverlay.setMap(map);

//	var modify = new ol.interaction.Modify({
//		features : featureOverlay.getFeatures(),
//		// the SHIFT key must be pressed to delete vertices, so
//		// that new vertices can be drawn at the same position
//		// of existing vertices
//		deleteCondition : function(event) {
//			return ol.events.condition.shiftKeyOnly(event)
//					&& ol.events.condition.singleClick(event);
//		},
//	});
//	modify.on('modifyend', send_end_point);
	
//no bird use
//	modify.once('modifystart', send_start_point);
	
//
//	map.addInteraction(modify);

	var draw; // global so we can remove it later
	var modify ;
	function addInteraction() {
		var value = typeSelect.value;
		if (value !== 'None') {
			draw = new ol.interaction.Draw({
				features : featureOverlay.getFeatures(),
				type : /** @type {ol.geom.GeometryType} */
				(value)
			});
			/* map.on('singleclick', function(evt) {alert("start point"+evt.coordinate);}); */
			//draw.on('drawend', confirm1);
			//draw.on('drawstart',confirm1);
			map.addInteraction(draw);
			
			modify = new ol.interaction.Modify({
				features : featureOverlay.getFeatures(),
				// the SHIFT key must be pressed to delete vertices, so
				// that new vertices can be drawn at the same position
				// of existing vertices
				deleteCondition : function(event) {
					return ol.events.condition.shiftKeyOnly(event)
							&& ol.events.condition.singleClick(event);
				},
			});
			map.addInteraction(modify);
		}
	}

//	function send_start_point() {
//		alert("start");
//	}
//	function send_end_point() {
//		alert("end");
//	}

	var typeSelect = document.getElementById('type');

	typeSelect.onchange = function(e) {
		map.removeInteraction(draw);
		map.removeInteraction(modify);
		addInteraction();
		if(isEdit === false){
				alert("����༭ģʽ");
				isEdit = true;
		}else{
				alert("�˳��༭ģʽ");
				isEdit = false;
		}
	};

	addInteraction();
}

function topLeft(msg) {
	$.messager.show({
		title : 'message',
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
/**
 * �������µ�ͼƬurl
 */
var refreashPictureUrl;
function setUrl1(url){
	refreashPictureUrl = url;
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
	// alert(json);
	 if(isEdit === true){
		 selectedFeature =json;
		 if (selectedFeature.islandName === 'error') {
				alert("��δѡ���κε���");
			}else{
				confirm1(selectedFeature.islandName);
		 }
	 }
	 parent.window.showAttributes(json);
}

var editing = false;
function confirm1(name) {
	$.messager.confirm('My Title', 'ȷ���޸ĵ��죺'+name+'?', function(r) {
		if (r) {
			editing = true;
			alert( "���϶��õ㵽��ָ��λ�ú󵥻�ȷ��" );
		}
	});
}

var pictures;
function thumbnailCallBack(json) {
	// alert(JSON.stringify(json));	
	if((isEdit === true)||(json.islandName === 'error')){
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
	alert("�ƶ��ɹ�");
	location.reload() ;
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
	if (json == "ok") {
		$.ajax({
			method : 'POST',
			url : "thumbnail",
			data : {
				xmlUrl : refreashPictureUrl
			},
			success : thumbnailCallBack,
			dataType : "json"
		});

	}
}
