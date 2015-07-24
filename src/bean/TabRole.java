package bean;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


/**
 * TabRole entity. @author MyEclipse Persistence Tools
 */

public class TabRole  implements java.io.Serializable {


    // Fields    

     private BigDecimal roleid;
     private String role;
     private String roledescription;
     private Set tabRoleprivleges = new HashSet(0);
     private Set tabUsers = new HashSet(0);


    // Constructors

    /** default constructor */
    public TabRole() {
    }

	/** minimal constructor */
    public TabRole(BigDecimal roleid, String role) {
        this.roleid = roleid;
        this.role = role;
    }
    
    /** full constructor */
    public TabRole(BigDecimal roleid, String role, String roledescription, Set tabRoleprivleges, Set tabUsers) {
        this.roleid = roleid;
        this.role = role;
        this.roledescription = roledescription;
        this.tabRoleprivleges = tabRoleprivleges;
        this.tabUsers = tabUsers;
    }

   
    // Property accessors

    public BigDecimal getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(BigDecimal roleid) {
        this.roleid = roleid;
    }

    public String getRole() {
        return this.role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }

    public String getRoledescription() {
        return this.roledescription;
    }
    
    public void setRoledescription(String roledescription) {
        this.roledescription = roledescription;
    }

    public Set getTabRoleprivleges() {
        return this.tabRoleprivleges;
    }
    
    public void setTabRoleprivleges(Set tabRoleprivleges) {
        this.tabRoleprivleges = tabRoleprivleges;
    }

    public Set getTabUsers() {
        return this.tabUsers;
    }
    
    public void setTabUsers(Set tabUsers) {
        this.tabUsers = tabUsers;
    }
   








}