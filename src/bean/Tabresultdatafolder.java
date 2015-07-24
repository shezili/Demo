package bean;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Tabresultdatafolder entity. @author MyEclipse Persistence Tools
 */

public class Tabresultdatafolder implements java.io.Serializable {

	// Fields

	private BigDecimal folderId;
	private String folderName;
	private Set tabresultdatas = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tabresultdatafolder() {
	}

	/** minimal constructor */
	public Tabresultdatafolder(BigDecimal folderId) {
		this.folderId = folderId;
	}

	/** full constructor */
	public Tabresultdatafolder(BigDecimal folderId, String folderName,
			Set tabresultdatas) {
		this.folderId = folderId;
		this.folderName = folderName;
		this.tabresultdatas = tabresultdatas;
	}

	// Property accessors

	public BigDecimal getFolderId() {
		return this.folderId;
	}

	public void setFolderId(BigDecimal folderId) {
		this.folderId = folderId;
	}

	public String getFolderName() {
		return this.folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public Set getTabresultdatas() {
		return this.tabresultdatas;
	}

	public void setTabresultdatas(Set tabresultdatas) {
		this.tabresultdatas = tabresultdatas;
	}

}