//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.08.09 at 08:33:11 AM GMT+01:00 
//


package com.kerem.genarated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *       &lt;sequence>
 *         &lt;element ref="{http://www.keren.net/keren}principal" minOccurs="0"/>
 *         &lt;element ref="{http://www.keren.net/keren}containertemplate" minOccurs="0"/>
 *         &lt;element ref="{http://www.keren.net/keren}logintemplate" minOccurs="0"/>
 *         &lt;element ref="{http://www.keren.net/keren}discussiontemplate" minOccurs="0"/>
 *         &lt;element ref="{http://www.keren.net/keren}formtemplate" minOccurs="0"/>
 *         &lt;element ref="{http://www.keren.net/keren}treetemplate" minOccurs="0"/>
 *         &lt;element ref="{http://www.keren.net/keren}calendartemplate" minOccurs="0"/>
 *         &lt;element ref="{http://www.keren.net/keren}reporttemplate" minOccurs="0"/>
 *         &lt;element ref="{http://www.keren.net/keren}dashboardtemplate" minOccurs="0"/>
 *         &lt;element ref="{http://www.keren.net/keren}importtemplate" minOccurs="0"/>
 *         &lt;element ref="{http://www.keren.net/keren}exporttemplate" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "principal",
    "containertemplate",
    "logintemplate",
    "discussiontemplate",
    "formtemplate",
    "treetemplate",
    "calendartemplate",
    "reporttemplate",
    "dashboardtemplate",
    "importtemplate",
    "exporttemplate"
})
@XmlRootElement(name = "theme")
public class Theme {

    protected Principal principal;
    protected Containertemplate containertemplate;
    protected Logintemplate logintemplate;
    protected Discussiontemplate discussiontemplate;
    protected Formtemplate formtemplate;
    protected Treetemplate treetemplate;
    protected Calendartemplate calendartemplate;
    protected Reporttemplate reporttemplate;
    protected Dashboardtemplate dashboardtemplate;
    protected Importtemplate importtemplate;
    protected Exporttemplate exporttemplate;

    /**
     * Gets the value of the principal property.
     * 
     * @return
     *     possible object is
     *     {@link Principal }
     *     
     */
    public Principal getPrincipal() {
        return principal;
    }

    /**
     * Sets the value of the principal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Principal }
     *     
     */
    public void setPrincipal(Principal value) {
        this.principal = value;
    }

    /**
     * Gets the value of the containertemplate property.
     * 
     * @return
     *     possible object is
     *     {@link Containertemplate }
     *     
     */
    public Containertemplate getContainertemplate() {
        return containertemplate;
    }

    /**
     * Sets the value of the containertemplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Containertemplate }
     *     
     */
    public void setContainertemplate(Containertemplate value) {
        this.containertemplate = value;
    }

    /**
     * Gets the value of the logintemplate property.
     * 
     * @return
     *     possible object is
     *     {@link Logintemplate }
     *     
     */
    public Logintemplate getLogintemplate() {
        return logintemplate;
    }

    /**
     * Sets the value of the logintemplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Logintemplate }
     *     
     */
    public void setLogintemplate(Logintemplate value) {
        this.logintemplate = value;
    }

    /**
     * Gets the value of the discussiontemplate property.
     * 
     * @return
     *     possible object is
     *     {@link Discussiontemplate }
     *     
     */
    public Discussiontemplate getDiscussiontemplate() {
        return discussiontemplate;
    }

    /**
     * Sets the value of the discussiontemplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Discussiontemplate }
     *     
     */
    public void setDiscussiontemplate(Discussiontemplate value) {
        this.discussiontemplate = value;
    }

    /**
     * Gets the value of the formtemplate property.
     * 
     * @return
     *     possible object is
     *     {@link Formtemplate }
     *     
     */
    public Formtemplate getFormtemplate() {
        return formtemplate;
    }

    /**
     * Sets the value of the formtemplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Formtemplate }
     *     
     */
    public void setFormtemplate(Formtemplate value) {
        this.formtemplate = value;
    }

    /**
     * Gets the value of the treetemplate property.
     * 
     * @return
     *     possible object is
     *     {@link Treetemplate }
     *     
     */
    public Treetemplate getTreetemplate() {
        return treetemplate;
    }

    /**
     * Sets the value of the treetemplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Treetemplate }
     *     
     */
    public void setTreetemplate(Treetemplate value) {
        this.treetemplate = value;
    }

    /**
     * Gets the value of the calendartemplate property.
     * 
     * @return
     *     possible object is
     *     {@link Calendartemplate }
     *     
     */
    public Calendartemplate getCalendartemplate() {
        return calendartemplate;
    }

    /**
     * Sets the value of the calendartemplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Calendartemplate }
     *     
     */
    public void setCalendartemplate(Calendartemplate value) {
        this.calendartemplate = value;
    }

    /**
     * Gets the value of the reporttemplate property.
     * 
     * @return
     *     possible object is
     *     {@link Reporttemplate }
     *     
     */
    public Reporttemplate getReporttemplate() {
        return reporttemplate;
    }

    /**
     * Sets the value of the reporttemplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Reporttemplate }
     *     
     */
    public void setReporttemplate(Reporttemplate value) {
        this.reporttemplate = value;
    }

    /**
     * Gets the value of the dashboardtemplate property.
     * 
     * @return
     *     possible object is
     *     {@link Dashboardtemplate }
     *     
     */
    public Dashboardtemplate getDashboardtemplate() {
        return dashboardtemplate;
    }

    /**
     * Sets the value of the dashboardtemplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Dashboardtemplate }
     *     
     */
    public void setDashboardtemplate(Dashboardtemplate value) {
        this.dashboardtemplate = value;
    }

    /**
     * Gets the value of the importtemplate property.
     * 
     * @return
     *     possible object is
     *     {@link Importtemplate }
     *     
     */
    public Importtemplate getImporttemplate() {
        return importtemplate;
    }

    /**
     * Sets the value of the importtemplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Importtemplate }
     *     
     */
    public void setImporttemplate(Importtemplate value) {
        this.importtemplate = value;
    }

    /**
     * Gets the value of the exporttemplate property.
     * 
     * @return
     *     possible object is
     *     {@link Exporttemplate }
     *     
     */
    public Exporttemplate getExporttemplate() {
        return exporttemplate;
    }

    /**
     * Sets the value of the exporttemplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Exporttemplate }
     *     
     */
    public void setExporttemplate(Exporttemplate value) {
        this.exporttemplate = value;
    }

}
