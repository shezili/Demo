function changeTotext(obj) {
	var tdValue = obj.innerText;
	obj.innerText = "";
	var txt = document.createElement("textarea");
	txt.type = "text";
	txt.value = tdValue;
	txt.id = "_text_000000000_";
	// txt.setAttribute("className", "text");rows
	var rows = tdValue.length / 20;
	if (rows < 1) {
		rows = 1;
	} else {
		rows = parseInt(rows) + 1;
	}
	var cols = 70;
	if (tdValue.length < cols) {
		cols = tdValue.length * 2;
	}
	txt.setAttribute("rows", rows);
	txt.setAttribute("cols", cols);
	obj.appendChild(txt);
	txt.select();
	// obj.style.border = "1px dashed #ff9900";
}
// ȡ����Ԫ���е��ı��򣬲����ı����е�ֵ������Ԫ��
function cancel(obj) {
	var txtValue = document.getElementById("_text_000000000_").value;
	obj.innerText = txtValue;
}

// �¼�
document.ondblclick = function() {
	if (event.srcElement.tagName.toLowerCase() == "td") {
		changeTotext(event.srcElement);
	}

};
document.onmouseup = function() {
	if (document.getElementById("_text_000000000_")
			&& event.srcElement.id != "_text_000000000_") {
		var obj = document.getElementById("_text_000000000_").parentElement;
		cancel(obj);
	}
};

function getObjectURL(file) {
	var url = null;
	if (window.createObjectURL != undefined) { // basic
		url = window.createObjectURL(file);
	} else if (window.URL != undefined) { // mozilla(firefox)
		url = window.URL.createObjectURL(file);
	} else if (window.webkitURL != undefined) { // webkit or chrome
		url = window.webkitURL.createObjectURL(file);
	}
	return url;
}

function preview(obj, number) {
	var objUrl = getObjectURL(obj);
	console.log("objUrl = " + objUrl);
	if (objUrl) {
		switch (number) {
		case 0:
			$("#img0").attr("src", objUrl);
			break;
		case 1:
			$("#img1").attr("src", objUrl);
			break;
		case 2:
			$("#img2").attr("src", objUrl);
			break;
		case 3:
			$("#img3").attr("src", objUrl);
			break;
		default:
			alert("no match");
		}
	}
}
function hide() {
	document.all.item("file0").style.display = "none";
	if ((document.getElementById("file1"))
			&& (document.getElementById("file2"))
			&& (document.getElementById("file3"))) {
		document.all.item("file1").style.display = "none";
		document.all.item("file2").style.display = "none";
		document.all.item("file3").style.display = "none";
	}
	document.all.item("butt").style.display = "none";
	window.print();
}

