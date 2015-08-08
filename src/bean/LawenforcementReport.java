package bean;

/**
 * LawenforcementReport entity. @author MyEclipse Persistence Tools
 */

public class LawenforcementReport implements java.io.Serializable {

	// Fields

	private String reportnumber;
	private String datestr;
	private String machinenumber;
	private String airport;
	private String projectname;
	private String projectlocation;
	private String latlon;
	private String description;
	private String pictureUrl;
	private String feedback;
	private String backprojectname;
	private String marineunit;
	private String city;
	private String islegal;
	private String route;

	// Constructors

	/** default constructor */
	public LawenforcementReport() {
	}

	/** minimal constructor */
	public LawenforcementReport(String reportnumber) {
		this.reportnumber = reportnumber;
	}

	/** full constructor */
	public LawenforcementReport(String reportnumber, String datestr,
			String machinenumber, String airport, String projectname,
			String projectlocation, String latlon, String description,
			String pictureUrl, String feedback, String backprojectname,
			String marineunit, String city, String islegal, String route) {
		this.reportnumber = reportnumber;
		this.datestr = datestr;
		this.machinenumber = machinenumber;
		this.airport = airport;
		this.projectname = projectname;
		this.projectlocation = projectlocation;
		this.latlon = latlon;
		this.description = description;
		this.pictureUrl = pictureUrl;
		this.feedback = feedback;
		this.backprojectname = backprojectname;
		this.marineunit = marineunit;
		this.city = city;
		this.islegal = islegal;
		this.route = route;
	}

	// Property accessors

	public String getReportnumber() {
		return this.reportnumber;
	}

	public void setReportnumber(String reportnumber) {
		this.reportnumber = reportnumber;
	}

	public String getDatestr() {
		return this.datestr;
	}

	public void setDatestr(String datestr) {
		this.datestr = datestr;
	}

	public String getMachinenumber() {
		return this.machinenumber;
	}

	public void setMachinenumber(String machinenumber) {
		this.machinenumber = machinenumber;
	}

	public String getAirport() {
		return this.airport;
	}

	public void setAirport(String airport) {
		this.airport = airport;
	}

	public String getProjectname() {
		return this.projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getProjectlocation() {
		return this.projectlocation;
	}

	public void setProjectlocation(String projectlocation) {
		this.projectlocation = projectlocation;
	}

	public String getLatlon() {
		return this.latlon;
	}

	public void setLatlon(String latlon) {
		this.latlon = latlon;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPictureUrl() {
		return this.pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getFeedback() {
		return this.feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getBackprojectname() {
		return this.backprojectname;
	}

	public void setBackprojectname(String backprojectname) {
		this.backprojectname = backprojectname;
	}

	public String getMarineunit() {
		return this.marineunit;
	}

	public void setMarineunit(String marineunit) {
		this.marineunit = marineunit;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getIslegal() {
		return this.islegal;
	}

	public void setIslegal(String islegal) {
		this.islegal = islegal;
	}

	public String getRoute() {
		return this.route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	@Override
	public String toString() {
		return "LawenforcementReport [reportnumber=" + reportnumber
				+ ", datestr=" + datestr + ", machinenumber=" + machinenumber
				+ ", airport=" + airport + ", projectname=" + projectname
				+ ", projectlocation=" + projectlocation + ", latlon=" + latlon
				+ ", description=" + description + ", pictureUrl=" + pictureUrl
				+ ", feedback=" + feedback + ", backprojectname="
				+ backprojectname + ", marineunit=" + marineunit + ", city="
				+ city + ", islegal=" + islegal + ", route=" + route + "]";
	}
	
	

}