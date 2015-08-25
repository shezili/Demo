package web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

import entity.Feature;
import entity.ServerConfig;

public class shpEditAction {

	public static void main(String[] args) {
		String xml = getDes("http://192.168.1.100:7777/geoserver/test/wms?SERVICE=WMS&VERSION=1.1.1&REQUEST=GetFeatureInfo&FORMAT=image%2Fpng&TRANSPARENT=true&QUERY_LAYERS=test%3ACW_BG_TOWN&tiled=true&LAYERS=test%3ACW_BG_TOWN&STYLES&INFO_FORMAT=application%2Fvnd.ogc.gml&X=222&Y=109&WIDTH=256&HEIGHT=256&SRS=EPSG%3A32649&BBOX=547287.2718699165%2C3283723.6312194914%2C625471.1678513329%2C3361907.527200908");
		String preMark1 = " fid=\"";
		String tailMark1 = "\"><test:the_geom>";
		String preMark2 = "<gml:featureMember><";
		String tailMark2 = " fid=\"";
		String layerName = xml.substring(
				xml.indexOf(preMark2) + preMark2.length(),
				xml.indexOf(tailMark2));
		String fid = xml.substring(xml.indexOf(preMark1) + preMark1.length(),
				xml.indexOf(tailMark1));
		System.out.println(layerName);
		System.out.println(fid);
		System.out.println(xml.indexOf(tailMark2));

		// String param = getWFSTranscationUpdatePost("test:CW_BG_TOWN",
		// "CW_BG_TOWN.15", "cat", "13");
		// String resultString =
		// sendPost("http://192.168.1.100:7777/geoserver/wfs", param);
		// System.out.println(resultString);
		// List<String> attributes = new ArrayList<String>();
		// // attributes.add("layerName");
		// // attributes.add("fid");
		// attributes.add("cat");
		// attributes.add("OBJECTID");
		// attributes.add("AREA");
		// attributes.add("PERIMETER");
		// attributes.add("BG_D_TOWN_");
		// attributes.add("BG_D_TOWN1");
		// attributes.add("ID");
		// attributes.add("i__g__");
		// attributes.add("e__i__");
		// attributes.add("NAME");
		// attributes.add("f__d__e__");
		// attributes.add("SHAPE_Leng");
		// attributes.add("SHAPE_Area");
		// attributes.add("FILE_NAME");
		// String url =
		// "http://192.168.1.100:7777/geoserver/test/wms?SERVICE=WMS&VERSION=1.1.1&REQUEST=GetFeatureInfo&FORMAT=image%2Fpng&TRANSPARENT=true&QUERY_LAYERS=test%3ACW_BG_TOWN&tiled=true&LAYERS=test%3ACW_BG_TOWN&STYLES&INFO_FORMAT=application%2Fvnd.ogc.gml&X=212&Y=74&WIDTH=256&HEIGHT=256&SRS=EPSG%3A32649&BBOX=586379.2198606245%2C3322815.5792101994%2C605925.1938559787%2C3342361.5532055534";
		// String xml = getDes(url);
		// String workSpace =
		// ServerConfig.getInstance().getConfig().getProperty("WORKSPACE");
		// System.out.println("workspace"+workSpace);
		// Feature feature = getAttributes(xml, "test",
		// attributes,"test:CW_BG_TOWN","CW_BG_TOWN.1");
		// System.out.println(feature.toString());
	}

	/**
	 * 批量修改属性,使用该方法需要在下方updateAttribute方法中添加url等四个参数
	 */
	// public String updateAttributes(){
	// // System.out.println("开始旋转");
	// // System.out.println(feature.toString());
	// String layerName = feature.getLayerName();
	// String fid = feature.getFid();
	// String editUrl = ServerConfig.getInstance().getConfig()
	// .getProperty("EDIT_ATTRIBUTE_URL");
	// updateAttribute(editUrl,layerName, fid, "cat", feature.getCat());
	// updateAttribute(editUrl,layerName, fid, "OBJECTID",
	// feature.getObjectId());
	// updateAttribute(editUrl,layerName, fid, "AREA", feature.getArea());
	// updateAttribute(editUrl,layerName, fid, "PERIMETER",
	// feature.getPerimeter());
	// updateAttribute(editUrl,layerName, fid, "BG_D_TOWN_",
	// feature.getBg_d_town_());
	// updateAttribute(editUrl,layerName, fid, "BG_D_TOWN1",
	// feature.getBg_d_town1());
	// updateAttribute(editUrl,layerName, fid, "ID", feature.getId());
	// updateAttribute(editUrl,layerName, fid, "i__g__", feature.getI__g__());
	// updateAttribute(editUrl,layerName, fid, "e__i__", feature.getE__i__());
	// updateAttribute(editUrl,layerName, fid, "NAME", feature.getName());
	// updateAttribute(editUrl,layerName, fid, "f__d__e__",
	// feature.getF__d__e__());
	// updateAttribute(editUrl,layerName, fid, "SHAPE_Leng",
	// feature.getShape_leng());
	// updateAttribute(editUrl,layerName, fid, "SHAPE_Area",
	// feature.getShape_area());
	// updateAttribute(editUrl,layerName, fid, "FILE_NAME",
	// feature.getFile_name());
	// return "success";
	// }

