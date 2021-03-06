/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatimgroup.generic.jax.rs.layer.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Commercial_2
 */
@XmlRootElement
public class MetaData implements Serializable,Comparable<MetaData>{

    private String entityName ;
    
    private String moduleName;
    
    private String editTitle ;
    
    private String listTitle ;
    
    private boolean createonfield = true ;
    
    private boolean desablecreate = false;
    
    private boolean desabledelete = false ;
    
    private boolean desableupdate = false ;
    
    private List<MetaColumn> header = new ArrayList<MetaColumn>();
    
    private List<MetaColumn> columns = new ArrayList<MetaColumn>();
    
    private List<MetaGroup> groups = new ArrayList<MetaGroup>();
    
    private boolean activefilelink = false ;
    
    private List<State> states =null;
    
    private String footerScript = null;
    
    private boolean activatefollower = false ;
    
    private String[] searchfields = null;
    
    private String className ;
    
    private String ownermodule ;
    
    private String ownerentity ;
    

    /**
     * 
     */
    public MetaData() {
    }

    
    /**
     * 
     * @param entityName
     * @param moduleName
     * @param editTitle
     * @param listTitle 
     */
    public MetaData(String entityName,String moduleName, String editTitle, String listTitle) {
        this.entityName = entityName;
        this.editTitle = editTitle;
        this.listTitle = listTitle;
        this.moduleName = moduleName;
        
    }

    public MetaData(MetaData meta) {
        this.entityName = meta.entityName;
        this.editTitle = meta.editTitle;
        this.listTitle = meta.listTitle;
        this.moduleName = meta.moduleName;
        this.createonfield = meta.createonfield;
        this.desablecreate = meta.desablecreate;
        this.desabledelete = meta.desabledelete;
        this.desableupdate = meta.desableupdate;
        this.activefilelink = meta.activefilelink;
        this.footerScript = meta.footerScript;
        this.searchfields = meta.searchfields;
        this.className = meta.className;
        this.ownermodule = meta.ownermodule;
        this.ownerentity = meta.ownerentity;
        if(meta.states!=null){
            for(State state:meta.states){
                states.add(new State(state));
            }
        }//end if(meta.states!=null){
        for(MetaColumn col:meta.header){
            this.header.add(new MetaColumn(col));
        }
        for(MetaColumn col:meta.columns){
            this.columns.add(new MetaColumn(col));
        }
        for(MetaGroup grp:meta.groups){
            this.groups.add(new MetaGroup(grp));
        }
        //if()
    }
    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }    
    
    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEditTitle() {
        return editTitle;
    }

    public void setEditTitle(String editTitle) {
        this.editTitle = editTitle;
    }

    public String getListTitle() {
        return listTitle;
    }

    public void setListTitle(String listTitle) {
        this.listTitle = listTitle;
    }

    public List<MetaColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<MetaColumn> columns) {
        this.columns = columns;
    }

    public List<MetaGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<MetaGroup> groups) {
        this.groups = groups;
    }

    public boolean isCreateonfield() {
        return createonfield;
    }

    public void setCreateonfield(boolean createonfield) {
        this.createonfield = createonfield;
    }

    public boolean isDesablecreate() {
        return desablecreate;
    }

    public void setDesablecreate(boolean desablecreate) {
        this.desablecreate = desablecreate;
    }

    public boolean isDesabledelete() {
        return desabledelete;
    }

    public void setDesabledelete(boolean desabledelete) {
        this.desabledelete = desabledelete;
    }

    public boolean isDesableupdate() {
        return desableupdate;
    }

    public void setDesableupdate(boolean desableupdate) {
        this.desableupdate = desableupdate;
    }   

    public List<MetaColumn> getHeader() {
        return header;
    }

    public void setHeader(List<MetaColumn> header) {
        this.header = header;
    }

    public boolean isActivefilelink() {
        return activefilelink;
    }

    public void setActivefilelink(boolean activefilelink) {
        this.activefilelink = activefilelink;
    }

    public List<State> getStates() {
        return states;
    }
   

    public String getFooterScript() {
        return footerScript;
    }

    public void setFooterScript(String footerScript) {
        this.footerScript = footerScript;
    }    
    

    public void setStates(List<State> states) {
        this.states = states;
    }    

    public boolean isActivatefollower() {
        return activatefollower;
    }

    public void setActivatefollower(boolean activatefollower) {
        this.activatefollower = activatefollower;
    }

    public String[] getSearchfields() {
        return searchfields;
    }

    public void setSearchfields(String[] searchfields) {
        this.searchfields = searchfields;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getOwnermodule() {
        return ownermodule;
    }

    public void setOwnermodule(String ownermodule) {
        this.ownermodule = ownermodule;
    }

    public String getOwnerentity() {
        return ownerentity;
    }

    public void setOwnerentity(String ownerentity) {
        this.ownerentity = ownerentity;
    }
    
    

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    @Override
    public String toString() {
        return "MetaData{" + "entityName=" + entityName + ", editTitle=" + editTitle + ", listTitle=" + listTitle + ", columns=" + columns + ", groups=" + groups + '}';
    }
    
    
    
    @Override
    public int compareTo(MetaData o) {
        //To change body of generated methods, choose Tools | Templates.
        return entityName.compareTo(o.entityName);
    }
    
}
