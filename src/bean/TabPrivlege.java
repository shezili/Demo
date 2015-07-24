package bean;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


/**
 * TabPrivlege entity. @author MyEclipse Persistence Tools
 */

public class TabPrivlege  implements java.io.Serializable {


    // Fields    

     private BigDecimal privid;
     private String priv;
     private String privdescription;
     private Set tabRoleprivleges = new HashSet(0);


    // Constructors

    /** default constructor */
    public TabPrivlege() {
    }

	/** minimal constructor */
    public TabPrivlege(BigDecimal privid, String priv) {
        this.privid = privid;
        this.priv = priv;
    }
    
    /** full constructor */
    public TabPrivlege(BigDecimal privid, String priv, String privdescription, Set tabRoleprivleges) {
        this.privid = privid;
        this.priv = priv;
        this.privdescription = privdescription;
        this.tabRoleprivleges = tabRoleprivleges;
    }

   
    // Property accessors

    public BigDecimal getPrivid() {
        return this.privid;
    }
    
    public void setPrivid(BigDecimal privid) {
        this.privid = privid;
    }

    public String getPriv() {
        return this.priv;
    }
    
    public void setPriv(String priv) {
        this.priv = priv;
    }

    public String getPrivdescription() {
        return this.privdescription;
    }
    
    public void setPrivdescription(String privdescription) {
        this.privdescription = privdescription;
    }

    public Set getTabRoleprivleges() {
        return this.tabRoleprivleges;
    }
    
    public void setTabRoleprivleges(Set tabRoleprivleges) {
        this.tabRoleprivleges = tabRoleprivleges;
    }
   








}