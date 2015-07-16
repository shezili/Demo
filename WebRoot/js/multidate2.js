var map1;
var map2;
var layer1;
var layer2;
var layerName1;
var layerName2;
var format = 'image/png';
var bounds = [ 220485, 3245385, 467115, 3462015 ];
var projection = new ol.proj.Projection({
	code : 'EPSG:32650',
	units : 'm',
	axisOrientation : 'neu'
});

var port = '7777';
var geoserverUrl = 'http://'+window.location.hostname+':'+port+'/geoserver/gwc/service/wms';

function init1(name) {
	var gr = new ol.tilegrid.TileGrid({
		origin : [ 166021.4430960765, 0 ],
		resolutions : [ 2609.2074758118965, 1304.6037379059483,
				652.3018689529741, 326.15093447648707, 163.07546723824353,
				81.53773361912177, 40.76886680956088, 20.38443340478044,
				10.19221670239022, 5.09610835119511, 2.548054175597555 ]
	});
	layerName1 = name;
	layer1 = new ol.layer.Tile({
		// extent:[220485,3245385,467115,3462015],
		source : new ol.source.TileWMS({
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
		})
	});

	map1 = new ol.Map({
		controls : ol.control.defaults({
			attribution : false
		}),
		target : 'map1',
		layers : [ layer1 ],
		view : new ol.View({
			projection : projection,
		})
	});

	map1.getView().fitExtent(bounds, map1.getSize());
}
function init2(name) {
	var gr = new ol.tilegrid.TileGrid({
		origin : [ 166021.4430960765, 0 ],
		resolutions : [ 2609.2074758118965, 1304.6037379059483,
				652.3018689529741, 326.15093447648707, 163.07546723824353,
				81.53773361912177, 40.76886680956088, 20.38443340478044,
				10.19221670239022, 5.09610835119511, 2.548054175597555 ]
	});
	layerName2 = name;
	layer2 = new ol.layer.Tile({
		// extent:[220485,3245385,467115,3462015],
		source : new ol.source.TileWMS({
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
		})
	});

	map2 = new ol.Map({
		controls : ol.control.defaults({
			attribution : false
		}),
		target : 'map2',
		layers : [ layer2 ],
		view : new ol.View({
			projection : projection,
		})
	});
	writeLayerName();
	map2.getView().fitExtent(bounds, map2.getSize());
	map2.bindTo('view', map1);
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

// function checkmap1Slider(value){
// var a = value;
// var layer12 = "work" + a;
// var source = new ol.source.TileWMS({
// url: 'http://localhost:7777/geoserver/test/wms',
// params: {'FORMAT': format,
// 'VERSION': '1.1.1',
// tiled: true,
// LAYERS: "test:"+layer12,
// STYLES: '',
// }
// });
//
// layer1.setSource(source);
// }
//
// function checkmap2Slider(value){
// var a = value;
// var layer12 = "work" + a;
// var source = new ol.source.TileWMS({
// url: 'http://localhost:7777/geoserver/test/wms',
// params: {'FORMAT': format,
// 'VERSION': '1.1.1',
// tiled: true,
// LAYERS: "test:"+layer12,
// STYLES: '',
// }
// });
//
// layer2.setSource(source);
// }
function writeLayerName() {
	var l = document.getElementById("layerName");
	l.innerHTML = "" + "left:" + layerName1 + "\t" + "right:" + layerName2;
}

var startDate;
var endDate;
var records;
var date1 = "0000-00-00";
var date2 = "0000-00-00";
var name1 = "null";
var name2 = "null";
function SelectStartDate(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	startDate = standardDate(y, m, d);
}
function SelectEndDate(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	endDate = standardDate(y, m, d);
}

function standardDate(y, m, d) {
	var date;
	if ((m < 10) && (d < 10)) {
		date = y + "-0" + m + "-0" + d;
	} else if ((m < 10) && (d >= 10)) {
		date = y + "-0" + m + "-" + d;
	} else if ((m >= 10) && (d < 10)) {
		date = y + "-" + m + "-0" + d;
	} else {
		date = y + "-" + m + "-" + d;
	}
	return date;
}
function getDate() {
	// var date=document.getElementById("date");
	// date.innerHTML = startDate + endDate + (startDate<endDate);
	if (startDate < endDate) {
		$.ajax({
			method : 'POST',
			url : "dateInterval",
			data : {
				startTime : startDate,
				endTime : endDate
			},
			success : dateIntervalCallBack,
			dataType : "json"
		});
	} else {
		warning();
	}
}
function warning() {
	$.messager.alert('warning', 'Input is not legal', 'warning');
}
function topLeft() {
	$.messager.show({
		title : 'Message',
		msg : 'update success',
		showType : 'show',
		style : {
			right : '',
			left : 0,
			top : document.body.scrollTop + document.documentElement.scrollTop,
			bottom : ''
		}
	});
}
function dateIntervalCallBack(jsons) {
	// for(var i=0;i<jsons.length;i++){
	// $("#date").append(jsons[i].id+"\t"+jsons[i].name+"\t"+jsons[i].dateStr+"<br/>");
	// }
	records = jsons;
	topLeft();
}

function showDate1(value) {
	var interval = 100 / records.length;
	var x = value / interval;
	x = parseInt(x);
	var tempDate = records[x].dateStr;
	var tempName = records[x].name;
	if (tempDate != date1) {
		date1 = tempDate;
		name1 = tempName;
		setLayerSource_1(name1);
	}
}
function showDate2(value) {
	var interval = 100 / records.length;
	var x = value / interval;
	x = parseInt(x);
	var tempDate = records[x].dateStr;
	var tempName = records[x].name;
	if (tempDate != date2) {
		date2 = tempDate;
		name2 = tempName;
		setLayerSource_2(name2);
	}
}