$(document).ready(function() {
	init();
});
var format = 'image/png';
var map;
function init() {

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

	map = new ol.Map({
		controls : ol.control.defaults({
			attribution : false
		}).extend([ mousePositionControl ]),
		target : 'map',
		layers : [layer1,layer2,vector,layer3],
		view: new ol.View({
			projection: 'EPSG:4326',
			center:[113.2,21.9],
			zoom:10
		})
	});
}

/**
 * 判断图层是否已经存在，不存在才加载
 * 所有图层类型均为ol.layer.Tile
 * @param name		图层名称
 * @param bounds		显示边界
 */
function addCovergae( name ,bounds){
	var layerName = name;
	var flag = true;
	map.getLayers().forEach(function(layer, i) {
		if (layer instanceof ol.layer.Tile) {
			var s = layer.getSource().params_.LAYERS;
			if (s === name)
				flag = false;
		}
	});
	if (flag === true) {
		var templayer = new ol.layer.Tile({
			source : new ol.source.TileWMS({
				url : 'http://192.168.1.100:7777/geoserver/gwc/service/wms',
				params : {
					'FORMAT' : format,
					'VERSION' : '1.1.1',
					tiled : true,
					LAYERS : layerName,
					STYLES : '',
					serverType:"geoserver"
				},
			})	
		});
		map.addLayer(templayer);
		parent.window.addVisConOfAddLayer(layerName);
	} 
	map.getView().fitExtent(bounds, map.getSize()); 
}

/*
 * select the layer through its name from all layers on the map to set its
 * visibility
 */
function selectLayer(layername) {
	map.getLayers().forEach(function(layer, i) {
		if (layer instanceof ol.layer.Tile) {
			var s = layer.getSource().params_.LAYERS;
			if (s === layername) {
				if (layer.getVisible() === true) {
					layer.setVisible(false);
				} else {
					layer.setVisible(true);
				}
			}
		}
	});
}