	private String layerName;

	private String fid;

	private String attribute;

	private String value;

	private String updateFlag;

	/**
	 * 根据上面四个页面传回的值生成wfs请求发送到geoserver修改属性值
	 * 
	 * @return
	 */
	public String updateAttribute() {
		// String info = getDes(xmlUrl);
		// Map<String, Object> jsonMap = new HashMap<String, Object>();
		// System.out.println("内容"+xml);
		// jsonMap.put(xml, info);
		// attributeArray = JSONObject.fromObject(jsonMap);
		String url = ServerConfig.getInstance().getConfig()
				.getProperty("EDIT_ATTRIBUTE_URL");
		// System.out.println("updateAttribute:url是：  "+url);
		String param = getWFSTranscationUpdatePost(layerName, fid, attribute,
				value);
		System.out.println("================"+param);
		String resultString = sendPost(url, param);
		System.out.println("==========结果==============" + resultString);
		if (resultString.contains("SUCCESS")) {
			updateFlag = "success";
		} else {
			updateFlag = "error";
		}
		return "success";
	}

	private String coordinate;

	public String updateGeom() {
		System.out.println("=====layername:" + layerName + "=========fid:"
				+ fid + "=============coordinate:" + coordinate + "======");
		String url = ServerConfig.getInstance().getConfig()
				.getProperty("EDIT_ATTRIBUTE_URL");
		String param = getWFSTranscationUpdateGeomPost(layerName, fid,
				coordinate);
		System.out.println(param);
		String resultString = sendPost(url, param);
		System.out.println("==========结果==============" + resultString);
		if (resultString.contains("SUCCESS")) {
			updateFlag = "success";
		} else {
			updateFlag = "error";
		}
		return "success";
	}
	
	public String addPoint(){
		System.out.println("=====layername:" + layerName + "=============coordinate:" + coordinate + "======");
		String url = ServerConfig.getInstance().getConfig()
				.getProperty("EDIT_ATTRIBUTE_URL");
		String param = getWFSTranscationInsertPost(layerName,  coordinate);
		System.out.println(param);
		String resultString = sendPost(url, param);
		System.out.println("==========结果==============" + resultString);
		if (resultString.contains("SUCCESS")) {
			updateFlag = "success";
		} else {
			updateFlag = "error";
		}
		return "success";
	}
	
	public String delPoint(){
		System.out.println("=====layername:" + layerName + "=============attribute:" + attribute + "===============value:"+value+"====");
		String url = ServerConfig.getInstance().getConfig()
				.getProperty("EDIT_ATTRIBUTE_URL");
		String param = getWFSTranscationDeletePost(layerName, attribute, value);
		System.out.println(param);
		String resultString = sendPost(url, param);
		System.out.println("==========结果==============" + resultString);
		if (resultString.contains("SUCCESS")) {
			updateFlag = "success";
		} else {
			updateFlag = "error";
		}
		return "success";
	}

	private String xmlUrl;
	private JSONObject featureJson;

