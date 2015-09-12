package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

public class PostTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String resultString = sendPost("http://192.168.1.100:7777/geoserver/wcs","<DescribeCoverage version=\"1.0.0\" service=\"WCS\"  xmlns=\"http://www.opengis.net/wcs\"  xmlns:nurc=\"http://www.nurc.nato.int\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"  xsi:schemaLocation=\"http://www.opengis.net/wcs http://schemas.opengis.net/wcs/1.0.0/describeCoverage.xsd\"> <Coverage>nurc:Img_Sample</Coverage></DescribeCoverage>");
//		System.out.println(resultString);
		String param = getDescribeCoveragePost("nurc:mosaic");
		System.out.println(param);
		String resultString = sendPost("http://192.168.1.100:7777/geoserver/wcs",param);
		System.out.println(resultString);
		getBbox(resultString);
	}

	
	public static String getDescribeCoveragePost(String layerName){
		StringBuilder sb = new StringBuilder();
		sb.append("<DescribeCoverage");
		sb.append("	version=\"1.0.0\"");
		sb.append("	service=\"WCS\"");
		sb.append("	xmlns=\"http://www.opengis.net/wcs\"");
//		sb.append("xmlns:nurc=\"http://www.nurc.nato.int\"");
		sb.append("	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
		sb.append("	xsi:schemaLocation=\"http://www.opengis.net/wcs");
		sb.append("	http://schemas.opengis.net/wcs/1.0.0/describeCoverage.xsd\">");
		sb.append("<Coverage>"+layerName+"</Coverage>");
		sb.append("</DescribeCoverage>");
		return sb.toString();
	}
	public static String getBbox(String xml){
		String bbox = "";
		String prefix = "<gml:Envelope srsName=\"EPSG:4326\"><gml:pos>";
		String middle = "</gml:pos><gml:pos>";
		String suffix = "</gml:pos></gml:Envelope>";
		System.out.println(xml.indexOf(prefix)+"====="+xml.indexOf(middle));
		System.out.println(xml.substring(xml.indexOf(prefix), xml.indexOf(suffix)));
		String temp = xml.substring(xml.indexOf(prefix), xml.indexOf(suffix));
		System.out.println(temp.indexOf(prefix)+"======"+temp.indexOf(middle)+"======"+temp.indexOf(suffix));
	    bbox = temp.substring(temp.indexOf(prefix)+prefix.length(),temp.indexOf(middle)) + " " + temp.substring(temp.indexOf(middle)+middle.length());
		System.out.println("========bbox:"+bbox);
		return bbox;
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
}
