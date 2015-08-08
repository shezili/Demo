package action;

import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import service.QueryReportService;
import bean.LawenforcementReport;

public class ReportAction {

	@Autowired
	private QueryReportService qService;

	private String type;

	private String number;

	private LawenforcementReport lReport;
	
	private String jsonString;

	public String getReport() {
		ActionContext context = ActionContext.getContext();
		@SuppressWarnings("unchecked")
		Map<String, Object> request = (Map<String, Object>) context
				.get("request");
			
		lReport = qService.findLawenforcementReport(number);
		request.put("lReport", lReport);
		return "success";
	}
	
	private String result;
	
	public String updateReport(){
		System.out.println("===================started======================");
//		lReport.setPictureUrl("Ö´ÐÐ¹ýÁË");
//		qService.updateReport(lReport);
		//System.out.println(jsonString);
		JSONObject obj = new JSONObject().fromObject(jsonString);
		LawenforcementReport praReport = (LawenforcementReport)JSONObject.toBean(obj,LawenforcementReport.class);
		System.out.println(praReport.toString());
		result = qService.updateReport(praReport);
		return "success";
	}

	public QueryReportService getqService() {
		return qService;
	}

	public void setqService(QueryReportService qService) {
		this.qService = qService;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public LawenforcementReport getlReport() {
		return lReport;
	}

	public void setlReport(LawenforcementReport lReport) {
		this.lReport = lReport;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	
	
	
}
