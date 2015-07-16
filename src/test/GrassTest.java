package test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.henc.grass.GRASSCaller;

public class GrassTest {

	public static void main(String[] args) {
		ImageSection("C:\\Program Files (x86)\\GRASS GIS 7.0.0beta2",
				"D:\\test\\test_grass", UUID.randomUUID().toString(), new File(
						"D:\\AT\\20100730.tif"), "E:\\temp\\2", "wlr",
				"D:\\AT\\20100730.tif", 30);
//		 String pathStr = "D:\\test\\test_grass";
//		 // String dem = pathStr + "/dem2.tif";
//		 GRASSCaller caller = new GRASSCaller(
//		 "C:\\Program Files (x86)\\GRASS GIS 7.0.0beta2", pathStr, UUID
//		 .randomUUID().toString(), new File(
//		 "D:\\AT\\20100730.tif"));
//		 String[] result1 = caller
//		 .runGrass("r.in.gdal input=D:\\AT\\20100730.tif output=work1");
//		 for (int i = 0; i < result1.length; i++) {
//		 System.out.println(result1[i]);
//		 }
//		 System.out.println("栅格图导入完毕！");
//		 String[] result2 = caller
//		 .runGrass("v.in.ogr dsn=E:\\temp\\2 layer=wlr output=wlr -o");
//		 for (int i = 0; i < result2.length; i++) {
//		 System.out.println(result2[i]);
//		 }
//		 System.out.println("矢量图导入完毕！");
//		
//		 String[] result3 = caller
//		 .runGrass("v.to.rast input=wlr output=wlrRaster use=val");
//		 for (int i = 0; i < result3.length; i++) {
//		 System.out.println(result3[i]);
//		 }
//		 System.out.println("矢量图转化完毕！");
//		
//		 caller.runGrass("r.mapcalc expression=\"out1=if(work1.1*wlrRaster>0,work1.1,null())\"");
//		 caller.runGrass("g.region rast=out1 -p");
//		 caller.runGrass("g.region res=30 -ap");
//		 caller.runGrass("r.resamp.interp input=out1 output=out_resample1");
//		 System.out.println("图层1！");
//		
//		 caller.runGrass("r.mapcalc expression=\"out2=if(work1.2*wlrRaster>0,work1.2,null())\"");
//		 caller.runGrass("g.region rast=out2 -p");
//		 caller.runGrass("g.region res=30 -ap");
//		 caller.runGrass("r.resamp.interp input=out2 output=out_resample2");
//		 System.out.println("图层2！");
//		
//		 caller.runGrass("r.mapcalc expression=\"out3=if(work1.3*wlrRaster>0,work1.3,null())\"");
//		 caller.runGrass("g.region rast=out3 -p");
//		 caller.runGrass("g.region res=30 -ap");
//		 caller.runGrass("r.resamp.interp input=out3 output=out_resample3");
//		 System.out.println("图层3！");
//		
//		 caller.runGrass("r.mapcalc expression=\"out4=if(work1.4*wlrRaster>0,work1.4,null())\"");
//		 caller.runGrass("g.region rast=out4 -p");
//		 caller.runGrass("g.region res=30 -ap");
//		 caller.runGrass("r.resamp.interp input=out4 output=out_resample4");
//		 System.out.println("图层4！");
//		
//		 /*
//		 * caller.runGrass(
//		 *
//		 " r.mapcalc expression='out5 = if(work1.5*wlrRaster>0,work1.5,null())' "
//		 * ); caller.runGrass("g.region rast=out5 -p");
//		 * caller.runGrass("g.region res=50 -ap");
//		 * caller.runGrass("r.resamp.interp input=out5 output=out_resample5");
//		 *
//		 * caller.runGrass(
//		 *
//		 " r.mapcalc expression='out6 = if(work1.6*wlrRaster>0,work1.6,null())' "
//		 * ); caller.runGrass("g.region rast=out6 -p");
//		 * caller.runGrass("g.region res=60 -ap");
//		 * caller.runGrass("r.resamp.interp input=out6 output=out_resample6");
//		 */
//		
//		 caller.runGrass("i.group group=g subgroup=subg input=out_resample1,out_resample2,out_resample3,out_resample4");
//		 System.out.println("图层组合完毕！");
//		
//		 caller.runGrass("r.out.gdal input=g output=D:/qqq.jpg format=GTiff");
//		 System.out.println("图层输出完毕！");
	}

	public static String ImageSection(String gisBase, String gisDBase,
			String locationName, File srsFile, String vfilePath, String vName,
			String rfilePath, int accuracy) {
		GRASSCaller caller = new GRASSCaller(gisBase, gisDBase, locationName,
				srsFile);
		String importVectorCmd = "v.in.ogr dsn="+ vfilePath +" layer="+vName+" output=wlr -o";
		caller.runGrass(importVectorCmd);
		String transformCmd = "v.to.rast input=wlr output=wlrRaster use=val";
		caller.runGrass(transformCmd);
		String importRasterCmd = "r.in.gdal input=" + rfilePath
				+ " output=importedRasterUnique";
		String[] result = caller.runGrass(importRasterCmd);
		int rasterCount = 0;
		List<String> layerName = new ArrayList<String>();
		for (int i = 0; i < result.length; i++) {
			if (result[i].contains("importedRasterUnique")) {
				rasterCount++;
				String temp1 = "r.mapcalc expression=\"out" + rasterCount
						+ "=if(" + "importedRasterUnique" + "." + rasterCount + "*"
						+ "wlrRaster" + ">0," + "importedRasterUnique" + "."
						+ rasterCount + ",null())\"";
				String temp2 = "g.region rast=out"+rasterCount+" -p";
				String temp3 = "g.region res="+accuracy+" -ap";
				String temp4 = "r.resamp.interp input=out"+rasterCount+" output=out_resample"+rasterCount;
				System.out.println(temp1+"\n"+temp2+"\n"+temp3+"\n"+temp4);
				caller.runGrass(temp1);
				caller.runGrass(temp2);
				caller.runGrass(temp3);
				caller.runGrass(temp4);
//				String[] aaaString = caller.runGrass(temp4);
//				for(int j=0;j<aaaString.length;j++){
//					System.out.println(aaaString[j]);
//				}
				layerName.add("out_resample"+rasterCount);
			}
		}
		String groupCmd = "i.group group=g subgroup=subg input=";
		for(String name : layerName){
			groupCmd = groupCmd + name +",";
		}
		
		System.out.println(groupCmd.substring(0, groupCmd.length()-1));
		
		caller.runGrass(groupCmd.substring(0, groupCmd.length()-1));
		
		//caller.runGrass("i.group group=g subgroup=subg input=out1,out2,out3,out4");
		
		System.out.println(rasterCount);
		
		caller.runGrass("r.out.gdal input=g output=D:/sss111.jpg format=GTiff");
		System.out.println("completed");
		return "";
	}

}
