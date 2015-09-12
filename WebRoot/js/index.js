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
/**
 * 使用选项卡来加载不同内容的页面，废弃上面的changesrc方法
 * 
 * 添加方法前判断其name是否已经存在于tab数组中，不存在再加入以免生成重复name的tab页面造成混乱
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
 * 移除选项卡
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
 * 清除layout east内显示的数据
 */
function clearEastChildPage(){
	window.frames["childPageEast"].clearLayerList();
}

/**
 * 定位图层，被   调用
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