
function init() {
	var format = 'image/png';
	var bounds = [ 113.02701693240054,21.869805318234405,113.35659208698961,22.037922412991932 ];
	var gr = new ol.tilegrid.TileGrid({
		origin : [ -180.0, -90.0 ],
		resolutions : [ 0.703125, 0.3515625, 0.17578125, 0.087890625,
				0.0439453125, 0.02197265625, 0.010986328125, 0.0054931640625,
				0.00274658203125, 0.001373291015625, 6.866455078125E-4,
				3.4332275390625E-4, 1.71661376953125E-4, 8.58306884765625E-5,
				4.291534423828125E-5, 2.1457672119140625E-5,
				1.0728836059570312E-5, 5.364418029785156E-6,
				2.682209014892578E-6, 1.341104507446289E-6,
				6.705522537231445E-7, 3.3527612686157227E-7 ]
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
				 STYLES : 'China'
			},
			tileGrid : gr,
			serverType: 'geoserver'
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
				STYLES : 'HaidaoMian1'
			},
			tileGrid : gr,
			serverType: 'geoserver'
		})	
	});
	
	var layer3 = new ol.layer.Tile({
		source : new ol.source.TileWMS({
			url : 'http://192.168.1.100:7777/geoserver/gwc/service/wms',
			params : {
				'FORMAT' : format,
				'VERSION' : '1.1.1',
				tiled : true,
				LAYERS : 'China:HaidaoDian',
				STYLES : 'HaidaoDian1'
			},
			tileGrid : gr,
			serverType: 'geoserver'
		})	
	});

	var projection = new ol.proj.Projection({
		code : 'EPSG:4326',
		units : 'm',
		axisOrientation : 'neu'
	});
	var map = new ol.Map({
		controls : ol.control.defaults({
			attribution : false
		}),
		target : 'map',
		layers : [ layer1,layer2,layer3 ],
		view : new ol.View({
			projection : projection
		})
	});

	map.getView().fitExtent(bounds, map.getSize());
}
