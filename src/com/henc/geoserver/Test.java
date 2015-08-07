package com.henc.geoserver;

import java.io.File;

import com.henc.geoserver.impl.GeoToolImpl;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
			GeoTool geoTool;
			geoTool = new GeoToolImpl("http://192.168.1.100:7777/geoserver", "admin", "geoserver");
			GeoserverOperationResult result;
			
			result = geoTool.publishShapeFile(new File("D:\\test\\zhongguoditu"),"bou2_4p.shp", "test","China","China", "EPSG:4326", false);
//			result = geoTool.publishMap(new File("D:\\AT\\Data\\small_sub_tm\\small_sub_tm_data_allbands.tif"), "test","smallsub","small_sub_tm_data_allbands", "GeoTIFF", true);
			//result = geoTool.disPublishMap("test", "smallsub", "small_sub_tm_data_allbands",true);
			
			System.out.println(result.getStatus());			
	}
	
//	public String publishMap(File map, String workspace,
//			String storeName, String coverageName, String format,
//			boolean isRepublish){
//		GeoTool geoTool  = new GeoToolImpl("http://localhost:7777/geoserver", "admin", "geoserver");
//		GeoserverOperationResult result = geoTool.publishMap(map, workspace,storeName,coverageName,format,isRepublish);
//		return "success";
//	}


	public String SayHelloTo(String name){
		return "hello: "+name;
	}


}
