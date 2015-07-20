package service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bean.Tabresultmetadata;
import dao.TabresultmetadataDAO;
import entity.Tabresultmetadata_1;

@Service
public class MetaDataService {

	@Autowired
	private TabresultmetadataDAO tabresultmetadataDAO;

	public List<Tabresultmetadata_1> findTabresultmetadataByMapName(
			String mapName, int first, int max) {

		@SuppressWarnings("unchecked")
		List<Tabresultmetadata> tabresultmetadatas = tabresultmetadataDAO
				.fuzzyQueryByMapName(mapName, first, max);
		
		List<Tabresultmetadata_1> tabresultmetadata_1s = new ArrayList<Tabresultmetadata_1>(); 
		for (Tabresultmetadata tabresultmetadata : tabresultmetadatas){
			Tabresultmetadata_1 temp = new Tabresultmetadata_1();
			temp.setCharacterSet(tabresultmetadata.getCharacterSet());
			temp.setCreationDate(TimestampToString(tabresultmetadata.getCreationDate()));
			temp.setDataVersion(tabresultmetadata.getDataVersion());
			temp.setDistrict(tabresultmetadata.getDistrict());
			temp.setHistory(tabresultmetadata.getHistory());
			temp.setInputDate(TimestampToString(tabresultmetadata.getInputDate()));
			temp.setLanguage(tabresultmetadata.getLanguage());
			temp.setLatestmodiDate(TimestampToString(tabresultmetadata.getLatestmodiDate()));
			temp.setMapIdentifier(tabresultmetadata.getMapIdentifier());
			temp.setMapName(tabresultmetadata.getMapName());
			temp.setResultdataId(tabresultmetadata.getResultdataId());
			temp.setResultQuality(tabresultmetadata.getResultQuality());
			temp.setSavingAddr(tabresultmetadata.getSavingAddr());
			temp.setScale(tabresultmetadata.getScale());
			tabresultmetadata_1s.add(temp);
		}
		// if(tabresultmetadatas.size()!=0){
		// //System.out.println(tabresultmetadatas.size()+"\n"+tabresultmetadatas.get(0).getMapName()+tabresultmetadatas.get(0).getInputDate()+"\n"+tabresultmetadatas.get(1).getMapName()+tabresultmetadatas.get(1).getInputDate());
		// return tabresultmetadatas;
		// }else{
		// //System.out.println("无记录");
		// }
		System.out.println("查询出的记录数量：" + tabresultmetadatas.size());
		return tabresultmetadata_1s;

	}

	public String TimestampToString(Timestamp timestamp) {
		String tsStr = "";
		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			tsStr = sdf.format(timestamp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tsStr;
	}

	public int findSizeOfTabresultmetadata(String mapName) {
		long result = tabresultmetadataDAO.querySizeOfFixedMapName(mapName);
		return (int) result;
	}

	public TabresultmetadataDAO getTabresultmetadataDAO() {
		return tabresultmetadataDAO;
	}

	public void setTabresultmetadataDAO(
			TabresultmetadataDAO tabresultmetadataDAO) {
		this.tabresultmetadataDAO = tabresultmetadataDAO;
	}

}
