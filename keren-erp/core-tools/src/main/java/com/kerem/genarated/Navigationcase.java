//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.08.22 at 12:34:47 PM GMT+01:00 
//


package com.kerem.genarated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="outcome" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="viewid" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="sectionid" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "navigationcase")
public class Navigationcase {

    @XmlAttribute(name = "outcome")
    protected String outcome;
    @XmlAttribute(name = "viewid")
    protected String viewid;
    @XmlAttribute(name = "sectionid")
    protected String sectionid;

    /**
     * Gets the value of the outcome property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutcome() {
        return outcome;
    }

    /**
     * Sets the value of the outcome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutcome(String value) {
        this.outcome = value;
    }

    /**
     * Gets the value of the viewid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getViewid() {
        return viewid;
    }

    /**
     * Sets the value of the viewid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setViewid(String value) {
        this.viewid = value;
    }

    /**
     * Gets the value of the sectionid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSectionid() {
        return sectionid;
    }

    /**
     * Sets the value of the sectionid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSectionid(String value) {
        this.sectionid = value;
    }

}
