package bean;

import java.math.BigDecimal;


/**
 * TabRoleprivlege entity. @author MyEclipse Persistence Tools
 */

public class TabRoleprivlege  implements java.io.Serializable {


    // Fields    

     private BigDecimal roleprivid;
     private TabRole tabRole;
     private TabPrivlege tabPrivlege;


    // Constructors

    /** default constructor */
    public TabRoleprivlege() {
    }

    
    /** full constructor */
    public TabRoleprivlege(BigDecimal roleprivid, TabRole tabRole, TabPrivlege tabPrivlege) {
        this.roleprivid = roleprivid;
        this.tabRole = tabRole;
        this.tabPrivlege = tabPrivlege;
    }

   
    // Property accessors

    public BigDecimal getRoleprivid() {
        return this.roleprivid;
    }
    
    public void setRoleprivid(BigDecimal roleprivid) {
        this.roleprivid = roleprivid;
    }

    public TabRole getTabRole() {
        return this.tabRole;
    }
    
    public void setTabRole(TabRole tabRole) {
        this.tabRole = tabRole;
    }

    public TabPrivlege getTabPrivlege() {
        return this.tabPrivlege;
    }
    
    public void setTabPrivlege(TabPrivlege tabPrivlege) {
        this.tabPrivlege = tabPrivlege;
    }
   








}