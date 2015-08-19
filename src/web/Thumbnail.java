package web;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream.PutField;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.net.aso.f;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.Key;

import entity.Picture;
import entity.Record;
import entity.ServerConfig;

public class Thumbnail  {

	public static void main(String[] args) {
//		String url = "http://localhost:7777/geoserver/test/wms?SERVICE=WMS&VERSION=1.1.1&REQUEST=GetFeatureInfo&FORMAT=image%2Fpng&TRANSPARENT=true&QUERY_LAYERS=test%3ACW_BG_TOWN&tiled=true&LAYERS=test%3ACW_BG_TOWN&STYLES&INFO_FORMAT=text%2Fxml&X=4&Y=127&WIDTH=256&HEIGHT=256&SRS=EPSG%3A32649&BBOX=625471.1678513326%2C3283723.6312194914%2C703655.063832749%2C3361907.527200908";
//		// String xml=getDes(url);
//		// System.out.println(xml);
//		String pk1 = getPictureKeyByString(url, "test", "cat");
//		// String pk2 = getPictureKeyByXml(url);
//		String aaaString = "D:" + "\\" + "th" + "\\" + pk1 + ".png";
//		// System.out.println(pk1+"!!!!!!!!!"+pk2);
//		File file = new File("E:/Program Files (x86)/apache-tomcat-7.0.57/webapps/Demo/island_data/haidao01_0001/image/12_2015_mainthumbnail.png");
//		String name="123";     
//        String fileName=file.getAbsolutePath();     
//		String aString = fileName.replace("mainthumbnail", "secothumbnail");
//		System.out.println(aString);
        //file.renameTo(new File(name+".jpg"));   //改名     
		String noString = "05";
		String pathString = "E:/Program Files (x86)/apache-tomcat-7.0.57/webapps/Demo/island_data/haidao"+noString+"_00"+noString+"/image/5_main_2015.png";
		File sourceFile = new File(pathString);
		
		String filePath2 = "E:/Program Files (x86)/apache-tomcat-7.0.57/webapps/Demo/island_data/haidao"+noString+"_00"+noString+"/image/11_2015_mainthumbnail.png";
	
		sourceFile.renameTo(new File(filePath2));
//		String parentPath = sourceFile.getParent();
//
//		File parentDirectory = new File(parentPath);
//		String[]  otherPictures = parentDirectory.list();
//		for(int i=0;i<otherPictures.length;i++){
//			String otherPath = parentPath+"\\"+otherPictures[i];
//			if(!otherPath.equals(filePath2)){
//			File tempFile = new File(otherPath);
//			tempFile.renameTo(new File(parentPath+"\\1"+i+"_201"+i+"_"+"secothumbnail.png"));
//			}
//			
//		}
       
	}


	private String xmlUrl;
	private JSONObject result;
	/**
	 * According to a certain attributes to generate the corresponding path
	 */
	public String showThumbnail() {
		// System.out.println("started suolutu");
		List<Picture> pictures = new ArrayList<Picture>();
		String mainPictureUrl = "";
//		List<String> pictureUrl = new ArrayList<String>();
//		List<String> pictureName = new ArrayList<String>();
		//List<String> textUrl = new ArrayList<String>();
//		setWorkSpace(ServerConfig.getInstance().getConfig()
//				.getProperty("WORKSPACE"));
//		setpAtt(ServerConfig.getInstance().getConfig()
//				.getProperty("FILEATTRIBUTE"));
//		System.out.println("获取的属性" + getWorkSpace() + getpAtt());
		// System.out.println(toString());
		String workSpace = ServerConfig.getInstance().getConfig().getProperty("WORKSPACE");
		String pAtt = ServerConfig.getInstance().getConfig().getProperty("FILEATTRIBUTE");
		String pk = getPictureKeyByString(xmlUrl, workSpace, pAtt);

//		System.out.println(pk);
		if (pk != "error") {
		
			String path = this.getClass().getClassLoader().getResource("")
					.getPath();
			path = path.replace("/classes", "").replace("%20", " ")
					.replace("/WEB-INF", "");
			String textPath = path + "island_data" + "/" + pk;
			// String textPath = "../../../island_data" + "/" + pk;
//			System.out.println("textpath" + textPath);
//			File textFolde = new File(textPath);
//			String[] temp1 = textFolde.list();
//			for (int i = 0; i < temp1.length; i++) {
//				if (!temp1[i].equals("image")) {
//					textUrl.add("../island_data/" + pk + "/" + temp1[i]);
//				}
//			}

			String picturePath = textPath + "/image";
			File pictureFolder = new File(picturePath);
			String[] temp2 = pictureFolder.list();               //picture文件名的结构为    uuid_时间_类别.格式
			for (int i = 0; i < temp2.length; i++) {	
				
				String[] tempStrings1 = temp2[i].split("\\.");
				String[] tempStrings2 = tempStrings1[0].split("_");
				Picture pictureTemp = new Picture();
				pictureTemp.setPreName(tempStrings2[0]);
				pictureTemp.setTime(tempStrings2[1]);
				pictureTemp.setType(tempStrings2[2]);
				pictureTemp.setUrl("../island_data/" + pk + "/image/" + temp2[i]);
				pictureTemp.setFullPath(picturePath+"/"+temp2[i]);
//				pictureName.add(temp2[i]);
				//pictureUrl.add("../island_data/" + pk + "/image/" + temp2[i]);
//				System.out.println(tempStrings1[0]+"!!!!!"+tempStrings1[1]);
//				System.out.println(tempStrings2[0]+"!!!!!"+tempStrings2[1]+"!!!!!"+tempStrings2[2]);
				if(pictureTemp.getType().equals("mainthumbnail")){
					mainPictureUrl = pictureTemp.getUrl();
					System.out.println("============mainPictureUrl========="+mainPictureUrl);
				}
				pictures.add(pictureTemp);
			}
		}
		sortPictures(pictures);
//		for(Picture p1 : pictures){
//			System.out.println(p1.getPreName()+p1.getType()+p1.getTime());
//		}
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		//jsonMap.put("textUrl", textUrl);
//		jsonMap.put("pictureUrl", pictureUrl);
		jsonMap.put("pictures", pictures);
		jsonMap.put("mainPictureUrl", mainPictureUrl);
		jsonMap.put("islandName",pk);
		result = JSONObject.fromObject(jsonMap);

		return "success";
	}

