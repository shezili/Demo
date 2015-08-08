package action;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;

import service.QueryReportService;
import bean.LawenforcementReport;

public class GetReportAction {

	@Autowired
	private QueryReportService qService;

	private String type;

	private String number;

	private LawenforcementReport lReport;

	public String getReport() {
		ActionContext context = ActionContext.getContext();
		@SuppressWarnings("unchecked")
		Map<String, Object> request = (Map<String, Object>) context
				.get("request");
			
		lReport = qService.findLawenforcementReport(number);
		request.put("lReport", lReport);
	 //System.out.println(this.toString());
//		Method[] methods = lReport.getClass().getMethods();
//		for(Method method : methods){
//			if(method.getName().startsWith("set")){
//				try {
//					method.invoke(lReport, "insert");
//				}  catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
		//System.out.println(lReport.toString());
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

	@Override
	public String toString() {
		return "GetReportAction [qService=" + qService + ", type=" + type
				+ ", number=" + number + ", lReport=" + lReport + "]";
	}
	
	
}
