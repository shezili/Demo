package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bean.LawenforcementReport;
import dao.LawenforcementReportDAO;

@Service
public class QueryReportService {

	@Autowired
	private LawenforcementReportDAO lawenforcementReportDAO;
	
	public LawenforcementReport findLawenforcementReport(String number){
		LawenforcementReport lReport = lawenforcementReportDAO.findById(number);
		return lReport;
	}
	
	public String updateReport(LawenforcementReport lReport){
		System.out.println("===========service============");
		System.out.println(lReport.toString());
		lawenforcementReportDAO.attachDirty(lReport);
		return "success";
	}

	public LawenforcementReportDAO getLawenforcementReportDAO() {
		return lawenforcementReportDAO;
	}

	public void setLawenforcementReportDAO(
			LawenforcementReportDAO lawenforcementReportDAO) {
		this.lawenforcementReportDAO = lawenforcementReportDAO;
	}
	
	
}
