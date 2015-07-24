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
 * 在eastlayer.jsp显示属性列表
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
	window.frames["childPageEast"].location.reload(); //切换src时刷新子页面，删除留存的属性信息和图层可见性控制列表     附： 子页面刷新父页面parent.location.reload();
	document.getElementById("innerpage").src=src;

}

function clearEastChildPage(){
	window.frames["childPageEast"].clearLayerList();
}
function testp(){
	alert("helloworld");
}