var vectorSource = new ol.source.ServerVector(
		{
			format : new ol.format.GeoJSON(),
			loader : function(extent, resolution, projection) {
				var url = 'http://192.168.1.100:7777/geoserver/wfs?service=WFS&'
						+ 'version=1.1.0&request=GetFeature&typename=China:HaidaoDian&'
						+ 'outputFormat=text/javascript&format_options=callback:loadFeatures'
						+ '&srsname=EPSG:4326&bbox='
						+ extent.join(',')
						+ ',EPSG:4326';
				$.ajax({
					url : url,
					dataType : 'jsonp'
				});
			},
			projection : 'EPSG:4326'
		});

var loadFeatures = function(response) {
	vectorSource.addFeatures(vectorSource.readFeatures(response));
};

var vector = new ol.layer.Vector({
	source : vectorSource,
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
