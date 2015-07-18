package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bean.Tabresultmetadata;
import dao.TabresultmetadataDAO;

@Service
public class MetaDataService {

	@Autowired
	private TabresultmetadataDAO tabresultmetadataDAO;
	
	public String test(String mapName){
		List<Tabresultmetadata> tabresultmetadatas = tabresultmetadataDAO.fuzzyQueryByMapName(mapName);
		if(tabresultmetadatas.size()!=0){
			System.out.println(tabresultmetadatas.size()+"\n"+tabresultmetadatas.get(0).getMapName()+tabresultmetadatas.get(0).getInputDate()+"\n"+tabresultmetadatas.get(1).getMapName()+tabresultmetadatas.get(1).getInputDate());
		}else{
			System.out.println("ÎÞ¼ÇÂ¼");
		}
		

		return "success";
	}
	public TabresultmetadataDAO getTabresultmetadataDAO() {
		return tabresultmetadataDAO;
	}

	public void setTabresultmetadataDAO(TabresultmetadataDAO tabresultmetadataDAO) {
		this.tabresultmetadataDAO = tabresultmetadataDAO;
	}
	
	
}
