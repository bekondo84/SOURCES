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

/**
 *
 * @author BEKO
 */
@Entity
@Table(name="T_REGL_CTB")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE",discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("TMP")
public class ReglementTmp extends BaseElement implements Serializable,Comparable<ReglementTmp>{

    @Predicate(label = "numero.piece",optional = false,unique = true,search = true)
    protected String code;
    
     
    @Temporal(javax.persistence.TemporalType.DATE)
    @Predicate(label = "date",type = Date.class,target = "date",optional = false,search = true)
    protected Date date ;

    public ReglementTmp() {
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
    public ReglementTmp(String code, Date date, long id, String designation, String moduleName, long comparedid) {
        super(id, designation, moduleName, comparedid);
        this.code = code;
        this.date = date;
    }
     
    public ReglementTmp(ReglementTmp entity) {
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
    public String getSerial() {
        return "160120191110RE"; //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int compareTo(ReglementTmp o) {
        return code.compareTo(o.code); //To change body of generated methods, choose Tools | Templates.
    }
    
}
