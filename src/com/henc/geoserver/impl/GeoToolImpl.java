package com.henc.geoserver.impl;

import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.GeoServerRESTReader;
import it.geosolutions.geoserver.rest.decoder.RESTCoverage;
import it.geosolutions.geoserver.rest.decoder.RESTCoverageStoreList;
import it.geosolutions.geoserver.rest.decoder.RESTDataStoreList;
import it.geosolutions.geoserver.rest.decoder.RESTLayer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.henc.geoserver.GeoserverOperationResult;
import com.henc.geoserver.GeoserverOperationStatus;
import com.henc.geoserver.GeoTool;

public class GeoToolImpl implements GeoTool {

	private String GEOSERVER_URL;
	private String GEOSERVER_USERNAME;
	private String GEOSERVER_PASSWORD;

	private GeoServerRESTReader reader;
	private GeoServerRESTPublisher publisher;

	public GeoToolImpl(String geoserverUrl, String adminUsername,
			String adminPassword) {
		this.GEOSERVER_URL = geoserverUrl;
		this.GEOSERVER_USERNAME = adminUsername;
		this.GEOSERVER_PASSWORD = adminPassword;
	}

	@Override
	public GeoserverOperationResult publishMap(File map, String workspace,
			String storeName, String coverageName, String format,
			boolean isRepublish) {
		try {
			this.reader = new GeoServerRESTReader(this.GEOSERVER_URL,
					this.GEOSERVER_USERNAME, this.GEOSERVER_PASSWORD);
			this.publisher = new GeoServerRESTPublisher(this.GEOSERVER_URL,
					this.GEOSERVER_USERNAME, this.GEOSERVER_PASSWORD);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return new GeoserverOperationResult(GeoserverOperationStatus.ERROR,
					"MalformedURL");
		}
		if (!reader.existGeoserver()) {
			return new GeoserverOperationResult(GeoserverOperationStatus.ERROR,
					"GeoServer do not exist");
		}
		List<String> lsW = reader.getWorkspaceNames();
		List<String> lsN = reader.getNamespaceNames();
		if (!(lsW.contains(workspace) && lsN.contains(workspace))) {
			publisher.createWorkspace(workspace);
		}
		RESTCoverageStoreList lsStore = reader.getCoverageStores(workspace);
		// if store exist and need republish
		if (lsStore.getNames().contains(storeName) && isRepublish) {
			if (!publisher.removeCoverageStore(workspace, storeName, true)) {
				return new GeoserverOperationResult(
						GeoserverOperationStatus.REMOVE_FAILD,
						"Republish remove failed");
			}
		}
		// if need to publish
		if (!lsStore.getNames().contains(storeName) || isRepublish) {
			try {
				if (!publisher.publishGeoTIFF(workspace, storeName,
						coverageName, map)) {
					return new GeoserverOperationResult(
							GeoserverOperationStatus.REPUBLISH_FAILD,
							"Publish unknow error");
				}
			} catch (FileNotFoundException e) {
				return new GeoserverOperationResult(
						GeoserverOperationStatus.ERROR,
						"Republish file not found");
			} catch (IllegalArgumentException e) {
				return new GeoserverOperationResult(
						GeoserverOperationStatus.ERROR,
						"Republish illegal argument");
			}
		}

		// get desUrl and wcsUrl
		RESTCoverage cov = reader.getCoverage(workspace, storeName,
				coverageName);
		String desUrl = this.GEOSERVER_URL
				+ "/wcs?SERVICE=WCS&VERSION=1.0.0&REQUEST=DESCRIBECOVERAGE&COVERAGE="
				+ cov.getNameSpace() + ":" + cov.getName();
		String wcsUrl = "";
		try {
			wcsUrl = getResUrl(desUrl, cov, format);
		} catch (IOException e) {
			return new GeoserverOperationResult(GeoserverOperationStatus.ERROR,
					"Get res IOException");
		} catch (ParserConfigurationException e) {
			return new GeoserverOperationResult(GeoserverOperationStatus.ERROR,
					"Get res ParserConfigurationException");
		} catch (SAXException e) {
			return new GeoserverOperationResult(GeoserverOperationStatus.ERROR,
					"Get res SAXException");
		}
		if (isRepublish) {
			return new GeoserverOperationResult(
					GeoserverOperationStatus.REPUBLISH_SUCCEED, desUrl, wcsUrl);
		} else {
			return new GeoserverOperationResult(
					GeoserverOperationStatus.MAP_EXIST, desUrl, wcsUrl);
		}

	}

