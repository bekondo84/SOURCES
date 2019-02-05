/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.ventes;

import com.core.base.BaseElement;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author BEKO
 */
@Entity
@Table(name="T_NOFR_CTB")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE",discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("TMP")
public class NoteFraisTMP extends BaseElement implements Serializable,Comparable<NoteFraisTMP>{

    @Predicate(label = "numero.piece",optional = false,unique = true,search = true)
    protected String code ;
    
     @Temporal(TemporalType.DATE)
    @Predicate(label = "date",target = "date",type = Date.class,optional = false,search = true)
    protected Date date ;

    public NoteFraisTMP() {
    }

    /**
     * 
     * @param code
     * @param date
     * @param id
     * @param designation
     * @param moduleName
     * @param comparedid 
     */
    public NoteFraisTMP(String code, Date date, long id, String designation, String moduleName, long comparedid) {
        super(id, designation, moduleName, comparedid);
        this.code = code;
        this.date = date;
    }
    
     public NoteFraisTMP(NoteFraisTMP entity) {
        super(entity.id, entity.designation, entity.moduleName, entity.compareid);
        this.code = entity.code;
        this.date = entity.date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
     
     
     
    @Override
    public int compareTo(NoteFraisTMP o) {
         //To change body of generated methods, choose Tools | Templates.
        return code.compareTo(o.code);
    }
    
}
