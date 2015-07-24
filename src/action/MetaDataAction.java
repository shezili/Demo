package action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import entity.Tabresultmetadata_1;
import service.MetaDataService;

public class MetaDataAction {

	@Autowired 
	private MetaDataService metaDataService;
	
	private String mapName; 
	
	private JSONObject result;

	private String rows;

	private String page;
	
	public String findMetaDataByMapName(){
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		int total = metaDataService.findSizeOfTabresultmetadata(mapName);
		int first = (intPage - 1) * number;
		int max = intPage*number < total ?  intPage*number : total;
		List<Tabresultmetadata_1> pageList = metaDataService.findTabresultmetadataByMapName(mapName, first, max);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", total);
		jsonMap.put("rows", pageList);
		result = JSONObject.fromObject(jsonMap);
		System.out.println(result.toString());
		return "success";
	}
	
	public MetaDataService getMetaDataService() {
		return metaDataService;
	}

	public void setMetaDataService(MetaDataService metaDataService) {
		this.metaDataService = metaDataService;
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
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
