/* 
 * generate controllers on page for each layers that you added
 * for the addlayer.jsp
 */
function addVisConOfAddLayer(layerName){
//	$("#layerList").append("<input class='visible' type='checkbox' checked='checked' " +
//			"value="+layerName+ " onchange="+"window.frames["+"'childPage'"+"].selectLayer(this.value)"+" />"+layerName
//			+"<br/>");

	window.frames["childPageEast"].addVisConOfAddLayer(layerName);
}
/**
 * ��eastlayer.jsp��ʾ�����б�
 */
function showAttributes(feature){
	window.frames["childPageEast"].showAttributes(feature);
}
/**
 * accept layer name from catalog.jsp 
 * then send the name to the current child page
 */
function setLayerSourceOfChild_1(name){
	//alert(name+"from catalog");
	window.frames["childPage"].setLayerSource_1(name);
}
function setLayerSourceOfChild_2(name){
	//alert(name+"from catalog");
	window.frames["childPage"].setLayerSource_2(name);
}

/**
 * create a progress bar
 */
function showProgress(title, desc, time) {
    var win = $.messager.progress({
        title: title,
        msg: desc
    });
    setTimeout(function () {
        $.messager.progress('close');
    }, time)
}
/**
 * change innerPage in the frame
 * @param src
 */
function changesrc(src){
	//clearEastChildPage();
	window.frames["childPageEast"].location.reload(); //�л�srcʱˢ����ҳ�棬ɾ�������������Ϣ��ͼ��ɼ��Կ����б�     ���� ��ҳ��ˢ�¸�ҳ��parent.location.reload();
	document.getElementById("innerpage").src=src;
}
/**
 * ʹ��ѡ������ز�ͬ���ݵ�ҳ�棬���������changesrc����
 * 
 * ��ӷ���ǰ�ж���name�Ƿ��Ѿ�������tab�����У��������ټ������������ظ�name��tabҳ����ɻ���
 */
var tabArray;
function addPanel(title, src, id, name){
	//tabArray.push(name);
	$('#dis_tabs').tabs('add',{
		title: title,
		content: '<iframe id="'+ id +'" src="'+ src +'" name="'+name+'" scrolling="no" height = "99%" width = "99%"></iframe>',
		closable: true
	});
}
/**
 * �Ƴ�ѡ�
 */
function removePanel(){
	var tab = $('#dis_tabs').tabs('getSelected');
	if (tab){
		var index = $('#dis_tabs').tabs('getTabIndex', tab);
		$('#dis_tabs').tabs('close', index);
	}
	window.frames["childPageEast"].location.reload();
}

/**
 * ���layout east����ʾ������
 */
function clearEastChildPage(){
	window.frames["childPageEast"].clearLayerList();
}

/**
 * ��λͼ�㣬��   ����
 * @param name
 * @param bounds
 */
function addCovergae( name , bounds){
	window.frames["cdname"].addCovergae( name , bounds);
	$('#dis_tabs').tabs('select', 'bottom');
}

function testp(){
	alert("helloworld");
}