	@Override
	public GeoserverOperationResult disPublishMap(String workspace,
			String storeName, String coverageName, boolean remove) {
		try {
			this.reader = new GeoServerRESTReader(this.GEOSERVER_URL,
					this.GEOSERVER_USERNAME, this.GEOSERVER_PASSWORD);
			this.publisher = new GeoServerRESTPublisher(this.GEOSERVER_URL,
					this.GEOSERVER_USERNAME, this.GEOSERVER_PASSWORD);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return new GeoserverOperationResult(GeoserverOperationStatus.ERROR,
					"MalformedURL");
		}
		if (!reader.existGeoserver()) {
			return new GeoserverOperationResult(GeoserverOperationStatus.ERROR,
					"GeoServer do not exist");
		}
		List<String> lsW = reader.getWorkspaceNames();
		if (!lsW.contains(workspace)) {
			return new GeoserverOperationResult(GeoserverOperationStatus.ERROR,
					"Workspace do not exist");
		}
		RESTCoverageStoreList lsStore = reader.getCoverageStores(workspace);
		if (!lsStore.getNames().contains(storeName)) {
			return new GeoserverOperationResult(GeoserverOperationStatus.ERROR,
					"Store do not exist");
		}
		RESTCoverage cov = reader.getCoverage(workspace, storeName,
				coverageName);
		if (cov == null) {
			return new GeoserverOperationResult(GeoserverOperationStatus.ERROR,
					"Coverage do not exist");
		}
		boolean pbFlag = publisher.unpublishCoverage(workspace, storeName,
				coverageName);
		if (!remove && pbFlag) {
			return new GeoserverOperationResult(
					GeoserverOperationStatus.UNPUBLISH_SUCCEED, "");
		} else if (!remove && !pbFlag) {
			return new GeoserverOperationResult(
					GeoserverOperationStatus.UNPUBLISH_FAILED, "");
		}
		boolean rmFlag = publisher.removeCoverageStore(workspace, storeName,
				true);
		if (rmFlag) {
			return new GeoserverOperationResult(
					GeoserverOperationStatus.REMOVE_SUCCEED, "");
		} else {
			return new GeoserverOperationResult(
					GeoserverOperationStatus.REPUBLISH_FAILD, "");
		}

	}

	@Override
	public GeoserverOperationResult publishStyle(File style, String styleName,
			boolean isRepublish) {
		try {
			this.reader = new GeoServerRESTReader(this.GEOSERVER_URL,
					this.GEOSERVER_USERNAME, this.GEOSERVER_PASSWORD);
			this.publisher = new GeoServerRESTPublisher(this.GEOSERVER_URL,
					this.GEOSERVER_USERNAME, this.GEOSERVER_PASSWORD);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return new GeoserverOperationResult(GeoserverOperationStatus.ERROR,
					"MalformedURL");
		}
		if (!reader.existGeoserver()) {
			return new GeoserverOperationResult(GeoserverOperationStatus.ERROR,
					"GeoServer do not exist");
		}
		if (reader.existsStyle(styleName)) {
			if (isRepublish) {
				if (publisher.updateStyle(style, styleName)) {
					return new GeoserverOperationResult(
							GeoserverOperationStatus.UPDATESTYLE_SUCCED, "");
				} else {
					return new GeoserverOperationResult(
							GeoserverOperationStatus.UPDATESTYLE_FAILED, "");
				}
			} else {
				return new GeoserverOperationResult(
						GeoserverOperationStatus.STYLE_EXIST, "");
			}
		} else {
			if (publisher.publishStyle(style, styleName)) {
				return new GeoserverOperationResult(
						GeoserverOperationStatus.PUBLISHSTYLE_SUCCEED, "");
			} else {
				return new GeoserverOperationResult(
						GeoserverOperationStatus.PUBLISHSTYLE_FAILED, "");
			}
		}
	}

