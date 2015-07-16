package web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tool.CATTool;
import entity.Record;
import net.sf.json.JSONObject;

public class CalculateInfoAction {
	
	private JSONObject result;

	private String rows;

	private String page;
	
	
	public String findCalPageDataJson(){
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		List<Record> totalList = CATTool.readFromFile("D:/2.txt");
		CATTool.sortRecords(totalList);
		List<Record> pageList = findRecords(intPage, number, totalList);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", totalList.size());
		jsonMap.put("rows", pageList);
		result = JSONObject.fromObject(jsonMap);
		return "success";
	}
	
	public List<Record> findRecords(int pageNo,int pageSize,List<Record> result){		
		List<Record> list=new ArrayList<Record>();
		int bottom = pageNo*pageSize;
		if(bottom>result.size()){
			bottom = result.size();
		}
		for(int i = (pageNo-1)*pageSize;i<bottom;i++){
			list.add(result.get(i));
		}
		return list;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	
	
}
