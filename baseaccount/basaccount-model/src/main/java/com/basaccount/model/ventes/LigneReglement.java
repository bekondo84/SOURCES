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
@Table(name="T_LIGREG_CTB")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE",discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("TMP")
public class LigneReglement extends BaseElement implements Serializable,Comparable<LigneReglement>{

    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Predicate(label = "echeance",type = Date.class,target = "date",search = true)
    protected Date echeance ;
    
    @Predicate(label = "montant",target = "number",type = Double.class,search = true,optional = false)
    protected Double montant ;

    public LigneReglement() {
    }

    /**
     * 
     * @param echeance
     * @param montant
     * @param id
     * @param designation
     * @param moduleName
     * @param comparedid 
     */
    public LigneReglement(Date echeance, Double montant, long id, String designation, String moduleName, long comparedid) {
        super(id, designation, moduleName, comparedid);
        this.echeance = echeance;
        this.montant = montant;
    }
    
    
    public LigneReglement(LigneReglement entity) {
        super(entity.id, entity.designation, entity.moduleName, entity.compareid);
        this.echeance = entity.echeance;
        this.montant = entity.montant;
    }

    public Date getEcheance() {
        return echeance;
    }

    public void setEcheance(Date echeance) {
        this.echeance = echeance;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }
    
    
    
    @Override
    public int compareTo(LigneReglement o) {
        return echeance.compareTo(o.echeance); //To change body of generated methods, choose Tools | Templates.
    }
    
}
