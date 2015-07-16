function getDate(){
//	var date=document.getElementById("date");
//	date.innerHTML = startDate + endDate + (startDate<endDate);
	if(startDate<endDate){
		$.ajax({
    	method:'POST',
	   	 url : "dateInterval",
	   	 data : {
			startTime:startDate,
			endTime:endDate
	   	 },
	   	 success : dateIntervalCallBack,
	   	 dataType : "json"
	   	 });		
	}else{
		warning();
	}
}

function dateIntervalCallBack(jsons){
//	for(var i=0;i<jsons.length;i++){
//		$("#date").append(jsons[i].id+"\t"+jsons[i].name+"\t"+jsons[i].dateStr+"<br/>");
//	}
	records = jsons;
	topLeft();
}