package service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bean.Tabproresult;
import bean.Tabresultdata;
import bean.Tabresultmetadata;
import dao.TabproresultDAO;
import dao.TabresultdataDAO;
import dao.TabresultmetadataDAO;
import entity.ShowMetadata;
/**
 * 元数据的操作
 * @author Administrator
 *
 */
@Service
public class MetaDataService {

	@Autowired
	private TabresultmetadataDAO tabresultmetadataDAO;
	
	@Autowired
	private TabresultdataDAO tabresultdataDAO;
	
	@Autowired TabproresultDAO tabproresultDAO;

	public List<ShowMetadata> findTabresultmetadataByMapName(
			String mapName, int first, int max) {

		@SuppressWarnings("unchecked")
		List<Tabresultmetadata> tabresultmetadatas = tabresultmetadataDAO
				.fuzzyQueryByMapName(mapName, first, max);
		
		List<ShowMetadata> showMetadata = new ArrayList<ShowMetadata>(); 
		for (Tabresultmetadata tabresultmetadata : tabresultmetadatas){	
			Tabresultdata tabresultdata = tabresultdataDAO.findById(tabresultmetadata.getResultdataId());
			Tabproresult tabproresult = tabproresultDAO.findById(tabresultmetadata.getResultdataId());
			ShowMetadata temp = new ShowMetadata();
			
			temp.setMapName(tabresultmetadata.getMapName());
			temp.setDataTypeDescr(tabresultdata.getTabdatatype().getDescr());
			temp.setDistributeDeptName(tabresultmetadata.getTabdepartmentByDistributeDept().getDeptName());
			temp.setState(tabresultdata.getState());
			temp.setResolution(tabresultdata.getResolution());
			temp.setProstarttime(this.TimestampToString(tabproresult.getProstarttime()));
			temp.setProendtime(this.TimestampToString(tabproresult.getProendtime()));
			temp.setResultdatapeople(tabproresult.getResultdatapeople());
			temp.setScale(tabresultmetadata.getScale());
			
			showMetadata.add(temp);
		}
		// if(tabresultmetadatas.size()!=0){
		// //System.out.println(tabresultmetadatas.size()+"\n"+tabresultmetadatas.get(0).getMapName()+tabresultmetadatas.get(0).getInputDate()+"\n"+tabresultmetadatas.get(1).getMapName()+tabresultmetadatas.get(1).getInputDate());
		// return tabresultmetadatas;
		// }else{
		// //System.out.println("无记录");
		// }
		System.out.println("查询出的记录数量：" + tabresultmetadatas.size());
		return showMetadata;

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

	public TabresultdataDAO getTabresultdataDAO() {
		return tabresultdataDAO;
	}

	public void setTabresultdataDAO(TabresultdataDAO tabresultdataDAO) {
		this.tabresultdataDAO = tabresultdataDAO;
	}

	public TabproresultDAO getTabproresultDAO() {
		return tabproresultDAO;
	}

	public void setTabproresultDAO(TabproresultDAO tabproresultDAO) {
		this.tabproresultDAO = tabproresultDAO;
	}
	
	

}
