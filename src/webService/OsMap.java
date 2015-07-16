package webService;

import java.io.File;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.henc.geoserver.GeoTool;
import com.henc.geoserver.GeoserverOperationResult;
import com.henc.geoserver.impl.GeoToolImpl;

import entity.DisPublishShapefileInfo;
import entity.PublishMapInfo;
import entity.DisPublishMapInfo;
import entity.PublishShapefileInfo;

@Path("/osmap")
public class OsMap {
	
	/**
	 * 发布栅格地图的 web service
	 * @param info 发布一个栅格地图所需的信息
	 * @return
	 */
    @POST
    @Path("/json/publish-map")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public PublishMapInfo addPublishMap(PublishMapInfo info) {
    	System.out.println(info.toString());
    	GeoTool geoTool;
		geoTool = new GeoToolImpl(info.getUrl(), info.getUserName(), info.getPassword());
		GeoserverOperationResult result;
		result = geoTool.publishMap(new File(info.getPathName()), info.getWorkspace(),info.getStoreName(),info.getCoverageName(), info.getFormat(), true);
		System.out.println(result.getStatus());
        return info;
    }
    
    /**
     * 删除一个栅格地图的 web service
     * @param info 删除一个栅格地图所需的信息
     * @return
     */
    @POST
    @Path("/json/disPublish-map")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public DisPublishMapInfo disPublishMap(DisPublishMapInfo info) {
    	System.out.println(info.toString());
    	GeoTool geoTool;
		geoTool = new GeoToolImpl(info.getUrl(), info.getUserName(), info.getPassword());
		GeoserverOperationResult result;
		result = geoTool.disPublishMap(info.getWorkspace(),info.getStoreName(),info.getCoverageName(), true);
		System.out.println(result.getStatus());
        return info;
    }
	 
    
	/**
	 * 发布矢量地图的 web service
	 * @param info 发布一个矢量地图所需的信息
	 * @return
	 */
    @POST
    @Path("/json/publish-shapefile")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	 public PublishShapefileInfo publishShapefile(PublishShapefileInfo info){
    	System.out.println(info.toString());
		 GeoTool geoTool;
		 geoTool = new GeoToolImpl(info.getUrl(), info.getUserName(), info.getPassword());
		 GeoserverOperationResult result;
		 result = geoTool.publishShapeFile(new File(info.getShpPath()), info.getFileName(), info.getWorkspace(), info.getStoreName(), info.getLayerName(), info.getSrs(), true);
		 System.out.println(result.getStatus());
		 return info;
	 }
    
    /**
     * 删除一个矢量地图的 web service
     * @param info 删除一个矢量地图所需的信息
     * @return
     */
    @POST
    @Path("/json/disPublish-shapefile")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public DisPublishShapefileInfo disPublishShapefile(DisPublishShapefileInfo info) {
    	System.out.println(info.toString());
    	GeoTool geoTool;
		geoTool = new GeoToolImpl(info.getUrl(), info.getUserName(), info.getPassword());
		GeoserverOperationResult result;
		result = geoTool.disPublishShapeFile(info.getWorkspace(),info.getStoreName(),info.getLayerName(),true);
		System.out.println(result.getStatus());
        return info;
    }
	 
	 public static void main(String[] args) {
		 GeoTool geoTool= new GeoToolImpl("http://192.168.1.100:7777/geoserver", "admin", "geoserver");
		 GeoserverOperationResult result;
		 //result = geoTool.publishShapeFile(new File("F:\\2014-2016\\Arcgis\\AT\\Data\\pt3"), "pts3.shp", "test", "pt3", "pts3", "EPSG:4326", true);
		 result = geoTool.disPublishShapeFile("test", "pt3" ,"pts3",true);
		 System.out.println(result.getStatus());
		 System.out.println(result.getInfo());
	 }
}