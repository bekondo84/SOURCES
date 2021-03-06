/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatimgroup.model.referentiels;

import com.megatim.common.annotations.Predicate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Commercial_2
 */
@Entity
@Table(name="SENS_OPERATION")
public class SensOperation implements Serializable, Comparable<SensOperation> {
    
     @Id
    @Column(name="CODE")
    @Predicate(max=1,nullable=false,optional=false)
    private String code ;
    
    @Column(name="LIBELLE")
    private String designation ;

    
    /**
     * 
     */
    public SensOperation() {
    }

    
    /**
     * 
     * @param code
     * @param designation 
     */
    public SensOperation(String code, String designation) {
        this.code = code;
        this.designation = designation;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.code != null ? this.code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SensOperation other = (SensOperation) obj;
        if ((this.code == null) ? (other.code != null) : !this.code.equals(other.code)) {
            return false;
        }
        return true;
    }

    
    
    public int compareTo(SensOperation o) {
        return code.compareTo(o.code);
    }
    
    public String toString() {
        return code +" - " + designation ;
    }
    
}
