package com.henc.geoserver;

import java.io.File;

public interface GeoTool {
	

	public GeoserverOperationResult publishMap(File map, String workspace,
			String storeName, String coverageName, String format,
			boolean isRepublish);

	public GeoserverOperationResult disPublishMap(String workspace,
			String storeName, String coverageName, boolean remove);

	public GeoserverOperationResult publishStyle(File style, String styleName,
			boolean isRepublish);

	public GeoserverOperationResult disPublishStyle(String styleName);

	public GeoserverOperationResult publishShapeFile(File shpPath,
			String fileName, String workspace, String storeName,
			String layerName, String srs, boolean isRepublish);
	
	public GeoserverOperationResult disPublishShapeFile(String workspace,
			String storeName ,String layerName ,boolean remove);
}