	@Override
	public GeoserverOperationResult disPublishStyle(String styleName) {
		try {
			this.reader = new GeoServerRESTReader(this.GEOSERVER_URL,
					this.GEOSERVER_USERNAME, this.GEOSERVER_PASSWORD);
			this.publisher = new GeoServerRESTPublisher(this.GEOSERVER_URL,
					this.GEOSERVER_USERNAME, this.GEOSERVER_PASSWORD);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return new GeoserverOperationResult(GeoserverOperationStatus.ERROR,
					"MalformedURL");
		}
		if (!reader.existGeoserver()) {
			return new GeoserverOperationResult(GeoserverOperationStatus.ERROR,
					"GeoServer do not exist");
		}
		if (reader.existsStyle(styleName)) {
			if (publisher.removeStyle(styleName, true)) {
				return new GeoserverOperationResult(
						GeoserverOperationStatus.REMOVESTYLE_SUCCEED, "");
			} else {
				return new GeoserverOperationResult(
						GeoserverOperationStatus.REMOVESTYLE_FAILED, "");
			}
		} else {
			return new GeoserverOperationResult(
					GeoserverOperationStatus.REMOVESTYLE_SUCCEED, "");
		}
	}

	private String getResUrl(String desUrl, RESTCoverage cov, String wcsformat)
			throws IOException, ParserConfigurationException, SAXException {
		URL url = new URL(desUrl);
		URLConnection conn = url.openConnection();
		conn.setRequestProperty("accept", "*/*");
		conn.setRequestProperty("connection", "Keep-Alive");
		conn.setRequestProperty("user-agent",
				"Mozilla/5.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
		conn.connect();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		String xmlStr = "";
		String line = "";
		for (line = br.readLine(); line != null; line = br.readLine()) {
			xmlStr += line;
		}
		// convert to xml
		DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		StringReader inStream = new StringReader(xmlStr);
		InputSource inSource = new InputSource(inStream);
		Document doc = documentBuilder.parse(inSource);
		// get layer info
		Node posL = doc.getElementsByTagName("gml:Envelope").item(0);

		String srs = posL.getAttributes().getNamedItem("srsName")
				.getNodeValue(); // map srs

		NodeList ppp = posL.getChildNodes();

		String bbox = ""; // BBOX String

		for (int i = 0; i < ppp.getLength(); i++) {
			if (ppp.item(i).getNodeType() == 1) {
				String[] temp = ppp.item(i).getFirstChild().getNodeValue()
						.split(" ");
				bbox += temp[0];
				bbox += ",";
				bbox += temp[1];
				if (i == 0)
					bbox += ",";
			}
		}

		Node gridN = doc.getElementsByTagName("gml:GridEnvelope").item(0);
		NodeList gridL = gridN.getChildNodes();

		int[] wh = { 0, 0 }; // width height

		String tempStr = "";
		for (int i = 0; i < gridL.getLength(); i++) {
			if (gridL.item(i).getNodeType() == 1) {
				tempStr += gridL.item(i).getFirstChild().getNodeValue();
				tempStr += " ";
			}
		}
		String[] tempArr = tempStr.split(" ");
		wh[0] = Math.abs(Integer.parseInt(tempArr[2])
				- Integer.parseInt(tempArr[0]));
		wh[1] = Math.abs(Integer.parseInt(tempArr[3])
				- Integer.parseInt(tempArr[1]));

		// generate wcs request url
		StringBuilder wcs = new StringBuilder();
		wcs.append(this.GEOSERVER_URL);
		wcs.append("/wcs?service=wcs&version=1.0.0&request=getcoverage&sourcecoverage=");
		wcs.append(cov.getNameSpace());
		wcs.append(":");
		wcs.append(cov.getName());
		wcs.append("&bbox=");
		wcs.append(bbox);
		wcs.append("&width=");
		wcs.append(wh[0]);
		wcs.append("&height=");
		wcs.append(wh[1]);
		wcs.append("&format=");
		wcs.append(wcsformat);
		wcs.append("&crs=");
		wcs.append(srs);
		return wcs.toString();
	}

	public GeoserverOperationResult publishShapeFile(File shpPath,
			String fileName, String workspace, String storeName,
			String layerName, String srs, boolean isRepublish) {

		File outFilePath = new File(shpPath.getAbsolutePath() + "/zip");
		if (!outFilePath.exists())
			outFilePath.mkdirs();
		File outFile = new File(outFilePath.getAbsolutePath() + "/" + layerName
				+ ".zip");
		if (!outFile.exists()) {
			try {
				outFile.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		ZipOutputStream zos = null;
		try {
			zos = new ZipOutputStream(new FileOutputStream(outFile));

			File[] infiles = shpPath.listFiles();
			for (File infile : infiles) {
				if (infile.isFile()) {
					String entryname = infile.getName();
					ZipEntry entry = new ZipEntry(entryname);

					InputStream is = null;
					try {
						zos.putNextEntry(entry);
						is = new FileInputStream(infile);
						int len = 0;
						while ((len = is.read()) != -1)
							zos.write(len);
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						try {
							if (is != null)
								is.close();
						} catch (IOException ee) {
							ee.printStackTrace();
						}
					}

				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (zos != null)
					zos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			this.reader = new GeoServerRESTReader(this.GEOSERVER_URL,
					this.GEOSERVER_USERNAME, this.GEOSERVER_PASSWORD);
			this.publisher = new GeoServerRESTPublisher(this.GEOSERVER_URL,
					this.GEOSERVER_USERNAME, this.GEOSERVER_PASSWORD);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return new GeoserverOperationResult(GeoserverOperationStatus.ERROR,
					"MalformedURL");
		}
		if (!reader.existGeoserver()) {
			return new GeoserverOperationResult(GeoserverOperationStatus.ERROR,
					"GeoServer do not exist");
		}

		List<String> lsW = reader.getWorkspaceNames();
		List<String> lsN = reader.getNamespaceNames();
		if (!(lsW.contains(workspace) && lsN.contains(workspace))) {
			publisher.createWorkspace(workspace);
		}
		try {
			if (!publisher.publishShp(workspace, storeName, layerName, outFile,
					srs)) {
				return new GeoserverOperationResult(
						GeoserverOperationStatus.REPUBLISH_FAILD,
						"Publish unknow error");
			}
		} catch (FileNotFoundException e) {
			return new GeoserverOperationResult(GeoserverOperationStatus.ERROR,
					"Republish file not found");
		} catch (IllegalArgumentException e) {
			return new GeoserverOperationResult(GeoserverOperationStatus.ERROR,
					"Republish illegal argument");
		}

		RESTLayer layer = reader.getLayer(workspace, layerName);
		if (null != layer) {
			String desUrl = this.GEOSERVER_URL+"/wfs?service=wfs&request=describefeaturetype&version=1.1.1&typename="
					+ workspace + ":" + layerName;
			String dataUrl = this.GEOSERVER_URL+"/wfs?service=wfs&request=GetFeature&typeName="
					+ workspace + ":" + layerName;
			return new GeoserverOperationResult(
					GeoserverOperationStatus.PUBLISH_SUCCEED, desUrl, dataUrl);
		}
		return null;
	}

	@Override
	public GeoserverOperationResult disPublishShapeFile(String workspace,
			String storeName,String layerName,boolean remove ) {
		try {
			this.reader = new GeoServerRESTReader(this.GEOSERVER_URL,
					this.GEOSERVER_USERNAME, this.GEOSERVER_PASSWORD);
			this.publisher = new GeoServerRESTPublisher(this.GEOSERVER_URL,
					this.GEOSERVER_USERNAME, this.GEOSERVER_PASSWORD);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return new GeoserverOperationResult(GeoserverOperationStatus.ERROR,
					"MalformedURL");
		}
		if (!reader.existGeoserver()) {
			return new GeoserverOperationResult(GeoserverOperationStatus.ERROR,
					"GeoServer do not exist");
		}
		List<String> lsW = reader.getWorkspaceNames();
		if (!lsW.contains(workspace)) {
			return new GeoserverOperationResult(GeoserverOperationStatus.ERROR,
					"Workspace do not exist");
		}

	
		  
//		查看某一工作区内的数据存储，但是发现矢量数据的数据存储并不显示在其中
		RESTDataStoreList lsStore = reader.getDatastores("test");
//		List<String> namesList = lsStore.getNames();
//		for (String a : namesList) {
//			System.out.println(a);
//		}
		
		if (!lsStore.getNames().contains(storeName)) {
			return new GeoserverOperationResult(GeoserverOperationStatus.ERROR,
					"Store do not exist");
		}
		boolean pbFlag = publisher.unpublishFeatureType(workspace, storeName,
				layerName);
		if (!remove && pbFlag) {
			return new GeoserverOperationResult(
					GeoserverOperationStatus.UNPUBLISH_SUCCEED, "");
		} else if (!remove && !pbFlag) {
			return new GeoserverOperationResult(
					GeoserverOperationStatus.UNPUBLISH_FAILED, "");
		}
		boolean rmFlag = publisher.removeDatastore(workspace, storeName, true);
		if (rmFlag) {
			return new GeoserverOperationResult(
					GeoserverOperationStatus.REMOVE_SUCCEED, "");
		} else {
			return new GeoserverOperationResult(
					GeoserverOperationStatus.REPUBLISH_FAILD, "");
		}
	}



}
