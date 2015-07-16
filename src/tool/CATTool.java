package tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import entity.Record;

public class CATTool {

	public static void main(String[] args) {
		List<Record> recordList = readFromFile("D:/2.txt");
		for(Record a:recordList){
			System.out.println("id是："+a.getId()+"名称是："+a.getName()+"日期是："+a.getDateStr());
		}
		sortRecords(recordList);
		//System.out.println(new Catalog().readFromFile("D:/1.txt"));
		for(Record a:recordList){
			System.out.println("id是："+a.getId()+"名称是："+a.getName()+"日期是："+a.getDateStr());
		}

	}
	/**
	 * 
	 * @param filePath 
	 * 		the storage path of the catalog text file
	 * @return 
	 * 		an ArrayList of Record include all records in the text file
	 */
	public static List<Record> readFromFile(String filePath){
		List<Record> recordList=new ArrayList<Record>();
		try {
            String encoding="utf-8";//GBK utf-8 这个自己选择
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;               
                while((lineTxt = bufferedReader.readLine()) != null){
                    String[] sourceStrArray = lineTxt.split(",");
                   //System.out.println(sourceStrArray.length);
                    Record recordTemp = new Record();
                    recordTemp.setId(sourceStrArray[0]);
                    recordTemp.setName(sourceStrArray[1]);
                    recordTemp.setDateStr(sourceStrArray[2]);
                    recordList.add(recordTemp);
                }
                read.close();
	        }else{
	            System.out.println("找不到指 定的文件");
	        }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
		return recordList;
	}
	
	/**
	 * sort the record list by date
	 */
	public static List<Record> sortRecords(List<Record> reList){
    	Comparator<Record> comparator = new Comparator<Record>(){  
            public int compare(Record r1, Record r2) {  
            	return r1.getDateStr().compareTo(r2.getDateStr());
            }  
    	};
	    Collections.sort(reList,comparator);
	    return  reList;
    }
	
	


}
