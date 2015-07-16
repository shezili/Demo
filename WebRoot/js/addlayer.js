var format = 'image/png';
var map;
var port = '7777';
var geoserverUrl = 'http://'+'192.168.1.100'+':'+port+'/geoserver/gwc/service/wms';

function init(name) {
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
	map = new ol.Map({
		controls : ol.control.defaults({
			attribution : false
		}),
		target : 'map',
		layers : [ layer ],
		view : new ol.View({
			projection : projection
		})
	});

	map.getView().fitExtent(bounds, map.getSize());
	// parent.window.testindex();
	parent.window.addVisConOfAddLayer(name);

}

/*
 * select the layer through its name from all layers on the map to set its
 * visibility
 */
function selectLayer(layername) {
	map.getLayers().forEach(function(layer, i) {
		if (layer instanceof ol.layer.Layer) {
			var s = layer.getSource().params_.LAYERS;
			if (s == layername) {
				if (layer.getVisible() == true) {
					layer.setVisible(false);
				} else {
					layer.setVisible(true);
				}
			}
		}
	});
}

// function removeLayer(layername){
// map.getLayers().forEach(function(layer, i) {
// if (layer instanceof ol.layer.Layer) {
// var s = layer.getSource().params_.LAYERS;
// if(s==layername)
// map.removeLayer(layer);
// }
// });
// }

/*
 * generate controllers on page for each layers that you added
 * 
 */
// function addVisCon(layerName){
// $("#viscon").append("<input class='visible' type='checkbox' checked='checked'
// value="+layerName+ " onchange='selectLayer(this.value)'>"+layerName+"<br/>");
// }
/**
 * receive the name from the index.jsp
 * 
 * @param name
 */
function setLayerSource_1(name) {
	var layerName = name;
	var flag = true;
	map.getLayers().forEach(function(layer, i) {
		if (layer instanceof ol.layer.Layer) {
			var s = layer.getSource().params_.LAYERS;
			if (s == name)
				flag = false;
		}
	});
	if (flag == true) {
		var gr = new ol.tilegrid.TileGrid({
			origin : [ 166021.4430960765, 0 ],
			resolutions : [ 2609.2074758118965, 1304.6037379059483,
					652.3018689529741, 326.15093447648707, 163.07546723824353,
					81.53773361912177, 40.76886680956088, 20.38443340478044,
					10.19221670239022, 5.09610835119511, 2.548054175597555 ]
		});
		var templayer = new ol.layer.Tile({
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
		map.addLayer(templayer);
		parent.window.addVisConOfAddLayer(layerName);
	} else {
		topLeft(name);
	}
}
function topLeft(name) {
	$.messager.show({
		title : 'Message',
		msg : 'layer:'+'[   '+name+'   ]'+'already exist',
		showType : 'show',
		style : {
			right : '',
			left : 0,
			top : document.body.scrollTop + document.documentElement.scrollTop,
			bottom : ''
		}
	});
}
// function addlayerByName(){
// var layerName = document.getElementById('layername1').value;
// templayer = new ol.layer.Tile({
// preload: Infinity,
// source: new ol.source.TileWMS({
// url: 'http://localhost:7777/geoserver/test/wms',
// params: {'FORMAT': format,
// 'VERSION': '1.1.1',
// tiled: true,
// LAYERS:layerName,
// STYLES: '',
// }
// })
// });
// map.addLayer(templayer);
// parent.window.addVisConOfAddLayer(layerName);
// }

// function removelayer(){
//	
// // var layername = document.getElementById('layername2').value;
// // x=document.getElementById("demo2"); // ÕÒµ½ÔªËØ
// // x.innerHTML=x.innerHTML+"<br/>"+layername;
// alert("started");
// map.removeLayer(layer);
// alert("started");
// // alert("addlayer");
// // var layer2 = new ol.layer.Tile({
// // preload: Infinity,
// // source: new ol.source.TileWMS({
// // url: 'http://localhost:7777/geoserver/test/wms',
// // params: {'FORMAT': format,
// // 'VERSION': '1.1.1',
// // tiled: true,
// // LAYERS:'test:work5',
// // STYLES: '',
// // }
// // })
// // });
// // alert("addlayer2");
// // map.addLayer(layer2);
// // alert("addlayer3");
// }

// function setVisibility(layer,visibleId){
// //var x=document.getElementById("test");
// //x.innerHTML=x.innerHTML+"<a
// onclick='selectLayer(this.innerHTML)'>"+layerName+"</a>"+"<br/>";
// var visible = new ol.dom.Input(document.getElementById(visibleId));
// visible.bindTo('checked', layer, 'visible');
// }
//
//
// function addRecord(layerName,visibleId){
// var x=document.getElementById("test");
// x.innerHTML=x.innerHTML+"<input id="+visibleId+" class='visible'"+"
// type='checkbox'/>"+layerName+"<br/>";
// }
