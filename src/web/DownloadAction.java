package web;

import tool.OGCTool;

import com.opensymphony.xwork2.ActionSupport;

import entity.ServerConfig;

/**
 * @author she download the data of the map that you have selected
 *         获取图层名称fullName和选取边界cropBbox 返回下载链接returnLink
 * */

public class DownloadAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fullName;
	private String cropBbox;
	private String returnLink;


	public String execute() throws Exception {
		System.out.println(ServerConfig.getInstance().getConfig()
				.getProperty("GEO_URL"));

		String geoUrl = ServerConfig.getInstance().getConfig()
				.getProperty("GEO_URL");
		// String geoUrlString =
		// request.getServletContext().getInitParameter("GEO_URL");
		// System.out.println("action started");
		// System.out.println("fullName is:"+fullName+";"+"cropBbox is:"+cropBbox);
		returnLink = OGCTool.getWCSMapPostLink(geoUrl, fullName, "ENVIHdr",
				cropBbox);
		// System.out.println("the link is:"+getReturnLink());
		// System.out.println("action executed success");
		if (returnLink.equals("failed")) {
			System.out.println("failed");
			return ERROR;
		} else {
			return SUCCESS;
		}
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCropBbox() {
		return cropBbox;
	}

	public void setCropBbox(String cropBbox) {
		this.cropBbox = cropBbox;
	}

	public String getReturnLink() {
		return returnLink;
	}

	public void setReturnLink(String returnLink) {
		this.returnLink = returnLink;
	}

}
