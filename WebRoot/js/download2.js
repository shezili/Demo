//32650坐标系的下载

var draw; // global so we can remove it later
var layer;
var layerName;
var bd;
var bbox;
var format = 'image/png';
var port = '7777';
var geoserverUrl = 'http://'+window.location.hostname+':'+port+'/geoserver/gwc/service/wms';  //当程序服务器端和geoserver在一台电脑上才行
/**
 * build the map
 * 
 * @param name
 *            the name of the layer that loaded
 */
function init(name) {
	layerName = name;
	var mousePositionControl = new ol.control.MousePosition({
		className : 'custom-mouse-position',
		target : document.getElementById('location'),
		coordinateFormat : ol.coordinate.createStringXY(5),
		undefinedHTML : '&nbsp;'
	});
	var bounds = [ 220485, 3245385, 467115, 3462015 ];
	var gr = new ol.tilegrid.TileGrid({
		origin : [ 166021.4430960765, 0 ],
		resolutions : [ 2609.2074758118965, 1304.6037379059483,
				652.3018689529741, 326.15093447648707, 163.07546723824353,
				81.53773361912177, 40.76886680956088, 20.38443340478044,
				10.19221670239022, 5.09610835119511, 2.548054175597555 ]
	});
	layer = new ol.layer.Tile({
		// extent:[220485,3245385,467115,3462015],
		source : new ol.source.TileWMS({
		    url : geoserverUrl,
			//url : 'geoserver/gwc/service/wms',
			params : {
				'FORMAT' : format,
				'VERSION' : '1.1.1',
				'SRS' : 'EPSG:32650',
				'tiled' : true,
				'LAYERS' : name,
				STYLES : ''
			},
			tileGrid : gr,
			serverType : 'geoserver'
		})
	});
	var projection = new ol.proj.Projection({
		code : 'EPSG:32650',
		units : 'm',
		axisOrientation : 'neu'
	});
	var source = new ol.source.Vector();

	var vector = new ol.layer.Vector({
		source : source,
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
	var map = new ol.Map({
		controls : ol.control.defaults({
			attribution : false
		}).extend([ mousePositionControl ]),
		target : 'map',
		layers : [ layer, vector ],
		view : new ol.View({
			projection : projection,
		})
	});

	writeLayerName();

	map.getView().fitExtent(bounds, map.getSize());

	var typeSelect = document.getElementById('type');
	function addInteraction() {
		var value = typeSelect.value;
		if (value !== 'None') {
			draw = new ol.interaction.Draw({
				source : source,
				type : /** @type {ol.geom.GeometryType} */
				(value)
			});

			draw.on('drawend', sendBounds);

			map.addInteraction(draw);
		}
	}

	/**
	 * Let user change the geometry type.
	 * 
	 * @param {Event}
	 *            e Change event.
	 */
	typeSelect.onchange = function(e) {
		// alert("remove");
		// alert(bbox);
		map.removeInteraction(draw);
		addInteraction();
	};

	addInteraction();

}
/**
 * send data to the controller(MVC)
 * 
 * @param featureObj
 *            the feature have been drawed
 */
function sendBounds(featureObj) {
	// alert(featureObj.feature.getGeometry().getExtent());
	// sendBoundary(featureObj.feature.getGeometry().getExtent());
	var bd = featureObj.feature.getGeometry().getExtent();
	// alert(layerName+"第一次:"+bd);
	// var beg = reTransProj([ bd[0], bd[1] ]);
	// var end = reTransProj([ bd[2], bd[3] ]);
	bbox = bd[0] + " " + bd[1] + " " + bd[2] + " " + bd[3];
	// alert(layerName+":"+bbox);
	parent.window.showProgress('waiting...', 'data loading...', 3000);
	$.ajax({
		method : 'POST',
		url : "download",
		data : {
			cropBbox : bbox,
			fullName : layerName
		},
		success : downloadCallBack,
		dataType : "json"
	});
	
}
/**
 * receive the name from the index.jsp
 * 
 * @param name
 */
function setLayerSource_1(name) {
	var gr = new ol.tilegrid.TileGrid({
		origin : [ 166021.4430960765, 0 ],
		resolutions : [ 2609.2074758118965, 1304.6037379059483,
				652.3018689529741, 326.15093447648707, 163.07546723824353,
				81.53773361912177, 40.76886680956088, 20.38443340478044,
				10.19221670239022, 5.09610835119511, 2.548054175597555 ]
	});
	var source = new ol.source.TileWMS({
		url : geoserverUrl,
		params : {
			'FORMAT' : format,
			'VERSION' : '1.1.1',
			'SRS' : 'EPSG:32650',
			'tiled' : true,
			'LAYERS' : name,
			STYLES : ''
		},
		tileGrid : gr,
		serverType : 'geoserver'
	});
	layer.setSource(source);
	layerName = name;
	writeLayerName();
}
/**
 * open a page for downloading
 * 
 * @param json
 */
function downloadCallBack(json) {
	if (json == 'failed') {
		errorMessge();
	} else {
		downloadMessage(json);
	}

}
function errorMessge(json) {
	$.messager.alert('Error', 'Beyond the border!', 'error');
}
function downloadMessage(json) {
	$.messager.confirm('download', 'download the data?', function(r) {
		if (r) {
			window.open(json);
		}
	});
}
function writeLayerName() {
	var l = document.getElementById("layerName");
	l.innerHTML = "current layer:" + layerName;
}