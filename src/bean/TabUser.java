package bean;

import java.math.BigDecimal;


/**
 * TabUser entity. @author MyEclipse Persistence Tools
 */

public class TabUser  implements java.io.Serializable {


    // Fields    

     private BigDecimal userid;
     private TabRole tabRole;
     private String username;
     private String password;
     private String status;
     private String userdescription;
     private String realname;
     private String postion;
     private String male;
     private String tel;


    // Constructors

    /** default constructor */
    public TabUser() {
    }

	/** minimal constructor */
    public TabUser(BigDecimal userid, String username) {
        this.userid = userid;
        this.username = username;
    }
    
    /** full constructor */
    public TabUser(BigDecimal userid, TabRole tabRole, String username, String password, String status, String userdescription, String realname, String postion, String male, String tel) {
        this.userid = userid;
        this.tabRole = tabRole;
        this.username = username;
        this.password = password;
        this.status = status;
        this.userdescription = userdescription;
        this.realname = realname;
        this.postion = postion;
        this.male = male;
        this.tel = tel;
    }

   
    // Property accessors

    public BigDecimal getUserid() {
        return this.userid;
    }
    
    public void setUserid(BigDecimal userid) {
        this.userid = userid;
    }

    public TabRole getTabRole() {
        return this.tabRole;
    }
    
    public void setTabRole(TabRole tabRole) {
        this.tabRole = tabRole;
    }

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserdescription() {
        return this.userdescription;
    }
    
    public void setUserdescription(String userdescription) {
        this.userdescription = userdescription;
    }

    public String getRealname() {
        return this.realname;
    }
    
    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPostion() {
        return this.postion;
    }
    
    public void setPostion(String postion) {
        this.postion = postion;
    }

    public String getMale() {
        return this.male;
    }
    
    public void setMale(String male) {
        this.male = male;
    }

    public String getTel() {
        return this.tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }
   








}