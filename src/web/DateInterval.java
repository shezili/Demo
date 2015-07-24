package web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import com.opensymphony.xwork2.ActionSupport;

import tool.CATTool;
import entity.Record;

public class DateInterval extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String startTime;
	
	private String endTime;
	
	private  JSONArray resultArray;

	public static void main(String[] args) {
		List<Record> resultList = new ArrayList<Record>();
		List<Record> totalList = CATTool.readFromFile("D:/2.txt");
		List<String> dInterval=new ArrayList<String>();
    	dInterval = getDateInterval("2013-04-03", "2019-05-10");
        for (Record r:totalList){
        	System.out.println("当前为："+r.getId()+"\t"+r.getDateStr());
     		System.out.println("判断前："+dInterval.contains(r.getDateStr()));
        	if(dInterval.contains(r.getDateStr())){
        		//System.out.println("判断结果："+dInterval.contains(r.getDateStr()));
        		resultList.add(r);
        		//System.out.println(r.getId()+"已加入");
        	}
        }
        for (Record j:resultList){
        	System.out.println(j.getDateStr()+"\t"+j.getId()+"\t"+j.getName());
        }
//        System.out.println("排序前：");
//        for (Record j:totalList){
//        	System.out.println(j.getDateStr()+"\t"+j.getId()+"\t"+j.getName());
//        }
        System.out.println("排序后：");
        List<Record> resultSort = CATTool.sortRecords(resultList);
        for (Record j:resultSort){
        	System.out.println(j.getDateStr()+"\t"+j.getId()+"\t"+j.getName());
        }
        JSONArray arry=JSONArray.fromObject(resultSort);
        System.out.println(arry.toString());
	}
	
	public String execute() throws Exception{
		List<Record> resultList = new ArrayList<Record>();
		List<Record> totalList = CATTool.readFromFile("D:/2.txt");
		List<String> dInterval = new ArrayList<String>();
    	dInterval = getDateInterval(startTime, endTime);
    	for (Record r:totalList){
          	if(dInterval.contains(r.getDateStr())){
          		resultList.add(r);
          	}
          }
    	List<Record> resultSort = CATTool.sortRecords(resultList);
    	for (Record j:resultSort){
        	System.out.println("rsultSort:"+j.getDateStr()+"\t"+j.getId()+"\t"+j.getName());
        }
    	JSONArray jsonArray = JSONArray.fromObject(resultSort);
    	setResultArray(jsonArray);
    	System.out.println(resultArray.toString());
		return SUCCESS;
	}
	
    /**
     * Get the Dates between Start Date and End Date.
     * @param startTime   Start Date
     * @param endTime     End Date
     * @return String List
     */
    public static List<String> getDateInterval(String startTime,String endTime){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	List<String> dateStrings = new ArrayList<String>();
    	Calendar startDate = switchToCalendar(startTime);
    	Calendar endDate = switchToCalendar(endTime);
    	List<Date> dates = getDates(startDate, endDate);
    	for (Date date : dates) {
            String dString = sdf.format(date);
            dateStrings.add(dString);
        }
    	return dateStrings;
    }
    
    /**
     * Get the Dates between Start Date and End Date.
     * @param p_start   Start Date
     * @param p_end     End Date
     * @return Dates List
     */
    public static List<Date> getDates(Calendar p_start, Calendar p_end) {
        List<Date> result = new ArrayList<Date>();
        Calendar temp = p_start;
        //temp.add(Calendar.DAY_OF_YEAR, 1);
        p_end.add(Calendar.DAY_OF_YEAR, 1);
        while (temp.before(p_end)) {
            result.add(temp.getTime());
            temp.add(Calendar.DAY_OF_YEAR, 1);
        }
        return result;
    }
    
    /**
     * switch string like "2015-04-10" to calendar
     */
    public static Calendar switchToCalendar(String dateString){
    	String[] dateDivide = dateString.split("-");
		Calendar c = Calendar.getInstance();// get an Instance
		if(dateDivide.length==3){  
			int year = Integer.parseInt(dateDivide[0].trim());//  Remove spaces
			int month = Integer.parseInt(dateDivide[1].trim());  
		    int day = Integer.parseInt(dateDivide[2].trim());  
			c.set(year, month-1, day);
		}
		return c;
    }

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public JSONArray getResultArray() {
		return resultArray;
	}

	public void setResultArray(JSONArray resultArray) {
		this.resultArray = resultArray;
	}
	
	
}
