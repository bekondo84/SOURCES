/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatimgroup.generic.jax.rs.layer.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BEKO
 */
public class TreeNode implements Serializable,Comparable<TreeNode>{

    private String text ;
    
    private String[] tags;
    
    private long source;
    
    private String modulename;
    
    private String entityname ;    
    
    private List<TreeNode> children = new ArrayList<TreeNode>();

    /**
     * 
     */
    public TreeNode() {
    }

    /**
     * 
     * @param label
     * @param entityname
     * @param moduleName
     * @param source 
     */
    public TreeNode(String label, String entityname, String moduleName, long source) {
        this.text = label;
        this.source = source;
        this.entityname = entityname;
        this.modulename = moduleName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    

    public long getSource() {
        return source;
    }

    public void setSource(long source) {
        this.source = source;
    }

    public String getEntityname() {
        return entityname;
    }

    public void setEntityname(String entityname) {
        this.entityname = entityname;
    }    

    public String getModulename() {
        return modulename;
    }

    public void setModulename(String modulename) {
        this.modulename = modulename;
    }    
    

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
    
    
    
    @Override
    public int compareTo(TreeNode o) {
        //To change body of generated methods, choose Tools | Templates.
        return text.compareTo(o.text);
    }
    
}