	/**
	 * @param url
	 *            url of the xml
	 * @param workSpace
	 *            workspace of the layer
	 * @param pAtt
	 *            name of the properties Binding with pictures
	 * @return value of the properties int the pAtt
	 */
	public static String getPictureKeyByString(String url, String workSpace,
			String pAtt) {
		String key = "";
		String xml = getDes(url);
//		System.out.println(xml);
		String preString = "<" + workSpace + ":" + pAtt + ">";
		String tailString = "<" + "/" + workSpace + ":" + pAtt + ">";
		if (xml.contains(preString)) {
			key = xml.substring(xml.indexOf(preString) + preString.length(),
					xml.indexOf(tailString));
			return key;
		} else {
			key = "error";
			return key;
		}

	}

	/**
	 * get the xml through the url
	 */
	private static String getDes(String url) {
		String res = "";
		InputStream is = null;
		try {
			StringBuilder req = new StringBuilder();
			req.append(url);
			URL u = new URL(req.toString());
			URLConnection conn = u.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/5.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.connect();
			is = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = "";
			for (line = br.readLine(); line != null; line = br.readLine()) {
				res += line;
			}
		} catch (IOException ex) {
			return ex.getMessage();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException ee) {
					return ee.getMessage();
				}
			}
		}
		return res;
	}
	

	public static List<Picture> sortPictures(List<Picture> pirList){
    	Comparator<Picture> comparator = new Comparator<Picture>(){  
            public int compare(Picture p1, Picture p2) {  
            	return p1.getTime().compareTo(p2.getTime());
            }  
    	};
	    Collections.sort(pirList,comparator);
	    return  pirList;
    }
	
	private String picturePath;
	private String setResult;
	
	public String setMainPicture(){
		System.out.println("picturePath========================="+picturePath);
		
		File sourceFile = new File(picturePath);
		String filePath = sourceFile.getAbsolutePath();
		
		String filePath2 = renameSecToMain(filePath);
	
		sourceFile.renameTo(new File(filePath2));
		String parentPath = sourceFile.getParent();

		File parentDirectory = new File(parentPath);
		String[]  otherPictures = parentDirectory.list();
		for(int i=0;i<otherPictures.length;i++){
			if(otherPictures[i].contains("mainthumbnail")){
				String tempPath = parentPath+"\\"+otherPictures[i];
				if(!(tempPath.equals(filePath2))){
		
					File tempFile = new File(parentPath+"/"+otherPictures[i]);
					tempFile.renameTo(new File(renameMainToSec(tempPath)));
				}
				
			}
		}
		setSetResult("ok");
		return "success";
	}
	
	public String renameMainToSec(String name){
		String name2 = name.replace("mainthumbnail","secothumbnail");
		return name2;
	}
	
	public String renameSecToMain(String name){
		String name2 = name.replace("secothumbnail","mainthumbnail");
		return name2;
	}
	// public static String getPictureKeyByXml(String url){
	// String key = "";
	// String xml = getDes(url);
	// Document doc = null;
	// try {
	// doc = DocumentHelper.parseText(xml);
	// } catch (DocumentException e1) {
	// e1.printStackTrace();
	// }
	// Element root = doc.getRootElement();
	// Namespace nstest = root.getNamespaceForPrefix("test");
	// Namespace nsgml = root.getNamespaceForPrefix("gml");
	// QName catQ = QName.get("cat", nstest);
	// QName areaQ = QName.get("AREA", nstest);
	// Element spatialParent = root.element(QName.get("featureMember", nsgml))
	// .element(QName.get("CW_BG_TOWN", nstest));
	//
	// key = spatialParent.elementText(catQ);
	// String area = spatialParent.elementText(areaQ);
	// System.out.println(area);
	// return key;
	// }


	public String getXmlUrl() {
		return xmlUrl;
	}

	public void setXmlUrl(String xmlUrl) {
		this.xmlUrl = xmlUrl;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public String getSetResult() {
		return setResult;
	}

	public void setSetResult(String setResult) {
		this.setResult = setResult;
	}
	
	
	

}
