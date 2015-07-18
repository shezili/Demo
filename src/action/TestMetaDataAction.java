package action;

import org.springframework.beans.factory.annotation.Autowired;

import service.MetaDataService;

public class TestMetaDataAction {

	@Autowired 
	private MetaDataService metaDataService;
	
	private String mapName; 
	
	public String test(){
		metaDataService.test(mapName);
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
	
	
	
	
}
