package bean;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Tabtaskdatastatus entity. @author MyEclipse Persistence Tools
 */

public class Tabtaskdatastatus implements java.io.Serializable {

	// Fields

	private BigDecimal foldernum;
	private String folder;
	private Set tabrsproductstatuses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tabtaskdatastatus() {
	}

	/** minimal constructor */
	public Tabtaskdatastatus(BigDecimal foldernum) {
		this.foldernum = foldernum;
	}

	/** full constructor */
	public Tabtaskdatastatus(BigDecimal foldernum, String folder,
			Set tabrsproductstatuses) {
		this.foldernum = foldernum;
		this.folder = folder;
		this.tabrsproductstatuses = tabrsproductstatuses;
	}

	// Property accessors

	public BigDecimal getFoldernum() {
		return this.foldernum;
	}

	public void setFoldernum(BigDecimal foldernum) {
		this.foldernum = foldernum;
	}

	public String getFolder() {
		return this.folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public Set getTabrsproductstatuses() {
		return this.tabrsproductstatuses;
	}

	public void setTabrsproductstatuses(Set tabrsproductstatuses) {
		this.tabrsproductstatuses = tabrsproductstatuses;
	}

}