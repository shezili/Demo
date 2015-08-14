
	
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

//var infoUrl;
//function setUrl2(url){
//	infoUrl = url;
//}

 function init(){
    /**
	 * Elements that make up the popup.
	 */
	var container = document.getElementById('popup');

	var closer = document.getElementById('popup-closer');

	/**
	 * Add a click handler to hide the popup.
	 * 
	 * @return {boolean} Don't follow the href.
	 */
	closer.onclick = function() {
		container.style.display = 'none';
		closer.blur();
		return false;
	};
		
		

	/**
	 * Create an overlay to anchor the popup to the map.
	 */
	var overlay = new ol.Overlay({
		element : container
	});

	var format = 'image/png';
	var bounds = [ 602349.5625117262, 3278841.7499960493, 637297.437488274,
			3356044.75000395 ];
					

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
		overlays: [overlay],
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
//	map.on('click', function(evt) {
//		var coordinate = evt.coordinate;
//		// var hdms = ol.coordinate.toStringHDMS(ol.proj.transform(
//		// coordinate, 'EPSG:3857', 'EPSG:4326'));
//		alert('aaaaa'+closepopup);
//		overlay.setPosition(coordinate);
//		// content.innerHTML = '<p>You clicked here:</p><code>' + hdms +
//		// '</code>';
//		container.style.display = 'block';
//
//	});
		
	 map.on('singleclick', function(evt) {
//        document.getElementById('nodelist').innerHTML = "Loading... please wait...";
        var view = map.getView();
        var viewResolution = view.getResolution();
        var source = layer1.getSource() ;
        var url_1 = source.getGetFeatureInfoUrl(
          evt.coordinate, viewResolution, view.getProjection(),
          //[text/plain, application/vnd.ogc.gml, application/vnd.ogc.gml/3.1.1, text/html, application/json]
          {'INFO_FORMAT': 'application/vnd.ogc.gml'});
        setUrl1(url_1);
//        alert(evt.coordinate);
        //���㽫getAttributesValueԪ�ذ�һ���̶���function�������function��
        //�ύajax���������ԣ�����һ�����ԵĶ�������д��eastlistlayer��jsp�У��������д��������ı�ajax�ύ�����xmlUrl����
        //document.getElementById("getAttributesValue").href='getAttributesValue?xmlUrl='+encodeURIComponent(url_1);//encodeURIComponent��������ϵ�url�Ų��ᱻ#�������ַ��ضϡ�
       
        //alert(document.getElementById("getAttributesValue").href);
        //alert(url_1);
//		var url_2 = source.getGetFeatureInfoUrl(
//          evt.coordinate, viewResolution, view.getProjection(),
//          {'INFO_FORMAT': 'text/html'});
//        if (url_2) {
//          document.getElementById('nodelist').innerHTML = '<iframe seamless src="' + url_2 + '"></iframe>';
//          setUrl2(url_2);
//        }
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
		container.style.display = 'block';
		$('#pictureWindow').window('close');
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
 function showAttributes(json){
	 //alert(json.file_name);
	 parent.window.showAttributes(json);
 }
// "<img src='"+ "D:\\th\\8.png"+"' width='128' height='128' />"
 //var pictures;
// var i=1;
 var pictures;
 function thumbnailCallBack(json) {
	if (json.islandName == 'error') {
		document.getElementById('popup').style.display = 'none';
		closer.blur();
	} else {
		pictures = json.pictures;
		document.getElementById('picture').src = json.mainPictureUrl;
		refreash();
	}
}
 

// function next() {
//	if (i < pictures.length) {
//		document.getElementById('picture').src = pictures[i];
//		i++;
//	} else {
//		i = 0;
//		document.getElementById('picture').src = pictures[i];
//		i++;
//	}
//}
	 function openPictureWindow() {
		refreash();
		$('#pictureWindow').window('open');
	}
 
 	function getData() {
		var rows = [];
		for (var i = 0; i < pictures.length; i++) {

			var picture = '<img  src=\"'+pictures[i].url+'\" width=\'128\' height=\'128\' />';
			var time = pictures[i].time;
			var os1 = "<a onclick='setMainPicture(\"" + pictures[i].fullPath + "\")';" + ">" + "����"
			+ "</a>";
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
	function setMainPicture(path){
		$.ajax({
	    	 method:'POST',
		   	 url : "setMainPicture",
		   	 data : {
		   		picturePath:path
		   	 },
		   	 success : setMainPictureCallBack,
		   	 dataType : "json"
	   	 });
	}
	
	//����������ͼ�ɹ����ٴ����̨��������ͼ������ͼ�滻Ϊ�����趨������ͼ���ﵽˢ�µ�Ч��
	function setMainPictureCallBack(json){
		if(json == "ok"){
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
	}
	
