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

function clearEastChildPage(){
	window.frames["childPageEast"].clearLayerList();
}
function testp(){
	alert("helloworld");
}