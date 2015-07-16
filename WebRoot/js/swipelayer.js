var map;
var layer1;
var layer2;
var layerName1;
var layderName2;
var format = 'image/png';
var port = '7777';
var geoserverUrl = 'http://'+window.location.hostname+':'+port+'/geoserver/gwc/service/wms';

function init(name1, name2) {
	var bounds = [ 220485, 3245385, 467115, 3462015 ];
	layerName1 = name1;
	layerName2 = name2;
	var gr = new ol.tilegrid.TileGrid({
		origin : [ 166021.4430960765, 0 ],
		resolutions : [ 2609.2074758118965, 1304.6037379059483,
				652.3018689529741, 326.15093447648707, 163.07546723824353,
				81.53773361912177, 40.76886680956088, 20.38443340478044,
				10.19221670239022, 5.09610835119511, 2.548054175597555 ]
	});
	layer1 = new ol.layer.Tile({
		// extent:[220485,3245385,467115,3462015],
		source : new ol.source.TileWMS({
			url : geoserverUrl,
			params : {
				'FORMAT' : format,
				'VERSION' : '1.1.1',
				'SRS' : 'EPSG:32650',
				'tiled' : true,
				'LAYERS' : name1,
				STYLES : ''
			},
			tileGrid : gr,
			serverType : 'geoserver'
		})
	});
	layer2 = new ol.layer.Tile({
		// extent:[220485,3245385,467115,3462015],
		source : new ol.source.TileWMS({
			url : geoserverUrl,
			params : {
				'FORMAT' : format,
				'VERSION' : '1.1.1',
				'SRS' : 'EPSG:32650',
				'tiled' : true,
				'LAYERS' : name2,
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
	map = new ol.Map({
		controls : ol.control.defaults({
			attribution : false
		}),
		target : 'map',
		layers : [ layer1, layer2 ],
		view : new ol.View({
			projection : projection,
		})
	});

	map.getView().fitExtent(bounds, map.getSize());
	writeLayerName();

	var swipe = document.getElementById('swipe');

	layer2.on('precompose', function(event) {
		var ctx = event.context;
		var width = ctx.canvas.width * (swipe.value / 100);

		ctx.save();
		ctx.beginPath();
		ctx.rect(width, 0, ctx.canvas.width - width, ctx.canvas.height);
		ctx.clip();
	});

	layer2.on('postcompose', function(event) {
		var ctx = event.context;
		ctx.restore();
	});

	// swipe.addEventListener('input', function() {
	// map.render();
	// }, false);

}

function refreshMap() {
	map.render();
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
	layer1.setSource(source);
	layerName1 = name;
	writeLayerName();
}
function setLayerSource_2(name) {
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
	layer2.setSource(source);
	layerName2 = name;
	writeLayerName();
}

function writeLayerName() {
	var l = document.getElementById("layerName");
	l.innerHTML = "" + "bottom:" + layerName1 + "<br/>" + "top:" + layerName2;
}