	/**
	 * 根据前端页面点击feature传回的url通过getDes()方法获得带有feature信息的字符串解析出其attributes
	 * 
	 * @return
	 */
	public String getAttributesValue() {

		String workSpace = ServerConfig.getInstance().getConfig()
				.getProperty("WORKSPACE");
		// String workSpace = "China";

		List<String> attributes = new ArrayList<String>();
		attributes.add("ISLANDID");
		attributes.add("ISLANDNAME");
		attributes.add("THUMBNAIL");

		// System.out.println("==========xmliurl============="+xmlUrl);
		String xml = getDes(xmlUrl);
		// System.out.println("==========xml============="+xml);

		Feature feature = getAttributes(xml, workSpace, attributes);
		System.out.println("===============================feature:"+feature.toString()+"=========");
		featureJson = JSONObject.fromObject(feature);
		System.out.println("===============================featureJson:"+feature.toString()+"=========");
		return "success";
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

	/**
	 * 发送wfs请求获取feature的属性值
	 * 
	 * @param xml
	 * @param workSpace
	 * @param attribute
	 * @return
	 */
	public String getAttributeValue(String xml, String workSpace,
			String attribute) {
		String value = "";
		// System.out.println(xml);
		String preString = "<" + workSpace + ":" + attribute + ">";
		String tailString = "<" + "/" + workSpace + ":" + attribute + ">";
		if (xml.contains(preString)) {
			value = xml.substring(xml.indexOf(preString) + preString.length(),
					xml.indexOf(tailString));
			return value;
		} else {
			value = "error";
			return value;
		}

	}

	public Feature getAttributes(String xml, String workSpace,
			List<String> attributes) {
		Feature feature = new Feature();
		for (String a : attributes) {
			String value = this.getAttributeValue(xml, workSpace, a);
			switch (a) {
			case "ISLANDID":
				feature.setIslandId(value);
				break;
			case "ISLANDNAME":
				feature.setIslandName(value);
				break;
			case "THUMBNAIL":
				feature.setThumbnail(value);
				break;
			default:
				break;
			}
		}
		String preMark1 = " fid=\"";
		String tailMark1 = "\"><" + workSpace + ":the_geom>";
		if (xml.contains(preMark1)) {
			feature.setFid(xml.substring(
					xml.indexOf(preMark1) + preMark1.length(),
					xml.indexOf(tailMark1)));
		} else {
			feature.setFid("error");
		}
		String preMark2 = "<gml:featureMember><";
		String tailMark2 = " fid=\"";
		if (xml.contains(preMark2)) {
			feature.setLayerName(xml.substring(
					xml.indexOf(preMark2) + preMark2.length(),
					xml.indexOf(tailMark2)));
		} else {
			feature.setLayerName("error");
		}
		return feature;
	}

	//命名空间：http://www.opengeospatial.net/China ，以下get...Post()方法多处用到www.opengeospatial.net，可考虑将www.opengeospatial.net写入web.xml
	public static String getWFSTranscationUpdatePost(String layerName,
			String fid, String attribute, String value) {
		String[] wlStrings = layerName.split(":");
		String workspace = wlStrings[0];
		StringBuilder sb = new StringBuilder();
		sb.append("<wfs:Transaction service=\"WFS\" version=\"1.0.0\" ");
		sb.append("	xmlns:"+workspace+"=\"http://www.opengeospatial.net/"+workspace+"\"");
		sb.append("	xmlns:ogc=\"http://www.opengis.net/ogc\"");
		sb.append("	 xmlns:wfs=\"http://www.opengis.net/wfs\">");
		sb.append("	<wfs:Update typeName=\""+layerName+"\">");
		sb.append("		<wfs:Property>");
		sb.append("			<wfs:Name>"+attribute+"</wfs:Name>");
		sb.append("			<wfs:Value>"+value+"</wfs:Value>");
		sb.append("		</wfs:Property>");
		sb.append("		<ogc:Filter>");
		sb.append("			<ogc:FeatureId fid=\""+fid+"\"/>");
		sb.append("		</ogc:Filter>");
		sb.append("	</wfs:Update>");
		sb.append("</wfs:Transaction>");
		return sb.toString();
	}

	public static String getWFSTranscationUpdateGeomPost(String layerName,
			String fid, String coordinates) {
		String[] wlStrings = layerName.split(":");
		String workspace = wlStrings[0];
		StringBuilder sb = new StringBuilder();
		sb.append("<wfs:Transaction service=\"WFS\" version=\"1.0.0\"");
		sb.append("	xmlns:"+workspace+"=\"http://www.opengeospatial.net/"+workspace+"\"");
		sb.append("	xmlns:ogc=\"http://www.opengis.net/ogc\"");
		sb.append("	xmlns:wfs=\"http://www.opengis.net/wfs\"");
		sb.append("	xmlns:gml=\"http://www.opengis.net/gml\"");
		sb.append("	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
		sb.append("	xsi:schemaLocation=\"http://www.opengis.net/wfs http://schemas.opengis.net/wfs/1.0.0/WFS-transaction.xsd\">");	
		sb.append("	<wfs:Update typeName=\""+layerName+"\">");
		sb.append("		<wfs:Property>");
		sb.append("			<wfs:Name>the_geom</wfs:Name>");
		sb.append("			<wfs:Value>");
		sb.append("				<gml:Point srsName=\"http://www.opengis.net/gml/srs/epsg.xml#4326\">");
		sb.append("					<gml:coordinates>"+coordinates+"</gml:coordinates>");
		sb.append("				</gml:Point>");
		sb.append("			</wfs:Value>");
		sb.append("		</wfs:Property>");
		sb.append("		<ogc:Filter>");
		sb.append("			<ogc:FeatureId fid=\""+fid+"\"/>");
		sb.append("		</ogc:Filter>");
		sb.append("	</wfs:Update>");
		sb.append("</wfs:Transaction>");
		return sb.toString();
	}
	
	public static String getWFSTranscationInsertPost(String layerName,String coordinates){
		String[] wlStrings = layerName.split(":");
		String workspace = wlStrings[0];
		String layer = wlStrings[1];
		StringBuilder sb = new StringBuilder();
		sb.append("<wfs:Transaction service=\"WFS\" version=\"1.0.0\"");
		sb.append("	outputFormat=\"GML2\" ");
		sb.append("	xmlns:wfs=\"http://www.opengis.net/wfs\"");
		sb.append("	xmlns:"+workspace+"=\"http://www.opengeospatial.net/"+workspace+"\"");
		sb.append("	xmlns:gml=\"http://www.opengis.net/gml\"");
		sb.append("	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
		sb.append("	xsi:schemaLocation=\"http://www.opengis.net/wfs http://schemas.opengis.net/wfs/1.0.0/WFS-transaction.xsd ");
		sb.append("	http://www.opengeospatial.net/"+workspace+" http://localhost:8080/geoserver/wfs/DescribeFeatureType?typename="+layerName+"\">");
		sb.append("	<wfs:Insert handle=\""+layer+" \">");
		sb.append("		<"+layerName+">");
		sb.append("			<"+workspace+":the_geom>");
		sb.append("				<gml:Point srsName=\"http://www.opengis.net/gml/srs/epsg.xml#4326\">");
		sb.append("					<gml:coordinates decimal=\".\" cs=\",\" ts=\" \">");
		sb.append(coordinates);
		sb.append("					</gml:coordinates>");
		sb.append("				</gml:Point>");
		sb.append("			</"+workspace+":the_geom>");
		sb.append("		</"+layerName+">");
		sb.append("	</wfs:Insert>");
		sb.append("</wfs:Transaction>");
		return sb.toString();
	}
	
	public static String getWFSTranscationDeletePost(String layerName,
			String key, String value){
		String[] wlStrings = layerName.split(":");
		String workspace = wlStrings[0];
		StringBuilder sb = new StringBuilder();
		sb.append("<wfs:Transaction service=\"WFS\" version=\"1.0.0\"");
		sb.append("	 xmlns:cdf=\"http://www.opengis.net/"+workspace+"/data\"");
		sb.append("	 xmlns:ogc=\"http://www.opengis.net/ogc\"");
		sb.append("	 xmlns:wfs=\"http://www.opengis.net/wfs\"");
		sb.append("	xmlns:"+workspace+"=\"http://www.opengeospatial.net/"+workspace+"\">");
		sb.append("	<wfs:Delete typeName=\""+layerName+"\">");
		sb.append("		<ogc:Filter>");
		sb.append("			<ogc:PropertyIsEqualTo>");
		sb.append("				<ogc:PropertyName>"+workspace+":"+key+"</ogc:PropertyName>");
		sb.append("				<ogc:Literal>"+value+"</ogc:Literal>");
		sb.append("			</ogc:PropertyIsEqualTo>");
		sb.append("		</ogc:Filter>");
		sb.append("	</wfs:Delete>");
		sb.append("</wfs:Transaction>");
		return sb.toString();
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param 生成的字符串形式的xml
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		URL u = null;
		HttpURLConnection conn = null;
		PrintWriter out = null;
		BufferedReader in = null;
		StringBuilder resXml = new StringBuilder();
		try {
			u = new URL(url);
			conn = (HttpURLConnection) u.openConnection();
			conn.setRequestMethod("POST");
			conn.setConnectTimeout(0);
			conn.setInstanceFollowRedirects(true);
			conn.setRequestProperty("Content-Type", "application/xml");
			conn.setDefaultUseCaches(false);
			conn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.3; WOW64; rv:31.0) Gecko/20100101 Firefox/31.0");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			conn.connect();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				resXml.append(line);
			}
			Document resDoc = null;
			try {
				resDoc = DocumentHelper.parseText(resXml.toString());
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resXml.toString();
	}

	public String getLayerName() {
		return layerName;
	}

	public void setLayerName(String layerName) {
		this.layerName = layerName;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}

	public String getXmlUrl() {
		return xmlUrl;
	}

	public void setXmlUrl(String xmlUrl) {
		this.xmlUrl = xmlUrl;
	}

	public JSONObject getFeatureJson() {
		return featureJson;
	}

	public void setFeatureJson(JSONObject featureJson) {
		this.featureJson = featureJson;
	}

	public String getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

}
