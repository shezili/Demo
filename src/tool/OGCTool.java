package tool;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

import org.cts.CRSFactory;
import org.cts.IllegalCoordinateException;
import org.cts.crs.CRSException;
import org.cts.crs.CoordinateReferenceSystem;
import org.cts.crs.GeodeticCRS;
import org.cts.op.CoordinateOperation;
import org.cts.op.CoordinateOperationFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;

public class OGCTool {

	private static String getWCSPost(String iden, String crs, String format,
			String lowCorn, String upCorn, String gridType, String ori,
			String off, String gridCS) {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><GetCoverage version=\"1.1.1\" service=\"WCS\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.opengis.net/wcs/1.1.1\" xmlns:ows=\"http://www.opengis.net/ows/1.1\" xmlns:gml=\"http://www.opengis.net/gml\" xmlns:ogc=\"http://www.opengis.net/ogc\" xsi:schemaLocation=\"http://www.opengis.net/wcs/1.1.1 http://schemas.opengis.net/wcs/1.1.1/wcsAll.xsd\"><ows:Identifier>");
		sb.append(iden);
		sb.append("</ows:Identifier><DomainSubset><ows:BoundingBox crs=\"");
		sb.append(crs);
		sb.append("\"><ows:LowerCorner>");
		sb.append(lowCorn);
		sb.append("</ows:LowerCorner><ows:UpperCorner>");
		sb.append(upCorn);
		sb.append("</ows:UpperCorner></ows:BoundingBox></DomainSubset><Output store=\"true\" format=\"");
		sb.append(format);
		sb.append("\"><GridCRS><GridBaseCRS>");
		sb.append(crs);
		sb.append("</GridBaseCRS><GridType>");
		sb.append(gridType);
		sb.append("</GridType><GridOrigin>");
		sb.append(ori);
		sb.append("</GridOrigin><GridOffsets>");
		sb.append(off);
		sb.append("</GridOffsets><GridCS>");
		sb.append(gridCS);
		sb.append("</GridCS></GridCRS></Output></GetCoverage>");
		return sb.toString();
	}
	
	public static boolean getWCSMapPost(String url, String cov, String format,
			String resFile, String bbox) {
		boolean flag = false;
		String xml = getDes(url, cov, "1.1.1");

		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		Element root = doc.getRootElement();

		// base crs info
		Namespace nsWcs = root.getNamespaceForPrefix("wcs");
		QName crsQ = QName.get("GridBaseCRS", nsWcs);
		QName gribQ = QName.get("GridType", nsWcs);
		QName offsetQ = QName.get("GridOffsets", nsWcs);
		QName gcsQ = QName.get("GridCS", nsWcs);

		Element spatialParent = root
				.element(QName.get("CoverageDescription", nsWcs))
				.element(QName.get("Domain", nsWcs))
				.element(QName.get("SpatialDomain", nsWcs));
		Element baseParent = spatialParent.element(QName.get("GridCRS", nsWcs));
		String crs = baseParent.elementText(crsQ);
		String gridType = baseParent.elementText(gribQ);
		String offsets = baseParent.elementText(offsetQ);
		String gridCS = baseParent.elementText(gcsQ);

		// original lonlat
		Namespace nsOws = root.getNamespaceForPrefix("ows");
		QName corQ = QName.get("BoundingBox", nsOws);
		List<Element> corArr = spatialParent.elements(corQ);
		Element cornEle = null;
		for (Element temp : corArr) {
			if (crs.equals(temp.attributeValue("crs"))) {
				cornEle = temp;
				break;
			}
		}

		String lowCorn = "";
		String upCorn = "";
		List<Element> cornArr = cornEle.elements();
		for (Element temp : cornArr) {
			if (temp.getName().endsWith("LowerCorner")) {
				lowCorn = temp.getText();
			} else if (temp.getName().endsWith("UpperCorner")) {
				upCorn = temp.getText();
			}
		}
		String ori = lowCorn.substring(0, lowCorn.indexOf(" "))
				+ upCorn.substring(upCorn.indexOf(" "));

		// bbox
		if (!bbox.equalsIgnoreCase("all")) {
			String[] temp = bbox.split(" ");
			if (temp.length == 4) {
				lowCorn = temp[0] + " " + temp[1];
				upCorn = temp[2] + " " + temp[3];
			}
		}

		String contentStr = getWCSPost(cov, crs, "image/tiff", lowCorn, upCorn,
				gridType, ori, offsets, gridCS);

		URL u = null;
		HttpURLConnection conn = null;
		PrintWriter out = null;
		BufferedReader in = null;
		try {
			u = new URL(url);
			conn = (HttpURLConnection) u.openConnection();
			conn.setRequestMethod("POST");
			// conn.setConnectTimeout(100000);
			// conn.setReadTimeout(300000);
			// conn.setDefaultUseCaches(false);
			// conn.setUseCaches(false);
			// conn.setRequestMethod("POST");
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
			out.print(contentStr);
			// flush输出流的缓冲
			out.flush();
			conn.connect();
			StringBuilder resXml = new StringBuilder();
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
			Element resRoot = resDoc.getRootElement();

			Namespace resOws = resRoot.getNamespaceForPrefix("ows");
			Namespace resWcs = resRoot.getNamespaceForPrefix("wcs");
			Namespace resLink = resRoot.getNamespaceForPrefix("xlink");
			QName linkQ = QName.get("Reference", resOws);
			QName covQ = QName.get("Coverage", resWcs);
			QName linkArrQ = QName.get("href", resLink);

			String link = resRoot.element(covQ).element(linkQ)
					.attributeValue(linkArrQ);

			mulDownload(link, resFile, 3);
			flag = true;
		} catch (MalformedURLException e) {
			flag = false;
			e.printStackTrace();
		} catch (IOException e) {
			flag = false;
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
		return flag;
	}

	public static String getWCSMapPostLink(String url, String cov, String format, String bbox) {
		boolean flag = false;
		String link="";
		String xml = getDes(url, cov, "1.1.1");

		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		Element root = doc.getRootElement();

		// base crs info
		Namespace nsWcs = root.getNamespaceForPrefix("wcs");
		QName crsQ = QName.get("GridBaseCRS", nsWcs);
		QName gribQ = QName.get("GridType", nsWcs);
		QName offsetQ = QName.get("GridOffsets", nsWcs);
		QName gcsQ = QName.get("GridCS", nsWcs);

		Element spatialParent = root
				.element(QName.get("CoverageDescription", nsWcs))
				.element(QName.get("Domain", nsWcs))
				.element(QName.get("SpatialDomain", nsWcs));
		Element baseParent = spatialParent.element(QName.get("GridCRS", nsWcs));
		String crs = baseParent.elementText(crsQ);
		String gridType = baseParent.elementText(gribQ);
		String offsets = baseParent.elementText(offsetQ);
		String gridCS = baseParent.elementText(gcsQ);

		// original lonlat
		Namespace nsOws = root.getNamespaceForPrefix("ows");
		QName corQ = QName.get("BoundingBox", nsOws);
		List<Element> corArr = spatialParent.elements(corQ);
		Element cornEle = null;
		for (Element temp : corArr) {
			if (crs.equals(temp.attributeValue("crs"))) {
				cornEle = temp;
				break;
			}
		}

		String lowCorn = "";
		String upCorn = "";
		List<Element> cornArr = cornEle.elements();
		for (Element temp : cornArr) {
			if (temp.getName().endsWith("LowerCorner")) {
				lowCorn = temp.getText();
			} else if (temp.getName().endsWith("UpperCorner")) {
				upCorn = temp.getText();
			}
		}
		
		String lowX = lowCorn.substring(0, lowCorn.indexOf(" "));
		String lowY = lowCorn.substring(lowCorn.indexOf(" ")+1);
		String upX = upCorn.substring(0,upCorn.indexOf(" "));
		String upY = upCorn.substring(upCorn.indexOf(" ")+1);
		
		double lX = Double.parseDouble(lowX);
		double lY = Double.parseDouble(lowY);
		double uX = Double.parseDouble(upX);
		double uY = Double.parseDouble(upY);
		
		String ori = lowX+" "+ upY;

		// bbox
		if (!bbox.equalsIgnoreCase("all")) {
			String[] temp = bbox.split(" ");
			if (temp.length == 4) {
				double minX = Double.parseDouble(temp[0]);
				double minY = Double.parseDouble(temp[1]);
				double maxX = Double.parseDouble(temp[2]);
				double maxY = Double.parseDouble(temp[3]);
				if((minX>uX)||(maxX<lX)||(minY>uY)||(maxY<lY)){
					return "failed";
				}
				
				if(minX < lX){
					temp[0] = lowX;
				}
				if(minY < lY){
					temp[1] = lowY;
				}
				if(maxX > uX){
					temp[2] = upX;
				}
				if(maxY > uY){
					temp[3] = upY;
				}
				lowCorn = temp[0] + " " + temp[1];
				upCorn = temp[2] + " " + temp[3];
			}
		}

		String contentStr = getWCSPost(cov, crs, "image/tiff", lowCorn, upCorn,
				gridType, ori, offsets, gridCS);

		URL u = null;
		HttpURLConnection conn = null;
		PrintWriter out = null;
		BufferedReader in = null;
		try {
			u = new URL(url);
			conn = (HttpURLConnection) u.openConnection();
			conn.setRequestMethod("POST");
			// conn.setConnectTimeout(100000);
			// conn.setReadTimeout(300000);
			// conn.setDefaultUseCaches(false);
			// conn.setUseCaches(false);
			// conn.setRequestMethod("POST");
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
			out.print(contentStr);
			// flush输出流的缓冲
			out.flush();
			conn.connect();
			StringBuilder resXml = new StringBuilder();
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
			Element resRoot = resDoc.getRootElement();

			Namespace resOws = resRoot.getNamespaceForPrefix("ows");
			Namespace resWcs = resRoot.getNamespaceForPrefix("wcs");
			Namespace resLink = resRoot.getNamespaceForPrefix("xlink");
			QName linkQ = QName.get("Reference", resOws);
			QName covQ = QName.get("Coverage", resWcs);
			QName linkArrQ = QName.get("href", resLink);

		    link = resRoot.element(covQ).element(linkQ)
					.attributeValue(linkArrQ);
			flag = true;
		} catch (MalformedURLException e) {
			flag = false;
			e.printStackTrace();
		} catch (IOException e) {
			flag = false;
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
		return link;
	}

	public static boolean getWCSMap(String url, String cov, String format,
			File resFile) {
		boolean flag = true;
		String xml = getDes(url, cov, "1.0.0");
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		Element root = doc.getRootElement();
		Namespace nsWcs = root.getNamespaceForPrefix("wcs");
		Namespace nsGml = root.getNamespaceForPrefix("gml");

		QName covOffQ = QName.get("CoverageOffering", nsWcs);
		QName domainQ = QName.get("nsWcs", nsWcs);
		QName spatilQ = QName.get("spatialDomain", nsWcs);
		QName envQ = QName.get("Envelope", nsGml);

		// srs
		Element spatilEle = root.element(covOffQ).element(domainQ)
				.element(spatilQ);
		Element envelopeEle = spatilEle.element(envQ);
		String srs = envelopeEle.attributeValue("srsName");

		// bbox
		List<Element> posArr = envelopeEle.elements();
		String[] bboxStr = new String[2];
		if (posArr.size() == 2) {
			bboxStr[0] = posArr.get(0).getText().replaceAll(" ", ",");
			bboxStr[1] = posArr.get(1).getText().replaceAll(" ", ",");
		}

		// wh
		QName rectQ = QName.get("RectifiedGrid ", nsGml);
		QName limitsQ = QName.get("limits", nsGml);
		QName gridEnvQ = QName.get("GridEnvelope", nsGml);
		Element gridEnvEle = spatilEle.element(rectQ).element(limitsQ)
				.element(gridEnvQ);
		String[] lowSep = gridEnvEle.elementText(QName.get("low", nsGml))
				.split(" ");
		String[] highSep = gridEnvEle.elementText(QName.get("high", nsGml))
				.split(" ");
		int width = Integer.parseInt(highSep[0]) - Integer.parseInt(lowSep[0]);
		int height = Integer.parseInt(highSep[1]) - Integer.parseInt(lowSep[1]);

		StringBuilder wcs = new StringBuilder();
		wcs.append(url);
		if (!url.endsWith("?"))
			wcs.append("?");
		wcs.append("service=wcs&version=1.0.0&request=getcoverage&sourcecoverage=");
		wcs.append(cov);
		wcs.append("&bbox=");
		wcs.append(bboxStr[0]);
		wcs.append(",");
		wcs.append(bboxStr[1]);
		wcs.append("&width=");
		wcs.append(width);
		wcs.append("&height=");
		wcs.append(height);
		wcs.append("&format=");
		wcs.append(format);
		wcs.append("&crs=");
		wcs.append(srs);
		URL u = null;
		OutputStream os = null;
		InputStream inp = null;
		HttpURLConnection conn = null;
		try {
			u = new URL(wcs.toString());
			conn = (HttpURLConnection) u.openConnection();
			conn.setRequestProperty("Accept", "*/*");
			// conn.setRequestProperty("connection", "Keep-Alive");
			conn.setConnectTimeout(100000);
			conn.setReadTimeout(300000);
			conn.setDefaultUseCaches(false);
			conn.setUseCaches(false);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.3; WOW64; rv:31.0) Gecko/20100101 Firefox/31.0");

			if (200 == conn.getResponseCode()) {
				inp = conn.getInputStream();
				byte[] bs = new byte[8192];
				if (!resFile.exists()) {
					resFile.createNewFile();
				}
				os = new FileOutputStream(resFile);
				int len;
				// 写入文件
				while ((len = inp.read(bs)) != -1) {
					os.write(bs, 0, len);
				}
			}
		} catch (MalformedURLException e) {
			flag = false;
			e.printStackTrace();
		} catch (IOException e) {
			flag = false;
			e.printStackTrace();
		} finally {
			try {
				if (os != null)
					os.close();
				if (inp != null)
					inp.close();
				if (conn != null)
					conn.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	public static boolean getCovMap(String url, File resFile) {
		boolean flag = true;
		URL u = null;
		OutputStream os = null;
		InputStream inp = null;
		try {
			u = new URL(url);
			URLConnection conn = u.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setConnectTimeout(24 * 3600 * 1000);
			conn.setRequestProperty("user-agent",
					"Mozilla/5.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.connect();

			inp = conn.getInputStream();
			byte[] bs = new byte[2048];
			if (!resFile.exists()) {
				resFile.createNewFile();
			}
			os = new FileOutputStream(resFile);
			int len;
			// 写入文件
			while ((len = inp.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
		} catch (MalformedURLException e) {
			flag = false;
			e.printStackTrace();
		} catch (IOException e) {
			flag = false;
			e.printStackTrace();
		} finally {
			try {
				if (os != null)
					os.close();
				if (inp != null)
					inp.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	public static int getBandCount(String url, String cov) {
		String xml = getDes(url, cov, "1.0.0");
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		Element root = doc.getRootElement();
		Namespace nsWcs = root.getNamespaceForPrefix("wcs");

		QName covOffQ = QName.get("CoverageOffering", nsWcs);
		QName rangeQ = QName.get("rangeSet", nsWcs);
		QName sRangeQ = QName.get("RangeSet", nsWcs);
		QName axisQ = QName.get("axisDescription", nsWcs);
		QName sAxisQ = QName.get("AxisDescription", nsWcs);
		QName valuesQ = QName.get("values", nsWcs);
		QName intervalQ = QName.get("interval", nsWcs);

		Element intervalEle = root.element(covOffQ).element(rangeQ)
				.element(sRangeQ).element(axisQ).element(sAxisQ)
				.element(valuesQ).element(intervalQ);

		String min = intervalEle.elementText(QName.get("min", nsWcs));
		String max = intervalEle.elementText(QName.get("max", nsWcs));

		return Integer.parseInt(max) - Integer.parseInt(min) + 1;
	}

	public static String getWCSSRS(String url, String cov) {
		String xml = getDes(url, cov, "1.0.0");
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		Element root = doc.getRootElement();
		Namespace nsWcs = root.getNamespaceForPrefix("wcs");
		Namespace nsGml = root.getNamespaceForPrefix("gml");

		QName covOffQ = QName.get("CoverageOffering", nsWcs);
		QName domainQ = QName.get("nsWcs", nsWcs);
		QName spatilQ = QName.get("spatialDomain", nsWcs);
		QName envQ = QName.get("Envelope", nsGml);

		return root.element(covOffQ).element(domainQ).element(spatilQ)
				.element(envQ).attributeValue("srsName");
	}

	public static int getBandCount(String fullUrl) {
		String iden = "";
		String pix = "sourceCoverage=";
		int len = pix.length();
		int fIndex = fullUrl.indexOf("?");
		String url = fullUrl.substring(0, fIndex);
		String temp = fullUrl.substring(fIndex + 1, fIndex + len + 1);
		if (!temp.equalsIgnoreCase(pix)) {
			while (fIndex >= 0) {
				fIndex = fullUrl.indexOf("&", fIndex + 1);
				temp = fullUrl.substring(fIndex + 1, fIndex + len + 1);
				if (temp.equalsIgnoreCase(pix))
					break;
			}
		}
		int lIndex = fullUrl.indexOf("&", fIndex + 1);
		if (lIndex == -1) {
			iden = fullUrl.substring(fIndex + len + 1);
		} else {
			iden = fullUrl.substring(fIndex + len + 1, lIndex);
		}
		return getBandCount(url, iden);
	}

	private static String getDes(String url, String cov, String version) {
		String res = "";
		InputStream is = null;
		try {

			StringBuilder req = new StringBuilder();
			req.append(url);
			if (!url.endsWith("?"))
				req.append("?");
			req.append("VERSION=");
			req.append(version);
			// if (serverType.equals("arcgisserver")) {
			if (version.equals("1.1.1")) {
				req.append("&SERVICE=WCS&REQUEST=DESCRIBECOVERAGE&identifiers=");
			} else {
				req.append("&SERVICE=WCS&REQUEST=DESCRIBECOVERAGE&coverage=");
			}
			// }
			// else {
			// req.append("&SERVICE=WCS&REQUEST=DESCRIBECOVERAGE&coverage=");
			// }
			req.append(cov);
			//System.out.print(req.toString());
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

	public static long getPixCount(String fullUrl) {
		String iden = "";
		String pix = "sourceCoverage=";
		int len = pix.length();
		int fIndex = fullUrl.indexOf("?");
		String url = fullUrl.substring(0, fIndex);
		String temp = fullUrl.substring(fIndex + 1, fIndex + len + 1);
		if (!temp.equalsIgnoreCase(pix)) {
			while (fIndex >= 0) {
				fIndex = fullUrl.indexOf("&", fIndex + 1);
				temp = fullUrl.substring(fIndex + 1, fIndex + len + 1);
				if (temp.equalsIgnoreCase(pix))
					break;
			}
		}
		int lIndex = fullUrl.indexOf("&", fIndex + 1);
		if (lIndex == -1) {
			iden = fullUrl.substring(fIndex + len + 1);
		} else {
			iden = fullUrl.substring(fIndex + len + 1, lIndex);
		}
		return getPixCount(url, iden);
	}

	public static long getPixCount(String url, String cov) {
		String xml = getDes(url, cov, "1.0.0");
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		Element root = doc.getRootElement();
		Namespace nsWcs = root.getNamespaceForPrefix("wcs");
		Namespace nsGml = root.getNamespaceForPrefix("gml");

		QName covOffQ = QName.get("CoverageOffering", nsWcs);
		QName domainQ = QName.get("domainSet", nsWcs);
		QName spatilQ = QName.get("spatialDomain", nsWcs);

		Element spatilEle = root.element(covOffQ).element(domainQ)
				.element(spatilQ);

		// wh
		QName rectQ = QName.get("RectifiedGrid", nsGml);
		QName limitsQ = QName.get("limits", nsGml);
		QName gridEnvQ = QName.get("GridEnvelope", nsGml);

		Element gridEnvEle = spatilEle.element(rectQ).element(limitsQ)
				.element(gridEnvQ);
		String[] lowSep = gridEnvEle.elementText(QName.get("low", nsGml))
				.split(" ");
		String[] highSep = gridEnvEle.elementText(QName.get("high", nsGml))
				.split(" ");
		int width = Integer.parseInt(highSep[0]) - Integer.parseInt(lowSep[0]);
		int height = Integer.parseInt(highSep[1]) - Integer.parseInt(lowSep[1]);

		QName rangeQ = QName.get("rangeSet", nsWcs);
		QName sRangeQ = QName.get("RangeSet", nsWcs);
		QName axisQ = QName.get("axisDescription", nsWcs);
		QName sAxisQ = QName.get("AxisDescription", nsWcs);
		QName valuesQ = QName.get("values", nsWcs);
		QName intervalQ = QName.get("interval", nsWcs);

		Element intervalEle = root.element(covOffQ).element(rangeQ)
				.element(sRangeQ).element(axisQ).element(sAxisQ)
				.element(valuesQ).element(intervalQ);

		String min = intervalEle.elementText(QName.get("min", nsWcs));
		String max = intervalEle.elementText(QName.get("max", nsWcs));

		int band = 1;
		if (min != null && max != null) {
			band = Integer.parseInt(max) - Integer.parseInt(min) + 1;
		}

		return (long) width * height * band * 10;
	}

	public static long getFeatureCount(String url, String layer) {
		return 1;
	}

	public static String getFeatureSRS(String url, String layerName) {

		Document doc = getFeatureCapabilities(url, layerName);
		if (doc != null) {
			Element root = doc.getRootElement();

			List<Element> feaArr = root.element("FeatureTypeList").elements();
			for (Element temp : feaArr) {
				if (layerName.equals(temp.elementText("Name"))) {
					String srsStr = temp.elementText("DefaultSRS");
					return "EPSG" + srsStr.substring(srsStr.lastIndexOf(":"));
				}
			}
		}
		return "";
	}

	public static String getFeatureBBox(String url, String layerName) {
		Document doc = getFeatureCapabilities(url, layerName);
		if (doc != null) {
			Element root = doc.getRootElement();

			List<Element> feaArr = root.element("FeatureTypeList").elements();
			Namespace nsOws = root.getNamespaceForPrefix("ows");
			QName qLow = QName.get("LowerCorner", nsOws);
			QName qUp = QName.get("UpperCorner", nsOws);
			QName q84 = QName.get("WGS84BoundingBox", nsOws);
			for (Element temp : feaArr) {
				if (layerName.equals(temp.elementText("Name"))) {
					String srsStr = temp.elementText("DefaultSRS");
					Element e84 = temp.element(q84);
					String lowStr = e84.elementText(qLow);
					String upStr = e84.elementText(qUp);
					double[] dLow = new double[2];
					int index = lowStr.indexOf(" ");
					dLow[0] = Double.parseDouble(lowStr.substring(0, index));
					dLow[1] = Double.parseDouble(lowStr.substring(index + 1));
					double[] dUp = new double[2];
					index = upStr.indexOf(" ");
					dUp[0] = Double.parseDouble(upStr.substring(0, index));
					dUp[1] = Double.parseDouble(upStr.substring(index + 1));

					if (!srsStr.equals("4326")) {
						CRSFactory cRSFactory = new CRSFactory();
						try {

							String wgs84Str = "GEOGCS[\"WGS 84\" \n,"
									+ "DATUM[\"World Geodetic System 1984\", \n"
									+ "SPHEROID[\"WGS 84\", 6378137.0, 298.257223563, AUTHORITY[\"EPSG\",\"7030\"]], \n"
									+ "AUTHORITY[\"EPSG\",\"6326\"]], \n"
									+ "PRIMEM[\"Greenwich\", 0.0, AUTHORITY[\"EPSG\",\"8901\"]], \n"
									+ "UNIT[\"degree\", 0.017453292519943295], \n"
									+ "AXIS[\"Geodetic longitude\", EAST], \n"
									+ "AXIS[\"Geodetic latitude\", NORTH], \n"
									+ "AUTHORITY[\"EPSG\",\"4326\"]]\n";
							String crs32649Str = "PROJCS[\"WGS 84 / UTM zone 49N\", \n"
									+ "GEOGCS[\"WGS 84\", \n"
									+ "DATUM[\"World Geodetic System 1984\", \n"
									+ "SPHEROID[\"WGS 84\", 6378137.0, 298.257223563, AUTHORITY[\"EPSG\",\"7030\"]], \n"
									+ "AUTHORITY[\"EPSG\",\"6326\"]], \n"
									+ "PRIMEM[\"Greenwich\", 0.0, AUTHORITY[\"EPSG\",\"8901\"]], \n"
									+ "UNIT[\"degree\", 0.017453292519943295], \n"
									+ "AXIS[\"Geodetic longitude\", EAST], \n"
									+ "AXIS[\"Geodetic latitude\", NORTH], \n"
									+ "AUTHORITY[\"EPSG\",\"4326\"]], \n"
									+ "PROJECTION[\"Transverse_Mercator\", AUTHORITY[\"EPSG\",\"9807\"]], \n"
									+ "PARAMETER[\"central_meridian\", 111.0], \n"
									+ "PARAMETER[\"latitude_of_origin\", 0.0], \n"
									+ "PARAMETER[\"scale_factor\", 0.9996], \n"
									+ "PARAMETER[\"false_easting\", 500000.0], \n"
									+ "PARAMETER[\"false_northing\", 0.0], \n"
									+ "UNIT[\"m\", 1.0], \n"
									+ "AXIS[\"Easting\", EAST], \n"
									+ "AXIS[\"Northing\", NORTH], \n"
									+ "AUTHORITY[\"EPSG\",\"32649\"]]";

							CoordinateReferenceSystem wgs84 = cRSFactory
									.createFromPrj(wgs84Str);
							CoordinateReferenceSystem desCrs = cRSFactory
									.createFromPrj(crs32649Str);
							List<CoordinateOperation> coordOps = CoordinateOperationFactory
									.createCoordinateOperations(
											(GeodeticCRS) wgs84,
											(GeodeticCRS) desCrs);
							if (coordOps.size() > 0) {
								for (CoordinateOperation op : coordOps) {
									double[] tempResLow = op.transform(dLow);
									double[] tempResUp = op.transform(dUp);
									return String.valueOf(tempResLow[0]) + " "
											+ String.valueOf(tempResLow[1])
											+ " "
											+ String.valueOf(tempResUp[0])
											+ " "
											+ String.valueOf(tempResUp[1]);
								}
							}
						} catch (CRSException e) {
							e.printStackTrace();
						} catch (IllegalCoordinateException e) {
							e.printStackTrace();
						}
					}

					return lowStr + " " + upStr;
				}
			}
		}
		return "";
	}

	private static Document getFeatureCapabilities(String url, String typeName) {
		StringBuilder req = new StringBuilder();
		req.append(url);
		if (!url.endsWith("?"))
			req.append("?");
		req.append("service=wfs&request=getcapabilities&version=1.1.0");
		String res = "";
		InputStream ist = null;
		try {
			URL u = new URL(req.toString());
			URLConnection conn = u.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/5.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.connect();
			ist = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(ist));
			String line = "";
			for (line = br.readLine(); line != null; line = br.readLine()) {
				res += line;
			}
		} catch (IOException ex) {
			return null;
		} finally {
			if (ist != null) {
				try {
					ist.close();
				} catch (IOException ee) {
					return null;
				}
			}
		}

		Document doc = null;
		try {
			doc = DocumentHelper.parseText(res);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		return doc;
	}

	public static boolean getShapeFile(String url, String layerName,
			String resFilePath) {
		StringBuilder sb = new StringBuilder();
		if (!url.endsWith("?"))
			url += "?";
		sb.append(url);
		sb.append("service=WFS&version=1.0.0&request=GetFeature&typeName=");
		sb.append(layerName);
		sb.append("&outputFormat=SHAPE-ZIP");

		boolean flag = true;
		URL u = null;
		OutputStream os = null;
		InputStream inp = null;
		HttpURLConnection conn = null;
		File resFile = new File(resFilePath + "/sh.zip");
		try {
			u = new URL(sb.toString());
			conn = (HttpURLConnection) u.openConnection();
			conn.setRequestProperty("Accept", "*/*");
			// conn.setRequestProperty("connection", "Keep-Alive");
			conn.setConnectTimeout(100000);
			conn.setReadTimeout(300000);
			conn.setDefaultUseCaches(false);
			conn.setUseCaches(false);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.3; WOW64; rv:31.0) Gecko/20100101 Firefox/31.0");

			if (200 == conn.getResponseCode()) {
				inp = conn.getInputStream();
				byte[] bs = new byte[8192];

				if (!resFile.exists()) {
					resFile.createNewFile();
				}
				os = new FileOutputStream(resFile);
				int len;
				// 写入文件
				while ((len = inp.read(bs)) != -1) {
					os.write(bs, 0, len);
				}
			}
		} catch (MalformedURLException e) {
			flag = false;
			e.printStackTrace();
		} catch (IOException e) {
			flag = false;
			e.printStackTrace();
		} finally {
			try {
				if (os != null)
					os.close();
				if (inp != null)
					inp.close();
				if (conn != null)
					conn.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// (new File(resFilePath + "\\")).mkdirs();
		ZipInputStream zipInputStream = null;
		ZipEntry zipEntry = null;
		BufferedOutputStream out = null;
		int bsize = 2048;
		try {
			zipInputStream = new ZipInputStream(new BufferedInputStream(
					new FileInputStream(resFile)));
			while ((zipEntry = zipInputStream.getNextEntry()) != null) {

				byte data[] = new byte[bsize];

				String fileName = zipEntry.getName();
				File temp = new File(resFilePath + "\\" + fileName);
				try {
					out = new BufferedOutputStream(new FileOutputStream(temp),
							bsize);
					int len = 0;
					while ((len = zipInputStream.read(data, 0, bsize)) != -1) {
						out.write(data, 0, len);
					}
					out.flush();
				} catch (IOException ee) {
					ee.printStackTrace();
				} finally {
					try {
						if (out != null)
							out.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		} catch (ZipException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
				if (zipInputStream != null)
					zipInputStream.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return flag;

	}

	public static boolean getShapeFile(String url, String resFilePath) {
		boolean flag = true;
		URL u = null;
		OutputStream os = null;
		InputStream inp = null;
		HttpURLConnection conn = null;
		File resFile = new File(resFilePath + "/sh.zip");
		try {
			u = new URL(url);
			conn = (HttpURLConnection) u.openConnection();
			conn.setRequestProperty("Accept", "*/*");
			// conn.setRequestProperty("connection", "Keep-Alive");
			conn.setConnectTimeout(100000);
			conn.setReadTimeout(300000);
			conn.setDefaultUseCaches(false);
			conn.setUseCaches(false);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.3; WOW64; rv:31.0) Gecko/20100101 Firefox/31.0");

			if (200 == conn.getResponseCode()) {
				inp = conn.getInputStream();
				byte[] bs = new byte[8192];

				if (!resFile.exists()) {
					resFile.createNewFile();
				}
				os = new FileOutputStream(resFile);
				int len;
				// 写入文件
				while ((len = inp.read(bs)) != -1) {
					os.write(bs, 0, len);
				}
			}
		} catch (MalformedURLException e) {
			flag = false;
			e.printStackTrace();
		} catch (IOException e) {
			flag = false;
			e.printStackTrace();
		} finally {
			try {
				if (os != null)
					os.close();
				if (inp != null)
					inp.close();
				if (conn != null)
					conn.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// (new File(resFilePath + "\\")).mkdirs();
		ZipInputStream zipInputStream = null;
		ZipEntry zipEntry = null;
		BufferedOutputStream out = null;
		int bsize = 2048;
		try {
			zipInputStream = new ZipInputStream(new BufferedInputStream(
					new FileInputStream(resFile)));
			while ((zipEntry = zipInputStream.getNextEntry()) != null) {

				byte data[] = new byte[bsize];

				String fileName = zipEntry.getName();
				File temp = new File(resFilePath + "\\" + fileName);
				try {
					out = new BufferedOutputStream(new FileOutputStream(temp),
							bsize);
					int len = 0;
					while ((len = zipInputStream.read(data, 0, bsize)) != -1) {
						out.write(data, 0, len);
					}
					out.flush();
				} catch (IOException ee) {
					ee.printStackTrace();
				} finally {
					try {
						if (out != null)
							out.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		} catch (ZipException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
				if (zipInputStream != null)
					zipInputStream.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return flag;
	}

	private static void mulDownload(String urlStr, String resFile,
			int threadCount) {
		URL url = null;
		OutputStream os = null;
		InputStream inp = null;
		HttpURLConnection conn = null;

		try {
			url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			// conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			int code = conn.getResponseCode();
			if (code == 200) {
				// // 服务器端返回的数据的长度，实际上就是文件的长度
				// int length = conn.getContentLength();
				//
				// System.out.println("File length：" + length);
				// if (length < 0) {
				// // 在客户端本地创建出来一个大小跟服务器端一样大小的临时文件
				// RandomAccessFile raf = new RandomAccessFile(resFile, "rwd");
				// // 指定创建的这个文件的长度
				// raf.setLength(length);
				//
				// // 假设是3个线程去下载资源。
				// // 平均每一个线程下载的文件大小.
				// int blockSize = length / threadCount;
				// for (int threadId = 1; threadId <= threadCount; threadId++) {
				// // 第一个线程下载的开始位置
				// int startIndex = (threadId - 1) * blockSize;
				// int endIndex = threadId * blockSize - 1;
				// if (threadId == threadCount) {// 最后一个线程下载的长度要稍微长一点
				// endIndex = length;
				// }
				// System.out.println("线程：" + threadId + "下载:---"
				// + startIndex + "--->" + endIndex);
				// new MulDownloadThread(urlStr, threadId, startIndex,
				// endIndex, raf).start();
				// }
				// // raf.close();
				// } else {

				inp = conn.getInputStream();
				byte[] bs = new byte[2048];

				File newFile = new File(resFile);
				if (!newFile.exists()) {
					newFile.createNewFile();
				}
				os = new FileOutputStream(newFile);
				int len;
				// 写入文件
				while ((len = inp.read(bs)) != -1) {
					os.write(bs, 0, len);
				}

				// }

			} else {
				System.out.printf("服务器错误!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null)
					os.close();
				if (inp != null)
					inp.close();
			} catch (IOException ee) {
				ee.printStackTrace();
			}
		}

	}

	public static boolean getSig(String urlStr, File resFile) {
		URL url = null;
		OutputStream os = null;
		InputStream inp = null;
		HttpURLConnection conn = null;

		try {
			url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			int code = conn.getResponseCode();
			if (code == 200) {

				inp = conn.getInputStream();
				byte[] bs = new byte[2048];
				if (!resFile.exists()) {
					resFile.createNewFile();
				}
				os = new FileOutputStream(resFile);
				int len;
				// 写入文件
				while ((len = inp.read(bs)) != -1) {
					os.write(bs, 0, len);
				}
				return true;
			}
			return false;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null)
					os.close();
				if (inp != null)
					inp.close();
			} catch (IOException ee) {
				ee.printStackTrace();
			}
		}
		return false;
	}
}
