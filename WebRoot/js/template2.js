function changeTotext(obj) {
		var tdValue = obj.innerText;
		obj.innerText = "";
		var txt = document.createElement("input");
		txt.type = "text";
		txt.value = tdValue;
		txt.id = "_text_000000000_";
		txt.setAttribute("className", "text");
		obj.appendChild(txt);
		txt.select();
		//obj.style.border = "1px dashed #ff9900"; 
	}
	// 取消单元格中的文本框，并将文本框中的值赋给单元格 
	function cancel(obj) {
		var txtValue = document.getElementById("_text_000000000_").value;
		obj.innerText = txtValue;
	}

	// 事件 
	document.ondblclick = function() {
		if (event.srcElement.tagName.toLowerCase() == "td") {
			changeTotext(event.srcElement);
		}

	}
	document.onmouseup = function() {
		if (document.getElementById("_text_000000000_")
				&& event.srcElement.id != "_text_000000000_") {
			var obj = document.getElementById("_text_000000000_").parentElement;
			cancel(obj);
		}
	}
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

	function preview(obj,number) {
		var objUrl = getObjectURL(obj);
		console.log("objUrl = " + objUrl);
		if (objUrl) {
			switch(number){
				case 0:
				 	$("#img0").attr("src", objUrl);
				    break;
				default:
				  alert("no match");
				}
		}
	}
	function hide() {
		document.all.item("butt").style.display = "none";
		document.all.item("file").style.display = "none";
		window.print();
	}