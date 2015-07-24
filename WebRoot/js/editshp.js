$(function() {
	$('#ff').form({
		success : function(data) {
			topLeft(data);
		}
	});
});
var flag = 0;
function showCatalog() {
	if (flag == 0) {
		$('#catalogwindow').window('open');
		flag = 1;
	} else {
		$('#catalogwindow').window('close');
		flag = 0;
	}
}
 function init(){

	  var format = 'image/png';
      var bounds = [602349.5625117262, 3278841.7499960493,
                    637297.437488274, 3356044.75000395];
					

	  var layer1 = new ol.layer.Tile({
		source: new ol.source.TileWMS({
          url: 'http://192.168.1.100:7777/geoserver/test/wms',
          params: {'FORMAT': format, 
                   'VERSION': '1.1.1',
                   tiled: true,
                LAYERS: 'test:CW_BG_TOWN',
                STYLES: '',
          }
        })
      });
     
      var projection = new ol.proj.Projection({
          code: 'EPSG:32649',
          units: 'm',
          axisOrientation: 'neu'
      });
      var map = new ol.Map({
        controls: ol.control.defaults({
          attribution: false
        }),
        target: 'map',
        layers: [
          layer1
        ],
        view: new ol.View({
           projection: projection
        })
      });

      map.getView().fitExtent(bounds, map.getSize());
	  /**
		 * Add a click handler to the map to render the popup.
		 */

	 map.on('singleclick', function(evt) {
        document.getElementById('nodelist').innerHTML = "Loading... please wait...";
        var view = map.getView();
        var viewResolution = view.getResolution();
        var source = layer1.getSource() ;
        //[text/plain, application/vnd.ogc.gml, application/vnd.ogc.gml/3.1.1, text/html, application/json]
//        var url_1 = source.getGetFeatureInfoUrl(
//          evt.coordinate, viewResolution, view.getProjection(),
//          {'INFO_FORMAT': ' application/json'});
		var url_2 = source.getGetFeatureInfoUrl(
          evt.coordinate, viewResolution, view.getProjection(),
          {'INFO_FORMAT': ' text/html'});
        if (url_2) {
          document.getElementById('nodelist').innerHTML = '<iframe seamless src="' + url_2 + '"></iframe>';
        }
//        alert(url_1);
//		$.ajax({
//    	method:'POST',
//	   	 url : "showShpAttribute",
//	   	 data : {
//			xmlUrl:url_1
//	   	 },
//	   	 success : showAttribute,
//	   	 dataType : "json"
//	   	 });	
      });
 }


	function clearForm(){
		$('#ff').form('clear');
	}
	
	 function topLeft(data){
         $.messager.show({
             title:'message',
             msg:'attribute update'+data,
             showType:'show',
             style:{
                 right:'',
                 left:0,
                 top:document.body.scrollTop+document.documentElement.scrollTop,
                 bottom:''
             }
         });
     }
