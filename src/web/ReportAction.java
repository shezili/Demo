package web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import entity.Report1;

public class ReportAction {
	
	public static void main(String[] args) {
		ReportAction reportAction = new ReportAction();
		List<Report1> report1s = reportAction.setDataReport1();
		for(Report1 report1 : report1s){
			System.out.println(report1.toString());
		}
	}
	
	private JSONObject result;
	
	public String getReport1(){
		List<Report1> report1s = this.setDataReport1();
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("rows", report1s);
		result = JSONObject.fromObject(jsonMap);
		System.out.println("action started"+result.toString()+"test git");
		return "success";
	}
	public List<Report1> setDataReport1(){
		List<Report1> report1s = new ArrayList<Report1>();
		Report1 temp1 = new Report1();
		temp1.setNumber("1");
		temp1.setArea("洋浦港");
		temp1.setLocation("108°21'54.000″E，21°32'23.000″N");
		temp1.setDescription("变化面积约36537m2");
		temp1.setImage1("image url 1");
		temp1.setImage2("image url 2");
		report1s.add(temp1);
		Report1 temp2 = new Report1();
		temp2.setNumber("2");
		temp2.setArea("赤溪镇");
		temp2.setLocation("112°51′32.21″E 21°55′32.32″N");
		temp2.setDescription("新增填海面积约9367㎡");
		temp2.setImage1("image url 1");
		temp2.setImage2("image url 2");
		report1s.add(temp2);
		Report1 temp3 = new Report1();
		temp3.setNumber("3");
		temp3.setArea("赤坎镇");
		temp3.setLocation("112°51′31.21″E 21°57′36.32″N");
		temp3.setDescription("码头拆除，陆地恢复为海域，变化面积约6573㎡");
		temp3.setImage1("image url 1");
		temp3.setImage2("image url 2");
		report1s.add(temp3);
		Report1 temp4 = new Report1();
		temp4.setNumber("4");
		temp4.setArea("光坡镇");
		temp4.setLocation("112°51′32.21″E 21°55′32.32″N");
		temp4.setDescription("新增填海面积约9367㎡");
		temp4.setImage1("image url 1");
		temp4.setImage2("image url 2");
		report1s.add(temp4);
		Report1 temp5 = new Report1();
		temp5.setNumber("5");
		temp5.setArea("XX镇");
		temp5.setLocation("112°51′31.21″E 21°57′36.32″N");
		temp5.setDescription("码头拆除，陆地恢复为海域，变化面积约6573㎡");
		temp5.setImage1("image url 1");
		temp5.setImage2("image url 2");
		report1s.add(temp5);
	
		return report1s;
	}
	
	public JSONObject getResult() {
		return result;
	}
	public void setResult(JSONObject result) {
		this.result = result;
	}
	

	
	
	
}
